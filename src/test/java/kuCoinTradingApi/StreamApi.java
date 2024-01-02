package kuCoinTradingApi;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
public class StreamApi {
    public List<TickerData> getTickers() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then().log().body()
                .extract().jsonPath().getList("data.ticker", TickerData.class);
    }
    @Test
    public void checkCrypto() {
        List<TickerData> usdTickers = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).collect(Collectors.toList());
        Assertions.assertTrue(usdTickers.stream().allMatch(x->x.getSymbol().endsWith("USDT")));
    }
    @Test
    public void sortCryptoFromHighToLow() {
        List<TickerData> highToLow = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).sorted(new Comparator<TickerData>() {
            @Override
            public int compare(TickerData o1, TickerData o2) {
                return o2.getChangeRate().compareTo(o1.getChangeRate());
            }
        }).collect(Collectors.toList());

        List<TickerData> top10Crypto = highToLow.stream().limit(10).collect(Collectors.toList());
        Assertions.assertEquals(top10Crypto.get(0).getSymbol(), "TRBDOWN-USDT");
    }
    @Test
    public void sortCryptoFromLowToHigh() {
        List<TickerData> lowToHigh = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted(new TickerComparatorLow()).limit(10).collect(Collectors.toList());
    }
    @Test
    public void map(){
        //List<String> lowerCase = getTickers().stream().map(x->x.getSymbol().toLowerCase()).collect(Collectors.toList());
        Map<String, Float> usd = new HashMap<>();
        //getTickers().forEach(x->usd.put(x.getSymbol(), Float.parseFloat(x.getChangeRate())));
        List<TickerShortInfo> shortInfoList = new ArrayList<>();
        getTickers().forEach(x->shortInfoList.add(new TickerShortInfo(x.getSymbol(), Float.parseFloat(x.getChangeRate()))));
    }


}

