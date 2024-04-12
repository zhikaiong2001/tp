---
layout: default.md
title: "Ryan's Project Portfolio Page"
---

### Project: Tether

Tether is a professional Applicant Tracking System (ATS) for hiring managers in small to mid range startups. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added find for applicants and interviewers
    * What it does: allows hiring managers to find applicants or interviewers using either their name, phone number or email.
    * Justification: This feature improves the product significantly because hiring managers often need to view a specific person's details from a long list of people.
    * Highlights: This enhancement required deciding between implementing 3 separate commands for finding based on the 3 different compulsory person detail fields and a singular command that takes in as input an additional parameter to denote the field to find by.
    * Challenges: During implementation, there were slight challenges in ensuring proper parsing of multiple inputs, as well as abstracting out common code to cut down on repeated code logic.

* **New Feature**: Added a command to add a remark to applicants and interviewers.
    * What it does: allows hiring managers to add remarks to persons.
    * Justification: This feature is a minor enhancement to the product that allows users to add a short description to the person of their choice.

* **New Feature**: Added a command to delete applicants and interviewers.

* **General Contribution**: Ensured that code and documentation adhered to the required checkstyle and formatting guidelines in earlier iterations of v1.1 to v1.3.



* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=macareonie&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Facilitated delegation of tasks and managed team issue tracker.


* **Enhancements to existing features**:
    * Refactoring the name of the product to Tether and changing the product icon. (Pull request [\#79](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/79))
    * Created a new ```Applicant``` type from existing ```Person``` to more closely model usage for hiring managers in particular.
    * Modified ```Delete``` command to select person to delete based on phone number instead of index.

* **Documentation**:
    * User Guide:
        * Added documentation for the features ```clear```, ```find```, ```list_persons``` and ```list_interviews```. ([\#143](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/143), [\#241](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/241))
        * Updated Quick Start section with details and hyperlinked guides to facilitate setting up Tether. ([\#251](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/251))
    * Developer Guide:
        * Added user stories, user profile, value proposition and glossary in first iteration.
        * Added implementation details of the ```find``` feature. ([\#103](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/103))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#97](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/97), [\#98](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/98), [\#101](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/101))
    * Contributed to forum discussions (example: [1](https://github.com/nus-cs2103-AY2324S2/forum/issues/109))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S2-CS2103T-T11-1/tp/issues/229), [2](https://github.com/AY2324S2-CS2103T-T11-1/tp/issues/231), [3](https://github.com/AY2324S2-CS2103T-T11-1/tp/issues/243), [4](https://github.com/AY2324S2-CS2103T-T11-1/tp/issues/247), [5](https://github.com/AY2324S2-CS2103T-F11-1/tp/issues/241))


