package part3.chapter10.model;

public class Stock {

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", market='" + market + '\'' +
                '}';
    }

    private String symbol;
    private String market;

    public String getSymbol() {
        return symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
