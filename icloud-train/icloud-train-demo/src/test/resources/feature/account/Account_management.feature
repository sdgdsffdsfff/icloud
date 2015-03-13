@AccountFeature
Feature: AccountFeature

  Background: init the account management system
    Given the account management system is initialized with the following data
      | userId  | userName  | money    |
      | 1       | mark      | 60000    |
      | 2       | ball      | 62000    |
      | 3       | piglet    | 55000    |
      | 4       | mao       | 70000    |
      | 5       | jerry     | 56000    |
      | 6       | dannie    | 62000    |
      | 7       | okry      | 51000    |
      | 8       | maoz      | 66500    |
      
 
  Scenario Outline: transfer
    When account id <fromId> transfer <money> to account id <toId> 
    Then account id <fromId>'s money is <fromTotal>, account id <toId>'s money is <toTotal>
    
   Examples:
     | fromId  | toId    | money | fromTotal | toTotal  |
     | 1       |   2     |  2000 | 58000     |  64000   |
     | 3       |   4     |  1000 | 54000     |  71000   |
     | 5       |   6     |  3000 | 53000     |  65000   |
     
        