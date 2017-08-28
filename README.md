### Status
[![Build Status](https://travis-ci.org/astrashevskiy/MobileQA.png)](https://travis-ci.org/astrashevskiy/MobileQA)

## [Documentation](https://astrashevskiy.github.io/MobileQA/)

## [Test Plan](TestPlan.md)


Run tests with  
```mvn test```  
this will execute tests using SauceLabs device farm (one need to set environment variables SAUCE_USER and SAUCE_KEY with values from one's SauceLab account).  

In order to run test locally one need to comment/uncomment app property in [capabilities.properties](src/test/resources/capabilities.properties) and then execute:  
```mvn -Dorg.no.run.local=true test```  
  
To generate HTML Allure report execute:  
```mvn site```  
Report will be generated at "target/site/allure-maven-plugin"
