// Solution 1: Horizontal Scanning
// class Solution {
//     public String longestCommonPrefix(String[] strs) {

//         // Assume the first string is the common prefix.
//         String prefix = strs[0];

//         // Compare the prefix with every remaining string.
//         for (int i = 1; i < strs.length; i++) {

//             // Keep shrinking the prefix until the current string starts with it.
//             while (!strs[i].startsWith(prefix)) {

//                 // Remove the last character from the prefix.
//                 prefix = prefix.substring(0, prefix.length() - 1);

//                 // If prefix becomes empty, there is no common prefix.
//                 if (prefix.isEmpty()) {
//                     return "";
//                 }
//             }
//         }

//         // Prefix common to all strings.
//         return prefix;
//     }
// }

/*
APPROACH : Horizontal Scanning

I assume the first string is the maximum possible prefix. Then I compare it with every other string. 
If a string doesn't start with the current prefix, I repeatedly remove the last character until it matches. 
Since the prefix only shrinks, the remaining prefix is guaranteed to be the longest common prefix.

Intuition:
- Assume the first string is the common prefix.
- Compare it with every remaining string.
- If a string doesn't start with the prefix,
  keep removing the last character.
- Stop when every string starts with the prefix.

Algorithm:
1. prefix = strs[0]
2. Traverse remaining strings.
3. While current string doesn't start with prefix:
      - Remove last character.
      - If prefix becomes empty, return "".
4. Return prefix.

Time Complexity : O(n × m)
Space Complexity: O(1)

Recognition Pattern:
- Find common prefix among multiple strings.
- Start with a candidate answer.
- Gradually shrink the answer until it satisfies all inputs.
*/


//Solution 2: Vertical Scanning
class Solution {
    public String longestCommonPrefix(String[] strs) {

        // Compare every character of the first string.
        for (int i = 0; i < strs[0].length(); i++) {

            char currentChar = strs[0].charAt(i);

            // Compare this character with every other string.
            for (int j = 1; j < strs.length; j++) {

                // If current string ends
                // OR characters don't match,
                // return prefix till previous character.
                if (i == strs[j].length() ||
                    strs[j].charAt(i) != currentChar) {

                    return strs[0].substring(0, i);
                }
            }
        }

        // Entire first string is common.
        return strs[0];
    }
}


/*
APPROACH : Vertical Scanning

Instead of shrinking a candidate prefix, I compare characters column by column. 
Every character in the common prefix must match across all strings. 
The first mismatch or the end of any string tells me exactly where the common prefix ends, 
so I return the substring up to that point.

Intuition:
- Compare one character at a time.
- Use the first string as reference.
- Compare each column across every string.
- Stop immediately on first mismatch.

Algorithm:
1. Traverse every character of first string.
2. Compare this character with every other string.
3. If
      - string ends
      OR
      - character differs
   return substring till previous index.
4. If no mismatch occurs,
   return first string.

Time Complexity : O(n × m)
Space Complexity: O(1)

Recognition Pattern:
- Character-by-character comparison.
- Stop at first mismatch.
- Avoid repeatedly shrinking prefixes.
*/