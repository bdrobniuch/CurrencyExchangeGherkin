package ExchangeCurrencies;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CurrencyExchangeStepDef{

    private Map<Currency, ExchangeType> exchangeTypes;
    private BigDecimal result;

    @DataTableType
    public Map.Entry<Currency, ExchangeType> authorEntryTransformer(Map<String, String> entry) {
        return new AbstractMap.SimpleEntry<Currency, ExchangeType> (Currency.valueOf(entry.get("currency")),
                new ExchangeType(new BigDecimal (entry.get("buy")),
                        new BigDecimal (entry.get("sell"))));
    }

    @ParameterType("USD|GBP|RUB|PLN")
    public Currency currency(String unformatCurrency)
    {
        return Currency.valueOf(unformatCurrency);
    }


    @Given("the following exchange rate for EUR is")
    public void the_following_exchange_rate_for_EUR_is(List<Map.Entry<Currency, ExchangeType>> entries) {
        this.exchangeTypes = entries.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @When("Cindy exchanges {double} {currency} to {currency}")
    public void actor_exchanges_currency_and_chooses_the_source_amount(Double amountToChange, Currency sourceCurrency, Currency destinationCurrency) {
        // Write code here that turns the phrase above into concrete actions
        result = CurrencyExchange.execute(BigDecimal.valueOf(amountToChange), this.exchangeTypes, sourceCurrency, destinationCurrency, true);
    }

    @Then("She gets {double} {currency}")
    public void actor_gets(Double amountExpected, Currency destinationCurrency) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(BigDecimal.valueOf(amountExpected).setScale(2, RoundingMode.FLOOR), result);
    }

    @When("Cindy exchanges {currency} to {double} {currency}")
    public void actor_exchanges_currency_and_chooses_the_destination_amount(Currency sourceCurrency, Double amountToChange, Currency destinationCurrency) {
        // Write code here that turns the phrase above into concrete actions
        result = CurrencyExchange.execute(BigDecimal.valueOf(amountToChange), this.exchangeTypes, sourceCurrency, destinationCurrency, false);
    }

    @Then("She pays {double} {currency}")
    public void actor_pays(Double amountExpected, Currency sourceCurrency) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(BigDecimal.valueOf(amountExpected).setScale(2, RoundingMode.FLOOR), result);
    }
}


