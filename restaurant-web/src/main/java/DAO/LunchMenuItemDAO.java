package DAO;

import bean.LunchMenuItemBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class LunchMenuItemDAO implements Serializable {

    private List<LunchMenuItemBean> lunchMenuItemBeanList;

    public LunchMenuItemDAO() {
        lunchMenuItemBeanList = new ArrayList<LunchMenuItemBean>();
    }

    public List<LunchMenuItemBean> getLunchMenuItemBeanList() {
        return lunchMenuItemBeanList;
    }

    public void setLunchMenuItemBeanList(List<LunchMenuItemBean> lunchMenuItemBeanList) {
        this.lunchMenuItemBeanList = lunchMenuItemBeanList;
    }

    public LunchMenuItemBean newLunchMenuItem(String description, Double price) {
        LunchMenuItemBean lunchMenuItemBean = new LunchMenuItemBean(description, price);
        lunchMenuItemBeanList.add(lunchMenuItemBean);
        return lunchMenuItemBean;
    }

    public LunchMenuItemBean findLunchMenuItemById(Long lunchMenuItemId) {
        for (LunchMenuItemBean lunchMenuItemBean : lunchMenuItemBeanList) {
            if (lunchMenuItemBean.getId().equals(lunchMenuItemId)) {
                return lunchMenuItemBean;
            }
        }
        return null;
    }

}
