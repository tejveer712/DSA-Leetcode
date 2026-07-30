class Solution {
    public String removeOuterParentheses(String s) {

        // Initialize result string
        StringBuilder result = new StringBuilder();  
        // Initialize nesting level counter
        int level = 0;  

        // Traverse the string
        for (char ch : s.toCharArray()) {
            // If we encounter '(', increase the level
            if (ch == '(') {
                // If we're inside a primitive, add '(' to result
                if (level > 0) result.append(ch);
                 // Increase the nesting level for '('
                level++; 
            } 
            // If we encounter ')', decrease the level
            else if (ch == ')') {
                // Decrease the nesting level for ')'
                level--;  
                // If we're inside a primitive, add ')' to result
                if (level > 0) result.append(ch);
            }
        }

        // Return the result as a string after removing the outer parentheses
        return result.toString();
        
    }
    
}

/*
REVISION NOTES

1. level = current nesting depth.

2. For '(':
   - If level > 0, we're already inside a primitive, so keep '('.
   - Then increment level.

3. For ')':
   - First decrement level because we're leaving one nesting level.
   - If level > 0 afterward, we're still inside the primitive, so keep ')'.

4. Skip:
   - '(' when level changes 0 -> 1 (outer opening bracket)
   - ')' when level changes 1 -> 0 (outer closing bracket)

5. Time Complexity: O(n)
6. Space Complexity: O(n) (output StringBuilder)

*/