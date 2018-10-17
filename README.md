# Firewall

## Tested solution using simple JUNIT test cases.
    Verified all the test cases given in document, all the test cases are satisfied.
    Execution Steps :

    To execute test cases
        mvn test

    To build and install
        mvn clean install

## Desing:
    Maintained cache of all rules for quick lookup.
    Accept packet would work in O(n) complexity where n is number of rules.


## Further Refinements:
    Given we are building only accept packet, we can sort rules based on wider port range and ip addresses to make searching faster.
    Could have tested for larger datasets.

## Preference for teams
    My order of preference would be
    1) Data Team
    2) Policy Team
    3) Platform Team