package bean;

import java.util.List;

public class OrderBean {

    private List<LunchMenuItemBean> lunchMenuItemBeans;
    private String clientName;
    private Integer totalAmount;
    private Double totalSumm;

    public OrderBean(List<LunchMenuItemBean> lunchMenuItemBeans, String clientName) {
        this.lunchMenuItemBeans = lunchMenuItemBeans;
        this.clientName = clientName;
    }

    public List<LunchMenuItemBean> getLunchMenuItemBeans() {
        return lunchMenuItemBeans;
    }

    public void setLunchMenuItemBeans(List<LunchMenuItemBean> lunchMenuItemBeans) {
        this.lunchMenuItemBeans = lunchMenuItemBeans;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalSumm() {
        return totalSumm;
    }

    public void setTotalSumm(Double totalSumm) {
        this.totalSumm = totalSumm;
    }

    public Integer getAmountOfItemByDescription(String description) {
        for (LunchMenuItemBean lunchMenuItemBean : lunchMenuItemBeans) {
            if (lunchMenuItemBean.getDescription().equals(description)) {
                return lunchMenuItemBean.getAmount();
            }
        }
        return 0;
    }

    public void calcTotal() {
        totalAmount = 0;
        totalSumm =0D;
        for (LunchMenuItemBean lunchMenuItemBean : lunchMenuItemBeans) {
            totalAmount += lunchMenuItemBean.getAmount();
            totalSumm += lunchMenuItemBean.getPrice() * lunchMenuItemBean.getAmount();
        }
    }
}
