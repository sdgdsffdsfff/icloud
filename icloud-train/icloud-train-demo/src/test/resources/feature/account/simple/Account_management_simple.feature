Feature: 转账业务
  Background: 初始化帐户系统
  Given 初始化帐户系统，以下是初始化数据
      | userId  | userName  | money    |
      | 1       | mark      | 60000    |
      | 2       | ball      | 62000    |
      | 3       | piglet    | 55000    |
      | 4       | mao       | 70000    |
      | 5       | jerry     | 56000    |
      | 6       | dannie    | 62000    |
      | 7       | okry      | 51000    |
      | 8       | maoz      | 66500    |
      
  Scenario Outline: 从帐户A给帐户B转账     
    When userId 为 <fromId>的帐户, 给 userId 为 <toId>的帐户转<money>元 
    Then userId 为 <fromId>的帐户的余额应该是<fromTotal>元，userId 为<toId>的余额应该是<toTotal>元
    
   Examples:
     | fromId  | toId    | money | fromTotal | toTotal  |
     | 1       |   2     |  2000 | 58000     |  64000   |
     | 3       |   4     |  1000 | 54000     |  71000   |
     | 5       |   6     |  3000 | 53000     |  65000   |