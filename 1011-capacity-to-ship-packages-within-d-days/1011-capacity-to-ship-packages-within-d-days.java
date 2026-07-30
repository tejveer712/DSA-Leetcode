class Solution {

    /**
     * Checks whether a ship with the given capacity can transport
     * all packages within the specified number of days.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    boolean canShip(int[] weights, int capacity, int days) {

        // We always start shipping on Day 1.
        int day = 1;

        // Current weight loaded on today's ship.
        int currentLoad = 0;

        // Process packages in the given order.
        // (Reordering is NOT allowed.)
        for (int weight : weights) {

            // If adding the current package exceeds the ship's capacity,
            // we cannot load it today.
            if (currentLoad + weight > capacity) {

                // Start a new day.
                day++;

                // The current package becomes the FIRST package of the new day.
                // (It is not skipped.)
                currentLoad = weight;

                // Optimization:
                // If we have already used more days than allowed,
                // this capacity is insufficient.
                if (day > days) {
                    return false;
                }

            } else {

                // Package fits on today's ship.
                // Keep loading today's ship greedily.
                currentLoad += weight;
            }
        }

        // All packages shipped within the allowed number of days.
        return true;
    }

    public int shipWithinDays(int[] weights, int days) {

        // -----------------------------
        // Binary Search on Answer
        // -----------------------------

        // Minimum possible capacity:
        // The ship must at least carry the heaviest package.
        int low = 0;
        for (int weight : weights) {
            low = Math.max(low, weight);
        }

        // Maximum possible capacity:
        // Ship carries all packages in one day.
        int high = 0;
        for (int weight : weights) {
            high += weight;
        }

        // Binary Search for the minimum valid capacity.
        while (low <= high) {

            // Candidate capacity.
            int mid = low + (high - low) / 2;

            if (canShip(weights, mid, days)) {

                // This capacity works.
                // Try finding a smaller valid capacity.
                high = mid - 1;

            } else {

                // Capacity is too small.
                // Increase the capacity.
                low = mid + 1;
            }
        }

        // At loop termination:
        // high = last invalid capacity
        // low  = first valid capacity
        return low;
    }
}


/*
What is my answer?

How do I check it?

Is the check monotonic?

What is the search space?

Am I finding the first valid or last valid answer?

Answer
------
Minimum ship capacity


Check
-----
Given a capacity,
simulate loading packages.
Return true if all packages are shipped within D days.


Monotonic
---------
Capacity

10 -> False
11 -> False
12 -> True
13 -> True
14 -> True


Search Space
------------
low  = max(weights)
high = sum(weights)


Binary Search
-------------
Need minimum valid capacity

Valid  -> search left
Invalid -> search right


Return
------
low
*/