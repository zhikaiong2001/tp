---
layout: default.md
title: "Wesley's Project Portfolio Page"
---

### Project: Tether

Tether is a professional Applicant Tracking System (ATS) for hiring managers in small to mid-range startups. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Features**: Added scheduling of interviews
    * What it does: Allows hiring managers to schedule interviews between applicants and interviewers.
    * Justification: This feature is core requirement of our product, without it there would be no purpose of Tether.
    * Highlights: This feature required a massive decision between incorporating interviews directly into the persons list or creating an entire new interview list. We decided on creating a new interview list entirely such that it could be displayed immediately on the GUI.
    * Challenges: During implementation, there were many challenges in creating the interview list and ensuring it did not affect the persons list. There were also many GUI challenges due to the display of the interviews.

* **New Features**: Automatic sorting of interviews
    * What it does: Allows hiring managers to know which upcoming interviews are more urgent.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=headcube&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Facilitated delegation of tasks and managed team issue tracker.
    * Ensured timely submissions of required tasks.
  
* **Enhancements to existing features**:
    * Refactor GUI to better complement both the interview and persons list ([\#79](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/116))


* **Documentation**:
    * User Guide:
        * Added documentation for the features ```add_interview```, ```help```, ```add_applicant```, ```add_interviewer``` ([\#149](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/149), [\#240](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/240))
        * Updated Quick Start section with details and hyperlinked guides to facilitate setting up Tether. ([\#251](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/251))
    * Developer Guide:
        * Added implementation details of the ```add_interview``` feature. ([\#122](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/122))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#99](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/99), [\#46](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/46)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S2-CS2103T-T10-1/tp/issues/333), [2](https://github.com/AY2324S2-CS2103T-T10-1/tp/issues/360))


