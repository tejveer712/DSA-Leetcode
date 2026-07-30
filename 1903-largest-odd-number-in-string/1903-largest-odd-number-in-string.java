class Solution {
    public String largestOddNumber(String num) {

        // Traverse the string from right to left
        for (int i = num.length() - 1; i >= 0; i--) {

            // Convert the current character into its integer value
            int digit = num.charAt(i) - '0';

            // If the last digit is odd, the substring from 0 to i
            // forms the largest possible odd number
            if (digit % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }

        // No odd digit found, so no odd substring exists
        return "";
    }
}

/*
========================
Revision Notes
========================

Problem Pattern:
- String Traversal
- Greedy
- Observation-Based

Key Observation:
- A number is odd only if its last digit is odd.
- We need the largest-valued odd substring.

Why do we traverse from right to left?
- We want to remove as few digits as possible.
- The first odd digit encountered from the end gives the longest possible prefix ending at that digit.

Why return substring(0, i + 1)?
- Removing digits from the front always decreases the value of the number.
- To maximize the value, always keep the entire prefix and only trim the suffix.

Example:
num = "4206358"

Check from right:
8 -> even
5 -> odd

Return:
substring(0, 6) = "420635"

Time Complexity:
- O(n)
  (Single traversal from right to left)

Space Complexity:
- O(1)
  (Ignoring the returned substring)

Interview Trick:
Whenever the problem asks for:
- Largest-valued odd number
- Number represented as a string

Immediately think:
1. What determines odd/even? -> Last digit.
2. Can I avoid checking every substring? -> Yes.
3. Find the rightmost odd digit and return the prefix till that index.

Why is this Greedy?
- We greedily keep as many digits as possible.
- The first odd digit from the right already gives the maximum possible answer.
*/