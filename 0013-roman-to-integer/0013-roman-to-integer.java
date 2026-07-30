import java.util.HashMap;

class Solution {
    public int romanToInt(String s) {

        // Map each Roman numeral to its corresponding integer value.
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // Stores the final integer value.
        int ans = 0;

        // Traverse the Roman numeral from left to right.
        for (int i = 0; i < s.length(); i++) {

            // Current Roman numeral value.
            int curr = map.get(s.charAt(i));

            // If there is a next character, compare the two values.
            if (i < s.length() - 1) {

                int next = map.get(s.charAt(i + 1));

                // If current value is smaller than the next value,
                // it represents subtraction (e.g., IV = 4, IX = 9).
                if (curr < next) {
                    ans -= curr;
                }
                // Otherwise, add the current value.
                else {
                    ans += curr;
                }
            }
            // Last character is always added because there is
            // no next character to compare with.
            else {
                ans += curr;
            }
        }

        return ans;
    }
}

/*
========================================
Revision Notes
========================================

Pattern:
--------
Character Mapping + Single Traversal

Recognition Clues:
------------------
1. The problem contains a fixed set of symbols with predefined values.
2. The current character's contribution depends on the next character.
3. We only need one pass through the string.

Core Observation:
-----------------
If current value < next value:
    Subtract current value.

Otherwise:
    Add current value.

The next value is ONLY used for comparison.
It is never directly added or subtracted because it will be processed
in its own iteration.

Algorithm:
----------
1. Store each Roman numeral and its integer value.
2. Traverse the string from left to right.
3. Get the current numeral's value.
4. If there is a next character:
      - If current < next → subtract current.
      - Otherwise → add current.
5. If it is the last character, always add it.
6. Return the final answer.

Dry Run (MCMXCIV):
------------------
M  -> +1000
C  -> -100
M  -> +1000
X  -> -10
C  -> +100
I  -> -1
V  -> +5

Answer = 1994

Time Complexity:
----------------
O(n)
- We traverse the string exactly once.

Space Complexity:
-----------------
O(1)
- The HashMap stores only 7 fixed Roman numerals, so the extra space
  does not grow with the input size.

Interview Tip:
--------------
Ask yourself:
"Does the current element's contribution depend only on the next element?"

If yes, compare with the next element and decide whether the current
element should be added or subtracted.

Possible Follow-up:
-------------------
Since there are only 7 Roman numerals, this solution can be optimized
by replacing the HashMap with a switch statement or a helper method
getValue(char c), avoiding the HashMap entirely while keeping the
same O(n) time complexity.
*/