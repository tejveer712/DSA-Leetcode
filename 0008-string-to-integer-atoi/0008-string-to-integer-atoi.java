class Solution {
    public int myAtoi(String s) {
        
        /* 
        Why do many accepted solutions use long?
        Because it avoids exactly this problem. We only care that the final answer fits in an int, but during computation it's convenient to use a larger type.
        */
        long ans = 0;
        int i = 0;
        int sign = 1;

        // Skip leading whitespaces
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        // Check for optional sign
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Read digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {

            int digit = s.charAt(i) - '0';

            // Check for overflow before multiplying by 10
            if (ans > Integer.MAX_VALUE / 10 ||
                (ans == Integer.MAX_VALUE / 10 &&
                 digit > (sign == 1 ? 7 : 8))) {

                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            ans = ans * 10 + digit;
            i++;
        }

        return (int)(sign * ans);
    }
}

/*
1. Use one pointer `i` to scan the string from left to right.

2. Algorithm:
   - Skip leading whitespaces.
   - Read optional '+' or '-' sign.
   - Read consecutive digits.
   - Stop immediately when a non-digit is encountered.
   - Return `sign * ans`.

3. Convert a character to a digit:
   digit = s.charAt(i) - '0';

4. Build the number:
   ans = ans * 10 + digit;

5. Overflow must be checked BEFORE:
   ans = ans * 10 + digit;

   because integer overflow in Java wraps around silently.

6. Overflow condition:
   ans > Integer.MAX_VALUE / 10
   OR
   ans == Integer.MAX_VALUE / 10 && digit > allowedLastDigit

7. Allowed last digit:
   Positive number → 7 (2147483647)
   Negative number → 8 (-2147483648)

8. Time Complexity: O(n)

9. Space Complexity: O(1)
 */