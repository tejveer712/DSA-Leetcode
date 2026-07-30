class Solution {
    public boolean rotateString(String s, String goal) {
        
        if (s.length() != goal.length()){
            return false;
        }

        // Concatenate the string with itself
        String doubledS = s + s;
        
        // Check if the goal is a substring of the concatenated string
        return doubledS.contains(goal);
        
    }
}

/*
------------------------------------ Revision Notes ------------------------------------

Brute Force:
1. Simulate every possible left rotation.
2. After each rotation, compare the new string with goal.
3. If any rotation matches, return true.
4. If all n rotations are checked without a match, return false.

Example:
abcde
↓
bcdea
↓
cdeab
↓
deabc
↓
eabcd

Time  : O(n²)
Space : O(n)

-----------------------------------------------------------------------------------------

Optimal Approach:

Observation:
A left rotation never changes the relative order of characters.
It only changes the starting position.

Example:
abcde
bcdea
cdeab
deabc
eabcd

Notice the order is always:
a → b → c → d → e

Trick:
Imagine the string is written in a circle.
Every rotation is simply reading the circle from a different starting point.

Instead of creating a circle, duplicate the string:

abcde + abcde
= abcdeabcde

Every possible rotation now appears as a contiguous substring.

Example:

abcdeabcde
^^^^^  -> abcde
 ^^^^^ -> bcdea
  ^^^^^-> cdeab
   ^^^^^-> deabc
    ^^^^^-> eabcd

Therefore:
goal is a valid rotation
⇔ goal is a substring of (s + s)

Always check lengths first because rotation never changes string length.

Time  : O(n)
Space : O(n)

Pattern Recognition:
Keywords like:
- rotate
- cyclic
- circular shift
- left/right rotation

→ Immediately think:
"Can I duplicate the string and search inside (s + s)?"

-----------------------------------------------------------------------------------------
*/