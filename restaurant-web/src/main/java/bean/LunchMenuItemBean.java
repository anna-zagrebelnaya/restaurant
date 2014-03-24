package bean;

/**
 * Row of menu or order
 */
public class LunchMenuItemBean {
    private static Long lastId=1L;

    private Long id=0L;
    private String description="";
    private Double price=0D;
    private Integer amount=0;

    public LunchMenuItemBean() {
        this.id = lastId++;
    }

    public LunchMenuItemBean(String description, Double price) {
        this();
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "LunchMenuItemBean{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LunchMenuItemBean that = (LunchMenuItemBean) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (!description.equals(that.description)) return false;
        if (!price.equals(that.price)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
