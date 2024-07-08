# Created by Bilal TOPAL on 08.07.2024
# Description: This scenario is about verification of filtering section functions properly
@btc @smoke
Feature: Cryptocurrency Market Filtering
  As a user,
  I want to be able to search for BTC in the Markets section of Stablex website,
  So that I can quickly find information related to BTC trading.

  Background:
    Given the user navigates to the base URL

  Scenario Outline: Filter and Verify Specific Cryptocurrency
    When the user scrolls to the market
    And the '<coinAsGivenParameter>' coin is filtered using the search functionality in the market
    Then the number of coins displayed should be '<displayedNumberOfCoins>'
    And the user should see '<coinShouldAppear>' in the list
    #'<coinShouldAppear>' can also have multiple coins with configurations in relative method

    Examples:
      | coinAsGivenParameter | displayedNumberOfCoins | coinShouldAppear |
      | btc                  | 1                      | BTC              |
      | BTC                  | 1                      | BTC              |
      | b                    | 2                      | BTC              |
      | bt                   | 2                      | BTC              |
      | TC                   | 2                      | BTC              |
      | tc                   | 2                      | BTC              |
      |                      | 29                     | BTC              |



