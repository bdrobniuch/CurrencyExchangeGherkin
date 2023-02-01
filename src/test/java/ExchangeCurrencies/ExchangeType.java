package ExchangeCurrencies;

import java.math.BigDecimal;

public class ExchangeType {

    private final BigDecimal buyPrice;
    private final BigDecimal sellPrice;


    public ExchangeType(BigDecimal buyPrice, BigDecimal sellPrice) {
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }
}