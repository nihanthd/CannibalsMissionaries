Language : Java
Tool : Eclipse
File to execute : CannibalsMissionaries.java in com.problem.solution package

For search space construction o/p format
first line - states possible from start state
second line - states possible from first valid state after start node. etc.

Similarly for the search space traversal - traversing only the valid states.

The result for running this program is given below.


States while search space construction : 
1 - 3 - 2 - 0 - 1    |||   2 - 2 - 1 - 1 - 1    |||   3 - 1 - 0 - 2 - 1    |||   
3 - 3 - 0 - 0 - 0    |||   2 - 3 - 1 - 0 - 0    |||   
3 - 3 - 0 - 0 - 0    |||   3 - 2 - 0 - 1 - 0    |||   2 - 3 - 1 - 0 - 0    |||   
0 - 3 - 3 - 0 - 1    |||   1 - 2 - 2 - 1 - 1    |||   2 - 1 - 1 - 2 - 1    |||   
2 - 3 - 1 - 0 - 0    |||   1 - 3 - 2 - 0 - 0    |||   
0 - 2 - 3 - 1 - 1    |||   1 - 1 - 2 - 2 - 1    |||   
3 - 1 - 0 - 2 - 0    |||   2 - 2 - 1 - 1 - 0    |||   1 - 3 - 2 - 0 - 0    |||   2 - 1 - 1 - 2 - 0    |||   1 - 2 - 2 - 1 - 0    |||   
0 - 2 - 3 - 1 - 1    |||   1 - 1 - 2 - 2 - 1    |||   2 - 0 - 1 - 3 - 1    |||   
3 - 1 - 0 - 2 - 0    |||   2 - 2 - 1 - 1 - 0    |||   3 - 0 - 0 - 3 - 0    |||   2 - 1 - 1 - 2 - 0    |||   
1 - 0 - 2 - 3 - 1    |||   
3 - 0 - 0 - 3 - 0    |||   2 - 1 - 1 - 2 - 0    |||   1 - 2 - 2 - 1 - 0    |||   2 - 0 - 1 - 3 - 0    |||   1 - 1 - 2 - 2 - 0    |||   
0 - 0 - 3 - 3 - 1    |||   
0 - 0 - 3 - 3 - 1    |||   
States while search space traversal using BFS
1 - 3 - 2 - 0 - 1    |||   2 - 2 - 1 - 1 - 1    |||   
2 - 3 - 1 - 0 - 0    |||   
0 - 3 - 3 - 0 - 1    |||   
1 - 3 - 2 - 0 - 0    |||   
1 - 1 - 2 - 2 - 1    |||   
2 - 2 - 1 - 1 - 0    |||   
2 - 0 - 1 - 3 - 1    |||   
3 - 0 - 0 - 3 - 0    |||   
1 - 0 - 2 - 3 - 1    |||   
2 - 0 - 1 - 3 - 0    |||   1 - 1 - 2 - 2 - 0    |||   
0 - 0 - 3 - 3 - 1    |||   
Cannibals and Missionaries reached the other shore.