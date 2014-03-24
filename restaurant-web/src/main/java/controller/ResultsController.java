package controller;

import DAO.OrderDAO;
import bean.LunchMenuItemBean;
import tableModel.ColumnModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Named
@RequestScoped
public class ResultsController implements Serializable {

    @Inject
    private OrderDAO orderDAO;

    private List<ColumnModel> columns;

    public ResultsController() {
        columns = new ArrayList<ColumnModel>();
    }

    @PostConstruct
    public void init() {
        List<LunchMenuItemBean> lunchMenuItemBeans = orderDAO.getLunchMenuItemsFromAllOrders();
        for (LunchMenuItemBean lunchMenuItemBean : lunchMenuItemBeans) {
            ColumnModel newColumn = new ColumnModel(lunchMenuItemBean.getDescription());
            if (!columns.contains(newColumn)) {
                columns.add(newColumn);
            }
        }
    }

    //getters and setters

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }


    //...getters and setters


}
