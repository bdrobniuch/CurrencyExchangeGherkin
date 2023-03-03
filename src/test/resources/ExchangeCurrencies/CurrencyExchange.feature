Feature: Currency Exchange. Our client can decide to sell or buy currencies.

  Background:
    Given the following exchange rate for EUR is
      | currency | sell      | buy        |
      | USD      | 0.91783   | 0.91796    |
      | JPY      | 0.00706   | 0.00706    |
      | PLN      | 0.21205   | 0.21221    |
      | GBP      | 1.13311   | 1.13335    |
      | RUB      | 0.01327   | 0.01329    |

  Rule: Our client can chose to define both the amount from the source account as well as the amount for the destination account.
    Example: where Cindy chooses the source amount
      When Cindy exchanges 1000 USD to PLN
      Then She gets 4325.10 PLN
    Example: where Cindy chooses the destination amount
      When Cindy exchanges USD to 1000 PLN
      Then She pays 231.21 USD
    Example: where Cindy chooses the destination amount
      When Cindy exchanges USD to 4325.10 PLN
      Then She pays 1000 USD

  Rule: if there is no account with chosen currency assume to create the account  with a cleared account balance
