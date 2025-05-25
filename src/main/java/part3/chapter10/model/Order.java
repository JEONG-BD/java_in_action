package part3.chapter10.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    @Override
    public String toString() {
        return "Order{" +
                "customer='" + customer + '\'' +
                ", trades=" + trades +
                '}';
    }

    private String customer;

    private List<Trade> trades = new ArrayList<>();

    public String getCustomer() {
        return customer;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void addTrade(Trade trade){
        this.trades.add(trade);
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }


    public double getValues(){
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }
}
