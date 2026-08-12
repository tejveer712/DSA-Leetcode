class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;

        // At most n + 1 intervals can be present in the result
        int[][] res = new int[n + 1][2];
        int idx = 0;

        int tstart = newInterval[0];
        int tend = newInterval[1];

        for (int i = 0; i < n; i++) {

            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            // Case 1: Current interval is completely BEFORE newInterval
            if (currEnd < tstart) {
                res[idx][0] = currStart;
                res[idx][1] = currEnd;
                idx++;
            }

            // Case 2: Current interval is completely AFTER newInterval
            else if (currStart > tend) {

                // First add the merged newInterval
                res[idx][0] = tstart;
                res[idx][1] = tend;
                idx++;

                // Then add the current interval
                res[idx][0] = currStart;
                res[idx][1] = currEnd;
                idx++;

                // From here onward, all remaining intervals
                // will also be after newInterval
                for (int j = i + 1; j < n; j++) {
                    res[idx][0] = intervals[j][0];
                    res[idx][1] = intervals[j][1];
                    idx++;
                }

                // newInterval has been inserted, so we're done
                return Arrays.copyOf(res, idx);
            }

            // Case 3: Current interval OVERLAPS newInterval
            else {
                // Extend newInterval to include the current interval
                tstart = Math.min(tstart, currStart);
                tend = Math.max(tend, currEnd);
            }
        }

        // If newInterval was never added,
        // it belongs at the end
        res[idx][0] = tstart;
        res[idx][1] = tend;
        idx++;

        return Arrays.copyOf(res, idx);
    }
}

/*
Revision Notes:

1. LC 57 can be divided into 3 cases:

   a) Current interval is BEFORE newInterval:
      currEnd < tstart
      → Add current interval directly to result.

   b) Current interval is AFTER newInterval:
      currStart > tend
      → Add newInterval first.
      → Then add current interval and all remaining intervals.
      → We can return because all remaining intervals are also after it.

   c) Current interval OVERLAPS newInterval:
      → Merge it into newInterval.
      → tstart = min(tstart, currStart)
      → tend   = max(tend, currEnd)

2. Important insight:
   We don't physically insert newInterval into the input array.
   We treat newInterval as the interval currently being merged.

3. The overlap condition is essentially:
      currEnd >= tstart && currStart <= tend

4. Since intervals are already sorted by start time,
   once we encounter an interval after newInterval,
   every interval after it will also be after newInterval.

5. Time Complexity: O(n)
   Space Complexity: O(n) for the result array.
*/