# Test Plan

## References
* Learning path: [QA ENGINEER TO MOBILE QA ENGINEER](https://gridu.litmos.com/home/LearningPath/23741)
* Practice task: [Certification Test](http://gridu.litmos.com/course/508045/module/2101855?moduletoken=H-YKGpQy3bwsRsHiNxO2DS1QedFUCNBPXYSl34saW2jjjdbPTRuj8zWGMSIkw33O&LPId=23741)
* Application under test: [Advanced Offline Dictionary](https://drive.google.com/open?id=0B-5x4dReNwDUZ3NBUzBDYm5LMzQ)

## Introduction
Present Test Plan is intended to describe testing of chosen for Practical task mobile application.

## Items Under Test
Application under test is simple Android application [Advanced Offline Dictionary](https://drive.google.com/open?id=0B-5x4dReNwDUZ3NBUzBDYm5LMzQ) as a simple offline dictionary.

## Testing Approach
Since no formal functional requirements are available test cases are designed using everyday experience of average user. Test scope will be limited to a number of Happy Path scenarios.

## Test Cases
|Test Case|Description|
|---      |---        |
|T01basicSearchTest|Check Basic search functionality with default settings|
|T02exactSearchTest|Check Basic search functionality with Search Type setting set to Exact|
|T03anySubstringSearchTest|Check Basic search functionality with Search Type setting set to Any|
|T04fullSearchBasicTest|Check Full search functionality with Search Type setting set to Word|
|T05fullSearchExactTest|Check Full search functionality with Search Type setting set to Exact|
|T06fullSearchAnyTest|Check Full search functionality with Search Type setting set to Any|
|T07clearSearchBarTest|Check Basic Search Bar behavior|
|T08clearSearchResultsTest|Check Search Results clearing|
|T09AddToFavouritesTest|Check adding to favourites|
|T10RemoveFromFavouritesTest|Check removing from favourites|
