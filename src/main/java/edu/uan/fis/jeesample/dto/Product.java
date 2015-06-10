package edu.uan.fis.jeesample.dto;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private Integer productId=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Product == false)
            return false;
        Product that = (Product) o;
        return that.productId.equals(this.productId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 79 * hash + (this.productId != null ? this.productId.hashCode() : 0);
        return hash;
    }
}
