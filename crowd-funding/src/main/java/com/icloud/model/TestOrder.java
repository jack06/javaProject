package com.icloud.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TestOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String transactionalid;

    private String ordernum;

    private String productname;

    private Integer count;

    private BigDecimal totalpay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionalid() {
        return transactionalid;
    }

    public void setTransactionalid(String transactionalid) {
        this.transactionalid = transactionalid == null ? null : transactionalid.trim();
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(BigDecimal totalpay) {
        this.totalpay = totalpay;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
    
    
}