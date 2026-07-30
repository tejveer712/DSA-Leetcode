/* Optimised solution which utilies the ascii tric 
// Method to check if two strings are isomorphic
      public boolean isomorphicString(String s, String t) {
          // Arrays to track last seen positions of characters in s and t
          int[] m1 = new int[256], m2 = new int[256];
  
          // Get length of the strings
          int n = s.length();
  
          // Loop through all characters in the strings
          for (int i = 0; i < n; ++i) {
              // Return false if mapping is inconsistent
              if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
  
              // Update last seen index for both characters
              m1[s.charAt(i)] = i + 1;
              m2[t.charAt(i)] = i + 1;
          }
  
          // Return true if all character mappings are consistent
          return true;
      }

*/
//My own intutive solution 
class Solution {
    public boolean isIsomorphic(String s, String t) {

        // Stores mapping: character in s -> character in t
        Map<Character, Character> map = new HashMap<>();

        // Stores characters in t that are already mapped
        Set<Character> used = new HashSet<>();

        int len = s.length();

        // Traverse both strings together
        for (int i = 0; i < len; i++) {

            char sc = s.charAt(i);
            char tc = t.charAt(i);

            // Case 1: Character from s has already been mapped
            if (map.containsKey(sc)) {

                // Existing mapping must match the current character
                if (map.get(sc) != tc) {
                    return false;
                }

            } 
            // Case 2: First occurrence of sc
            else {

                // tc is already mapped to some other character
                if (used.contains(tc)) {
                    return false;
                }

                // Create a new mapping
                map.put(sc, tc);
                used.add(tc);
            }
        }

        return true;
    }
}

/*
========================
Revision Notes
========================

Pattern:
- Character Mapping
- HashMap + HashSet

Questions to Ask Yourself:
1. What information do I need to remember?
   -> Mapping from s character to t character.

2. What questions will I repeatedly ask?
   -> Has this character in s been seen before?
   -> If yes, what was it mapped to?
   -> Has this character in t already been used?

3. What is the minimum information needed?
   -> HashMap<Character, Character> for mappings.
   -> HashSet<Character> for used characters in t.

Algorithm:
1. Traverse both strings together.
2. If sc is already mapped:
      - Verify its mapped character equals tc.
      - If not, return false.
3. Otherwise:
      - If tc is already used, return false.
      - Else create the mapping and mark tc as used.
4. If traversal completes, return true.

Time Complexity:
O(n)

Space Complexity:
O(k)
where k = number of distinct characters (at most ASCII characters)

Interview Recognition Clues:
- One-to-one mapping
- Replace characters
- Preserve order
- Consistent mapping
- Bijection / Isomorphic

Common Mistakes:
1. Using <= instead of < in the loop.
2. Using String instead of Character.
3. Calling map.put() before validation.
4. Forgetting to check if tc is already used.
5. Updating an existing mapping unnecessarily.

Key Insight:
Don't think about HashMap first.
Think about:
"What information do I need to remember to answer my next question?"
The data structure becomes obvious after that.
*/

