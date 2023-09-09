## About
OrangeHRM

OrangeHRM WebPage Automation
Here we are executing Immigration module of OrangeHRM web page.
All the possibilities of Add, edit, delete functions of both immigrations and attachments are done.

## Concepts Included

* Sequential test runs
* Dependency injection
* Page Object pattern
* Common web page interaction methods
* Commonly used test utility classes

## Tools

* Maven
* JRE- 1.7
* TestNG
* Eclipse
* WebDriverManager
* Apache POI
* Selenium

## Requirements

In order to utilise this project you need to have the following installed locally:

* Eclipse
* Chrome Browser - V114
* Java SE-17
* Files Data.xlsx, Exp.ppt, PageObject.rtf should be dowloaded to your local drive.
* Added dependent files under ExcelData.
* Files named Data.xlsx, Exp.xlsx, PageObject.rtf must be downloaded in the local drive.
* The filePath variable must hold the local drives path when test cases with test priority 26,28,29,32, 35 are executed.


## Usage

The project is broken into separate modules for  UI .
Use Eclipse to run all modules

## Reporting

Reports for each module are written into their respective `/target` directories after a successful run.
file:///C:/Users/harsh/eclipse-workspace/MyInfo_Immigration/test-output/passed.png
UI acceptance tests result in a HTML report.
In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the report.
