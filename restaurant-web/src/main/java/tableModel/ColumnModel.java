package tableModel;

import bean.LunchMenuItemBean;

import java.io.Serializable;

public class ColumnModel implements Serializable {

    private String header;

    public ColumnModel(String header){
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnModel that = (ColumnModel) o;

        if (!header.equals(that.header)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return header.hashCode();
    }
}
