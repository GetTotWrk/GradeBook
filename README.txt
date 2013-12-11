Test Cases for GradeTracker:


Search database:
    - Only returns the assignments with specific course
    - Nothing happens when input string that is not found
    - When nothing found can return to seeing complete database view
Navigation:
    - buttons lead to eachother
        - when press cancel, 

For all "Save" buttons:
    - One user input missing
    - Two user inputs missing
    - User inputs wrong type
    - User inputs a negative number
    - User inputs a 0 --> important when finding averagages and weighted scores because of dividing by 0
    - user inputs a string or symbol when expecting
    - user inpus a n integer out of expected range
General performance:
    - Scroll fast between fragments
    - Reasonably fast startup time
Security:
    - App does not access any other files other than the database it creates

