class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        // Store each intersection as an int[] like [start, end]
        ArrayList<int[]> res = new ArrayList<>();

        // Two pointers:
        // i -> current interval in firstList
        // j -> current interval in secondList
        int i = 0;
        int j = 0;

        // Since firstList and secondList are arrays, use .length
        int n = firstList.length;
        int m = secondList.length;

        // Process both lists while both pointers are within their lists
        while (i < n && j < m) {

            // Get the current interval from firstList
            int start1 = firstList[i][0];
            int end1 = firstList[i][1];

            // Get the current interval from secondList
            int start2 = secondList[j][0];
            int end2 = secondList[j][1];

            /*
             * Find the intersection of the two intervals.
             *
             * The intersection must:
             * - start at the later of the two starting points
             * - end at the earlier of the two ending points
             *
             * Example:
             * [1, 5]
             * [3, 7]
             *
             * start = max(1, 3) = 3
             * end   = min(5, 7) = 5
             *
             * Intersection = [3, 5]
             */
            int start = Math.max(start1, start2);
            int end = Math.min(end1, end2);

            // Since intervals are CLOSED, [5,5] is a valid intersection.
            // Therefore, we check start <= end.
            if (start <= end) {
                res.add(new int[]{start, end});
            }

            /*
             * Move the pointer whose interval ends first.
             *
             * Why?
             * The interval that ends first cannot intersect with any
             * future interval from the other list.
             */
            if (end1 <= end2) {
                i++;
            } else {
                j++;
            }
        }

        // Convert ArrayList<int[]> into int[][]
        return res.toArray(new int[res.size()][]);
    }
}

// ===================== REVISION NOTES =====================
//
// 1. Pattern:
//    Sorted + disjoint intervals -> Two Pointer approach.
//
// 2. Intersection formula:
//    start = max(start1, start2)
//    end   = min(end1, end2)
//
// 3. Valid intersection:
//    start <= end
//
//    IMPORTANT:
//    Because intervals are CLOSED, [5,5] is valid.
//
// 4. Pointer movement:
//    Move the interval that ends first.
//
//    if (end1 <= end2)
//        i++;
//    else
//        j++;
//
// 5. Why move the smaller end?
//    The interval that finishes first can no longer overlap with
//    future intervals from the other list.
//
// 6. Java:
//    Array      -> .length
//    ArrayList  -> .size()
//
// 7. Result:
//    ArrayList<int[]> is useful because every intersection is an
//    int[] containing [start, end].
//
// 8. Complexity:
//    Time  -> O(n + m)
//    Space -> O(n + m) for the result
//
// 9. Interview intuition:
//    "Compare the current intervals, calculate their overlap,
//     record it if valid, and move the interval that ends first."