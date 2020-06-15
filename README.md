## Description

### Execution

gradlew test

### Web

This module includes 25 test case for Web part.

1. Test Normal Process with Load Purpose: Business
2. Test Normal Process with Load Purpose: Home Improvement
3. Test Normal Process with Load Purpose: Large Purchase
4. Test Normal Process with Load Purpose: Pay off Credit Cards
5. Test Normal Process with Load Purpose: Debt Consolidation
6. Test Normal Process with Load Purpose: Other
7. Test Loan Amount Less 1000
8. Test Loan Amount More 35000
9. Test Loan Amount Empty
10. Test Missing Load Purpose
11. Test Missing Contact Info FirstName
12. Test Missing Contact Info LastName
13. Test Missing Contact Info Street
14. Test Missing Contact Info City
15. Test Missing Contact Info State
16. Test ZipCode Only Digital
17. Test Invalid ZipCode
18. Test Invalid Birthday Month
19. Test Invalid Birthday Day
20. Test BirthdayDay Before 1930
21. Test BirthdayDay After 2000
22. Test Age Less 18
23. Test Invalid Password
24. Test Invalid Email
25. Test Missing Agreement

#### API

This module includes 2 test cases for API part. One is 200, the other is 401.

#### Notes

1. Test 20 and 21 are Disabled.
Based on Instruction "DOB: <any valid date before 01/01/2000 and after 01/01/1930>".
Actually, we can input before 01/01/1930 or (after 01/01/2000 and age > 18).
2. Only test on Chrome, should expand to other Driver.







