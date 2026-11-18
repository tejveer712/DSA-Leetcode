class Solution {

    // Returns the highest count in the frequency array,
    // i.e. the count of the most frequent character in the current window.
    // Scans all 256 slots every call: O(256).
    private int find(int[] a) {
        int maxc = -1;
        for (int i = 0; i < 256; i++) {
            maxc = Math.max(maxc, a[i]);
        }
        return maxc;
    }

    public int characterReplacement(String s, int k) {
        int n = s.length();

        // f[c] = how many times character c appears in the current window.
        // A char can be used as an index directly (its ASCII value).
        // Every slot starts at 0, so no getOrDefault is needed (unlike a HashMap).
        int[] f = new int[256];

        // low = left edge, high = right edge, res = longest valid window so far.
        // res could just start at 0.
        int low = 0, high = 0, res = Integer.MIN_VALUE;

        // KEY IDEA: keep the most frequent char and replace everything else.
        //   replacements needed = window length - count of most frequent char
        //   The window is valid while (replacements needed <= k).

        for (high = 0; high < n; high++) {

            // EXPAND: add the new character on the right.
            f[s.charAt(high)]++;

            int maxcnt = find(f);         // count of the majority char in the window
            int len = high - low + 1;     // current window size
            int diff = len - maxcnt;      // chars that must be replaced

            // SHRINK: needs more than k replacements -> invalid window.
            // Drop chars from the left until it's valid again.
            while (diff > k) {
                f[s.charAt(low)]--;       // remove the leftmost char from the counts
                low++;                    // move the left edge forward

                // Recompute everything for the smaller window
                maxcnt = find(f);
                len = high - low + 1;
                diff = len - maxcnt;
            }

            // The window [low, high] is valid here, so record its length.
            len = high - low + 1;
            res = Math.max(res, len);
        }

        return res;
    }
}

/*
 * Example: s = "AABABBA", k = 1  ->  answer 4  ("AABA" -> "AAAA")
 *
 * Time:  O(256 * n), because find() scans the full array each step (effectively O(n))
 * Space: O(1), fixed-size array
 *
 * Optional optimisations:
 *  1. Replace find(f) with: maxcnt = Math.max(maxcnt, f[s.charAt(high)]);
 *     (only the newly added char can raise the max)
 *  2. Never decrease maxcnt while shrinking. res only grows when maxcnt grows,
 *     so a stale maxcnt never gives a wrong answer. This lets 'while' become 'if'.
 */