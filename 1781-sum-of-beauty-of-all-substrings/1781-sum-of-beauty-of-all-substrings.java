class Solution {
    public int beautySum(String s) {

        int n = s.length();
        int ans = 0;

        // Fix the starting index of the substring
        for (int start = 0; start < n; start++) {

            // Frequency array for the current starting index
            int[] freq = new int[26];

            // Expand the ending index
            for (int end = start; end < n; end++) {

                // Add the newly included character
                freq[s.charAt(end) - 'a']++;

                // Find the maximum and minimum non-zero frequencies
                int maxFreq = 0;
                int minFreq = Integer.MAX_VALUE;

                for (int f : freq) {
                    if (f > 0) {
                        maxFreq = Math.max(maxFreq, f);
                        minFreq = Math.min(minFreq, f);
                    }
                }

                // Add the beauty of the current substring
                ans += (maxFreq - minFreq);
            }
        }

        return ans;
    }
}

/*
==========================
Revision Notes
==========================

1. Pattern:
   - Fix the starting index.
   - Expand the ending index.
   - Maintain a running frequency array.

2. Why don't we rebuild the frequency array?
   - When extending a substring by one character,
     only one character's frequency changes.
   - Reusing the frequency array reduces unnecessary work.

3. Why scan all 26 characters every time?
   - We need both:
       • Maximum frequency
       • Minimum non-zero frequency
   - Only one frequency changes, but we cannot know whether
     the minimum changed without checking all present characters.
   - Since there are only 26 lowercase letters,
     scanning takes O(26) = O(1).

4. Time Complexity:
   - Outer loop: O(n)
   - Inner loop: O(n)
   - Frequency scan: O(26)
   - Overall: O(n² × 26) = O(n²)

5. Space Complexity:
   - O(26) = O(1)

6. Interview Clue:
   - Whenever the problem says:
       "lowercase English letters"
     think:
       → int[] freq = new int[26]
     instead of HashMap, because the alphabet size is fixed.

7. Common Mistakes:
   ❌ Forgetting to reset maxFreq and minFreq for each substring.
   ❌ Including frequency 0 when computing the minimum.
   ❌ Adding the beauty inside the frequency scan loop.
   ❌ Declaring freq outside the outer loop.
*/