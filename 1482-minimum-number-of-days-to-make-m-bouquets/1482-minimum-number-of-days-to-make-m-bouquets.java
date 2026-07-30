class Solution {

    // Verification Function
    // Returns true if it is possible to make at least 'm' bouquets
    // after waiting 'guess' days.
    boolean canMake(int[] bloomDay, int m, int k, int guess) {

        // Counts consecutive bloomed flowers
        int c = 0;

        // Counts bouquets formed
        int bouq = 0;

        // Traverse all flowers
        for (int flower : bloomDay) {

            // Flower has bloomed by 'guess' day
            if (guess >= flower) {

                c++;

                // We have enough adjacent flowers to make one bouquet
                if (c == k) {

                    bouq++;

                    // Early exit if required bouquets are formed
                    if (bouq >= m) {
                        return true;
                    }

                    // Reset because flowers are already used
                    c = 0;
                }

            } else {

                // Adjacency breaks if flower hasn't bloomed
                c = 0;
            }
        }

        // Return whether enough bouquets were formed
        return bouq >= m;
    }

    public int minDays(int[] bloomDay, int m, int k) {

        int len = bloomDay.length;

        // Search space:
        // Minimum possible answer = minimum bloom day
        // Maximum possible answer = maximum bloom day
        int min = bloomDay[0];
        int max = bloomDay[0];

        for (int day : bloomDay) {
            min = Math.min(min, day);
            max = Math.max(max, day);
        }

        // Total flowers required
        // Use long to avoid integer overflow
        long required = (long) m * k;

        if (required > len) {
            return -1;
        }

        // Binary Search on Answer
        while (min <= max) {

            int guess = min + (max - min) / 2;

            // If 'guess' days are enough,
            // search for an even smaller answer
            if (canMake(bloomDay, m, k, guess)) {
                max = guess - 1;
            }

            // Otherwise we need to wait longer
            else {
                min = guess + 1;
            }
        }

        // After binary search:
        // min -> first valid answer
        // max -> last invalid answer
        return min;
    }
}