package tableModel;

import bean.LunchMenuItemBean;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Allows selecting items in table.
 */
public class SelectableLunchMenuItemsModel extends ListDataModel<LunchMenuItemBean> implements SelectableDataModel<LunchMenuItemBean> {

    @Override
    public Object getRowKey(LunchMenuItemBean object) {
        return object.getId();
    }

    @Override
    public LunchMenuItemBean getRowData(String rowKey) {
        List<LunchMenuItemBean> lunchMenuItemBeanList = (List<LunchMenuItemBean>) getWrappedData();

        for(LunchMenuItemBean lunchMenuItemBean : lunchMenuItemBeanList) {
            if(lunchMenuItemBean.getId().toString().equals(rowKey))
                return lunchMenuItemBean;
        }

        return null;
    }
}
