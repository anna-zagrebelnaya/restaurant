package controller;

import beans.MessageBean;
import org.primefaces.model.LazyDataModel;

import java.util.List;

public class LazyMessageDataModel extends LazyDataModel<MessageBean> {

    @Override
    public MessageBean getRowData(String rowKey) {
        List<MessageBean> list = (List<MessageBean>) getWrappedData();
        for(MessageBean messageBean : list) {
            if(messageBean.getId().toString().equals(rowKey))
                return messageBean;
        }

        return null;
    }

    @Override
    public Object getRowKey(MessageBean messageBean) {
        return messageBean.getId();
    }
}
