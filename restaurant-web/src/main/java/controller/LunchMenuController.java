package controller;

import DAO.LunchMenuItemDAO;
import bean.LunchMenuItemBean;
import tableModel.SelectableLunchMenuItemsModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Named
@SessionScoped
public class LunchMenuController implements Serializable {

    @Inject
    private LunchMenuItemDAO lunchMenuItemDAO;

    private List<LunchMenuItemBean> selectedLunchMenuItemBeans;
    private List<LunchMenuItemBean> filteredLunchMenuItemBeans;
    private SelectableLunchMenuItemsModel selectableLunchMenuItemsModel;
    private Double startPrice;
    private Double endPrice;

    public LunchMenuController() {
        selectedLunchMenuItemBeans = new ArrayList<LunchMenuItemBean>();
        filteredLunchMenuItemBeans = new ArrayList<LunchMenuItemBean>();
        selectableLunchMenuItemsModel = new SelectableLunchMenuItemsModel();
        startPrice = 0D;
        endPrice = 0D;
    }

    @PostConstruct
    public void init() {
        lunchMenuItemDAO.newLunchMenuItem("salad", 4D);
        lunchMenuItemDAO.newLunchMenuItem("chicken", 3D);
        lunchMenuItemDAO.newLunchMenuItem("soup", 2D);
        lunchMenuItemDAO.newLunchMenuItem("roast-beef", 5D);
        lunchMenuItemDAO.newLunchMenuItem("potatoes", 2D);
        lunchMenuItemDAO.newLunchMenuItem("lazania", 2D);
        filteredLunchMenuItemBeans.addAll(lunchMenuItemDAO.getLunchMenuItemBeanList());
        updateModel();
    }

    //getters and setters

    public List<LunchMenuItemBean> getSelectedLunchMenuItemBeans() {
        return selectedLunchMenuItemBeans;
    }

    public void setSelectedLunchMenuItemBeans(List<LunchMenuItemBean> selectedLunchMenuItemBeans) {
        this.selectedLunchMenuItemBeans = selectedLunchMenuItemBeans;
    }

    public List<LunchMenuItemBean> getFilteredLunchMenuItemBeans() {
        return filteredLunchMenuItemBeans;
    }

    public void setFilteredLunchMenuItemBeans(List<LunchMenuItemBean> filteredLunchMenuItemBeans) {
        this.filteredLunchMenuItemBeans = filteredLunchMenuItemBeans;
    }

    public SelectableLunchMenuItemsModel getSelectableLunchMenuItemsModel() {
        return selectableLunchMenuItemsModel;
    }

    public void setSelectableLunchMenuItemsModel(SelectableLunchMenuItemsModel selectableLunchMenuItemsModel) {
        this.selectableLunchMenuItemsModel = selectableLunchMenuItemsModel;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    //...getters and setters

    private void updateModel() {
        selectableLunchMenuItemsModel.setWrappedData(lunchMenuItemDAO.getLunchMenuItemBeanList());
    }

    public void filterPrice(AjaxBehaviorEvent e) {
        filteredLunchMenuItemBeans.clear();
        List<LunchMenuItemBean> list = lunchMenuItemDAO.getLunchMenuItemBeanList();
        for (LunchMenuItemBean lunchMenuItemBean : list) {

            if (!startPrice.equals(0D) &&
                !startPrice.isNaN() &&
                !(lunchMenuItemBean.getPrice()>=startPrice)) {
                continue;
            }

            if (!endPrice.equals(0D) &&
                !endPrice.isNaN() &&
                !(lunchMenuItemBean.getPrice()<=endPrice)) {
                continue;
            }

            filteredLunchMenuItemBeans.add(lunchMenuItemBean);
        }
    }

}
