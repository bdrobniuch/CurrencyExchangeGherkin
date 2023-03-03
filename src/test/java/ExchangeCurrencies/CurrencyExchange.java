package ExchangeCurrencies;

import java.math.MathContext;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import ExchangeCurrencies.ExchangeType;
import java.lang.Double;

public class CurrencyExchange {

    public static BigDecimal execute(
            BigDecimal amount,
            Map<Currency, ExchangeType> exchangeTypes,
            Currency sourceCurrency,
            Currency destinationCurrency,
            boolean isOrigin)
    {
        ExchangeType source = exchangeTypes.get(sourceCurrency);
        ExchangeType dest = exchangeTypes.get(destinationCurrency);
        if(isOrigin) {
            return amount.multiply(source.getSellPrice().divide(dest.getBuyPrice(), MathContext.DECIMAL128)).setScale(2, RoundingMode.FLOOR);
        }
        return amount.divide(source.getSellPrice().divide(dest.getBuyPrice(), MathContext.DECIMAL128), MathContext.DECIMAL128).setScale(2, RoundingMode.UP);

        // source sell price 0.91783
        // destination buy price 0.21221
        // 1000 USD * (0.91783 USD / 0.21221 PLN) = 4325.10

        // source buy price 0.91796 USD
        // destination sell price 0.21205 PLN
        // 1000 PLN / (0.91796 USD / 0.21205 PLN) = 231,00 USD
    }
}
