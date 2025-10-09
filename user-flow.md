## High Level Design

```markdown
UI (Home / Add / History)
  ↓
ViewModel
  ↓
UseCase (AddCommitment / ObserveCommitmentsForDay)
  ↓
Repositories (CommitmentRepository, ScheduleRepository)
  ↓
DataStore (Local DB)
```

## Use Cases

### 1 - Create a Commitment
1. User Taps "Add Commitment"
2. Add Commitment Screen Opens
3. User Enters the details
    1. Date Range (Time + Day + Repeat) (Schedule)
        1. Daily
        2. Weekly (S, M, T, W, T, F, S)
        3. Monthly
        4. Yearly
        5. Ends
            1. Never
            2. On <Date>
            3. After (N) Occurrence
    2. Title/Description
    3. Approximate Time Commitment (Most likely should be entered before Date Range)
    4. Sub Tasks
        1. One liner text description
        2. Time (Schedule)
            1. (eg. 10 min/hour) From <Time> to <Time>
            2. Only duration eg 10m

#### Design
1. ViewModel -> Create Commitment Domain object
2. AddCommitment(c: Commitment) -> CommitmentRepository#AddCommitment
3. CommitmentRepository#AddCommitment 
   1. CommitmentDataStore -> AddCommitment
   2. CommitmentScheduleDataStore -> AddSchedule (There can be multiple schedules)
   3. SubTasksDataStore -> Add/Remove/Edit (There can be multiple subtasks)
   4. SubtasksScheduleDataStore -> Schedule for subtasks

### 2 - See a Commitment
1. Navigation to commitment screen
2. Load Today's Commitments
   1. CommitmentRepository#observeCommitments(today)
3. Load Future Commitments
   1. CommitmentRepository#observeCommitments(fromDate, toDate) (Dates are inclusive)

### 3 - Commitment State
1. Future Commitment 
   1. Most of them should not have any State
   2. It is possible that I might start/partially complete / or complete one of the sub tasks
   3. CommitmentWorkLogDataStore -> Store any progress in the log data store
   4. When Retrieving a commitment retrieve the work session to get the state. 

---

## Gradle Modules
```markdown
:app
:libs
    |-- :logger
    |-- :design-system
:screens
    |-- :save-commitments
    |-- :view-commitments
:features
    |-- :pomodoro
:core
    |-- :data
    |-- :domain
    |-- :database

```



---

### Models:
- Commitment
- Commitment Schedule
- ScheduleType
  - Once
  - Repeated
    - Daily, Weekly, Monthly, Yearly
    - Ends
      - Never, On <Date>, N Occurrence
- Commitment Status
- CommitmentAction or Task ?
  (Action-on/Task-For a commitment for a given WorkSession of scheduled commitment)

---

### Open Questions:
1. How to handle traveling - timezone changes ?
2. How to handle commitments - that failed ?
3. Commitment vs Sub Tasks ?








