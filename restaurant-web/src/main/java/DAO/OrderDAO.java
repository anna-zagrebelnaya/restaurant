package DAO;

import bean.LunchMenuItemBean;
import bean.OrderBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class OrderDAO implements Serializable {
    private List<OrderBean> orderBeanList;

    public OrderDAO() {
        orderBeanList = new ArrayList<OrderBean>();
    }

    public List<OrderBean> getOrderBeanList() {
        return orderBeanList;
    }

    public void setOrderBeanList(List<OrderBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }

    public OrderBean newOrder(List<LunchMenuItemBean> lunchMenuItemBeans, String clientName) {
        OrderBean orderBean = new OrderBean(lunchMenuItemBeans, clientName);
        orderBean.calcTotal();
        orderBeanList.add(orderBean);
        return orderBean;
    }

    public List<LunchMenuItemBean> getLunchMenuItemsFromAllOrders() {
        List<LunchMenuItemBean> lunchMenuItemBeans = new ArrayList<LunchMenuItemBean>();
        for (OrderBean orderBean : orderBeanList) {
            lunchMenuItemBeans.addAll(orderBean.getLunchMenuItemBeans());
        }
        return lunchMenuItemBeans;
    }

    public Integer getAmountOfItemByDescriptionInAllOrders(String description) {
        Integer amount = 0;
        for (OrderBean orderBean : orderBeanList) {
            amount += orderBean.getAmountOfItemByDescription(description);
        }
        return amount;
    }

    public Integer getTotalAmountInAllOrders() {
        Integer totalAmount = 0;
        for (OrderBean orderBean : orderBeanList) {
            totalAmount += orderBean.getTotalAmount();
        }
        return totalAmount;
    }

    public Double getTotalSummInAllOrders() {
        Double totalSumm = 0D;
        for (OrderBean orderBean : orderBeanList) {
            totalSumm += orderBean.getTotalSumm();
        }
        return totalSumm;
    }
}
