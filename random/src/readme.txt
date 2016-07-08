Key takeaways

1. Use table to record flags int[][][][]

2. Use bit operation to save memory

3. Use stack for flood fill

4. Define the right data structure for the problem

5. Collections.sort to sort a List of custom defined objects. Object can implement comparable interface "int compareTo(Object o)"

6. Max sum of contiguous sub array, file at MaxSumContinuousArray.java https://www.interviewbit.com/problems/max-sum-contiguous-subarray/. 
The algorithm is to find all the segments with positive sums, within each segment keep tracking the sub sums to update the overall max sum. 
Or there is another solution using dynamic programming in that file

7. Dynamic Programming is just a fancy way to say 'remembering stuff to save time later'".  Think of a recursive approach to solving the problem.

8. Similar problem to #6 is to find the max product of contiguous array. See the file MaxProductSubArray.java
   For example [0, -3, 2, -3, 0, 5, 6, 0]
   For each non-zero section, one max positive product and one max negative product need to be tracked
   If the next item in array is negative, use the max negative product to multiply. 
   The key technique here is to swap two maximums in this case. 