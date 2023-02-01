package ExchangeCurrencies;

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
            Currency destinationCurrency)
    {
        ExchangeType source = exchangeTypes.get(sourceCurrency);
        ExchangeType dest = exchangeTypes.get(destinationCurrency);
        return source.getSellPrice().multiply(dest.getBuyPrice()).multiply(amount).setScale(2, RoundingMode.FLOOR);
    }
}
