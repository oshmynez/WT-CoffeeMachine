package model;

public class Order {
    public String NameCoffee;
    public String NameBeans;
    public String NameAdditives;
    public String StatusOrder;

    public Order(){

    }
    public String getStatusOrder() {
        return StatusOrder;
    }

    public String getAdditives() {
        return NameAdditives;
    }

    public String getNameBeans() {
        return NameBeans;
    }

    public String getNameCoffee() {
        return NameCoffee;
    }

    public void setAdditives(String additives) {
        this.NameAdditives = additives;
    }

    public void setNameBeans(String nameBeans) {
        NameBeans = nameBeans;
    }

    public void setNameCoffee(String nameCoffee) {
        NameCoffee = nameCoffee;
    }

    public void setStatusOrder(String statusOrder) {
        StatusOrder = statusOrder;
    }

}
