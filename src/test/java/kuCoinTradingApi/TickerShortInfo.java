package kuCoinTradingApi;

public class TickerShortInfo {
    private final String name;
    private final Float changeRate;

    public TickerShortInfo(String name, Float changeRate) {
        this.name = name;
        this.changeRate = changeRate;
    }

    public String getName() {
        return name;
    }

    public Float getChangeRate() {
        return changeRate;
    }
}
