class Solution {

    // Stores the start and end indices of the longest palindrome found so far
    int start = 0;
    int end = 0;

    // Expands around the given center and updates start/end if a longer palindrome is found
    void expand(int left, int right, String s) {

        // Expand while the characters match
        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        // After the loop:
        // left  -> one position before palindrome
        // right -> one position after palindrome
        int length = right - left - 1;

        // Update answer if this palindrome is longer
        if (length > end - start + 1) {
            start = left + 1;
            end = right - 1;
        }
    }

    public String longestPalindrome(String s) {

        if (s == null || s.length() < 2) {
            return s;
        }

        for (int i = 0; i < s.length(); i++) {

            // Check odd-length palindrome
            expand(i, i, s);

            // Check even-length palindrome
            expand(i, i + 1, s);
        }

        // substring(endIndex) excludes the end index,
        // so we use end + 1
        return s.substring(start, end + 1);
    }
}

/*
========================
Revision Notes
========================

1. Brute Force
   - Generate all substrings.
   - Check each substring for palindrome.
   - Time: O(n³)

2. Key Observation
   - Every palindrome has a center.
   - Odd palindrome -> one center character.
   - Even palindrome -> center between two characters.

3. Number of Centers
   - n odd centers
   - n - 1 even centers
   - Total = 2n - 1 = O(n)

4. Expansion Logic
   while (
       left >= 0 &&
       right < s.length() &&
       s.charAt(left) == s.charAt(right)
   ) {
       left--;
       right++;
   }

5. Important
   When expansion stops:
   left  -> one step before palindrome
   right -> one step after palindrome

6. Palindrome Length
   length = right - left - 1

7. Actual Palindrome
   start = left + 1
   end   = right - 1

8. Result
   return s.substring(start, end + 1);

9. Complexity
   Time  : O(n²)
   Space : O(1)
*/