class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        // Maximum possible eating speed.
        // Koko never needs to eat faster than the largest pile because
        // she can only eat from one pile in one hour.
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        // Minimum possible eating speed is 1 banana/hour.
        int low = 1;

        // Stores the smallest valid speed found so far.
        int ans = -1;

        // Binary Search on the ANSWER (eating speed k) not on array piles
        while (low <= high) {

            // Guess the eating speed.
            int k = low + (high - low) / 2;

            // Calculate total hours required if Koko eats at speed k.
            long totalHours = 0;

            for (int pile : piles) {

                // Hours needed for one pile.
                // ceil(pile / k) using integer arithmetic.
                totalHours += (pile + k - 1) / k;
            }

            // If Koko can finish within h hours,
            // this speed is valid.
            if (totalHours <= h) {

                // Store current answer.
                ans = k;

                // Try to find an even smaller valid speed.
                high = k - 1;
            } else {

                // Too slow.
                // Need to increase the eating speed.
                low = k + 1;
            }
        }

        // Smallest valid speed.
        return ans;
    }
}