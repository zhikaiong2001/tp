---
layout: default.md
title: "Yash's Project Portfolio Page"
---

### Project: Tether

Tether is a professional Applicant Tracking System (ATS) for hiring managers in small to mid range startups. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added status-tagging for applicants and interviewers
    * What it does: allows hiring managers to set and view applicant statuses (among resume review, pending interview, completed interview, waiting list, accepted, rejected) as well as view interviewer statuses (among free and interview with [applicant name]).
    * Justification: This feature improves the product significantly because hiring managers often need to view where applicants are in the hiring pipeline at any given time, and check interviewer availabilities for interviews at a glance.
    * Highlights: This enhancement required an in-depth weighing of cost/benefit to users such as evaluating whether restricting users from setting custom applicant statuses was sensible, or blocking users from setting interviewer statuses at all was acceptable. Implementation wise there were some challenges for stackable interviewer statuses, especially in storing and parsing multiple statuses.

* **New Feature**: Added a command to filter persons by status.
    * What it does: allows hiring managers to filter persons by valid applicant and interviewer statuses.
    * Justification: This feature is a minor enhancement to the product that allows users to group persons quickly by statuses for a quick overview. Moreover, this feature comes in as an alternative to `find`, should users know enough information to `find` persons by their details.

* **New Feature**: Added a command to view overall statistics of stored data in terms of total applicants, interviewers and interviewers (as well as breakdown numbers of applicants
* and interviewers by status).


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=yashpola&tabRepo=AY2324S2-CS2103T-F11-3/tp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Set up team repo, github pages, and repo settings.
    * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub.

* **Enhancements to existing features**:
    * Updated the GUI color scheme and touched up app layout (Pull requests [\#49](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/49/files), [\#121](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/121/files))
    * Created a new `Interviewer` type from existing `Person` to more closely model usage for hiring managers in particular

* **Documentation**:
    * User Guide:
        * Added documentation for the features `applicant_status`, `filter_by_status`, and `view_overall_statistics` [\#147](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/147)
        * Standardised descriptions for features, enhanced readability of descriptions, added more orientation sections to better introduce users to Tether: [\#246](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/246)
    * Developer Guide:
        * Added implementation details of the `applicant_status` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#120](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/120), [\#143](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/143), [\#122](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/122), [\#95](https://github.com/AY2324S2-CS2103T-F11-3/tp/pull/95)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2324S2/forum/issues/667), [2](https://github.com/nus-cs2103-AY2324S2/forum/issues/584), [3](https://github.com/nus-cs2103-AY2324S2/forum/issues/583), [4](https://github.com/nus-cs2103-AY2324S2/forum/issues/732))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S2-CS2103-F15-1/tp/issues/263), [2](https://github.com/AY2324S2-CS2103-F15-1/tp/issues/211), [3](https://github.com/AY2324S2-CS2103-F15-1/tp/issues/215))


