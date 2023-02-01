import ExchangeCurrencies.Currency;
import ExchangeCurrencies.ExchangeType;
import ExchangeCurrencies.CurrencyExchange;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
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

    @ParameterType("USD|GBP")
    public Currency currency(String unformatCurrency)
    {
        return Currency.valueOf(unformatCurrency);
    }


    @Given("the following exchange rate for EUR is")
    public void the_following_exchange_rate_for_EUR_is(List<Map.Entry<Currency, ExchangeType>> entries) {
        this.exchangeTypes = entries.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @When("the client exhange {double} {currency} to {currency}")
    public void the_client_exhange_USD_to_GBP(Double amountToChange, Currency sourceCurrency, Currency destinationCurrency) {
        // Write code here that turns the phrase above into concrete actions
        result = CurrencyExchange.execute(BigDecimal.valueOf(amountToChange), this.exchangeTypes, sourceCurrency, destinationCurrency);
    }

    @Then("the outcome amount will be {double}")
    public void the_outcome_amount_will_be(Double amountExpected) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(BigDecimal.valueOf(amountExpected), result);
    }
}


