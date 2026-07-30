class Solution {
    public int maxDepth(String s) {

        // Stores the maximum nesting depth encountered
        int maxDepth = 0;

        // Represents the current nesting level
        // (number of currently open parentheses)
        int level = 0;

        // Traverse each character of the string
        for (char ch : s.toCharArray()) {

            // Opening parenthesis increases the current depth
            if (ch == '(') {
                level++;

                // Update the maximum depth reached so far
                maxDepth = Math.max(maxDepth, level);
            }

            // Closing parenthesis decreases the current depth
            else if (ch == ')') {
                level--;
            }
        }

        // Return the deepest nesting level
        return maxDepth;
    }
}

/*
---------------------------------- Revision Notes ----------------------------------

Pattern:
- Balanced Parentheses
- Counter / Depth Tracking

Key Idea:
- '(': Enter a new level -> level++
- ')': Exit the current level -> level--
- The maximum value of 'level' during traversal is the answer.

Algorithm:
1. Initialize:
      level = 0
      maxDepth = 0
2. Traverse each character.
3. If '(':
      - Increment level.
      - Update maxDepth.
4. If ')':
      - Decrement level.
5. Return maxDepth.

Invariant:
- level always represents the number of currently open parentheses.

Why update maxDepth only on '('?
- The nesting depth increases only when entering a new pair of parentheses.
- ')' only reduces the current depth.

Time Complexity:
- O(n) : Single traversal of the string.

Space Complexity:
- O(1) : Only two integer variables are used.

Recognition Clues:
- "Maximum nesting"
- "Current depth"
- "Balanced parentheses"
- "How many parentheses are currently open?"

Whenever you see these clues, think:
-> Maintain a running depth/balance counter.
------------------------------------------------------------------------------------
*/