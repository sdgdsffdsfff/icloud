@manageFeature
Feature: employee Management

  Background: init the management system
    Given the employee management system is initialized with the following data
      | id  | user      | salary   |
      | 1   | mark      | 60000.0  |
      | 2   | ball      | 62000.0  |
      | 3   | piglet    | 55000.0  |
      | 4   | mao       | 70000.0  |
      | 5   | jerry     | 56000.0  |
      | 6   | dannie    | 62000.0  |
      | 7   | okry      | 51000.0  |
      | 8   | maoz      | 66500.0  |
 
  Scenario Outline: Modify an employee's salary
    When the boss increases the salary for the employee with id <id> by <withdrawal>% 
    Then the payroll for the employee with id <id> should display a salary of <remaining>
    
   Examples:
     | id  | salary      | withdrawal   | remaining |
     | 1   | 60000.0     |   5			| 63000     |
     | 2   | 62000.0     |   3			| 63860     |
        