package model;

public class OrderDetail {

    private String numOrder;
    private String product;
    private int quantity;
    private double total;

    public OrderDetail() {}

    public OrderDetail(String numOrder, String product, int quantity, double total) {
        this.numOrder = numOrder;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }

    public String getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(String numOrder) {
        this.numOrder = numOrder;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail [numOrder=" + numOrder + ", product=" + product + ", quantity=" + quantity + ", total=" + total + "]";
    }
}

	
   

