# twino-test-task

## Overview

### Part1

Create RESTful web-service for issuing loans.

Workflow is as follows: System operator adds a loan application, system scores the application, decides if loan should be issued and returns the result to the operator.

LoanApplication when created consists of

* Personal ID
* First Name
* Last Name
* Birth Date
* Employer (as a String)
* Salary (as decimal amount)
* Monthly Liability - sum of all liability payments per month (as decimal amount)
* Requested Amount (as decimal amount)
* Requested Term (days or months)

There should be ability to **List**, **Create** and **Delete** applications, to **Mark** applications as *Approved*, *Manual* or *Rejected* and to assign credit score to the application.

When applications are listed there should be a possibility to specify sorting field and order (ASC/DESC).

### Scoring

Each application can be assigned a score which is calculated as follows:

`Score = (Sum of first name letter positions in the alphabet (a = 1, z = 26)) + Salary * 1.5 - MonthlyLiability * 3 + (year of birth) - (month of birth) - (Julian day of the year of birth (1st Feb = 32, etc.))`

When scoring is applied, if score is < 2500, application should be rejected, if it is > 3500, application should be approved, otherwise marked as manual.

### Part 2
Create front-end for the API described in Part 1

### Part 3
**Security stuff**
1. registration of operators
2. ability for clients to register and independently ask for a loan without contacting operator.
In this case loan application should still be visible for operators for them to be able to approve or reject it in case of *Manual* scoring result.

## Technologies

Language: Java or Kotlin (only use Kotlin if you have experience and are comfortable with it)

RDBMS: any embedded SQL database via JPA

Project Management: Gradle or Maven

Tests: JUnit Jupiter (JUnit 5) or Spock

Framework: Spring Boot

Front-end: HTML5 + JavaScript, Angular or AngularJS is preferred, please also use some CSS framework.

## Other Requirements

Solution should be submitted as a link to a bitbucket or github repository which has this file as a README.

Solution should be self contained and should not need anything aside from JDK and git to run on either Linux or Windows.

The solution should be submitted before the deadline and it is okay to send it incomplete, just make it runnable and implement as many requirements as you can within the allocated time.
