Feature: Exchange Rates
  As a User
  I want to be able to have an automated testing framework available which covers different scenarios for the Rates API
  So that I can make sure that the API’s are fit for purpose in the use of the exchange rate for financial reasons


  Background:
    #Given Rates API for Latest Foreign Exchange rates


  @rates
  Scenario: Latest rate available
    When The API is available
    Then assert the success status of the response
    Then assert the response