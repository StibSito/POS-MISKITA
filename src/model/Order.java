package model;

public class Order {

    private String numOrder;
    private int idCliente;
    private String nomCli;
    private boolean delivery;
    private int state;

    public Order() {}

    public Order(String numOrder, int idCliente, String nomCli, boolean delivery, int state) {
        this.numOrder = numOrder;
        this.idCliente = idCliente;
        this.nomCli = nomCli;
        this.delivery = delivery;
        this.state = state;
    }

    public String getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(String numOrder) {
        this.numOrder = numOrder;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order [numOrder=" + numOrder + ", idCliente=" + idCliente + ", nomCli=" + nomCli + ", delivery=" + delivery + ", state=" + state + "]";
    }
}
