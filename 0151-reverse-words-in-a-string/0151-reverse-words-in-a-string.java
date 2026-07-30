class Solution {
    public String reverseWords(String s) {

        // StringBuilder is used to efficiently build the final answer.
        StringBuilder result = new StringBuilder();

        // Start traversing from the end of the string.
        int i = s.length() - 1;

        while (i >= 0) {

            // Skip any spaces (leading, trailing, or multiple spaces).
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }

            // If we've reached the beginning after skipping spaces, stop.
            if (i < 0) break;

            // Store the end index of the current word.
            int end = i;

            // Move backwards until we reach the beginning of the word.
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }

            // Extract the current word.
            String word = s.substring(i + 1, end + 1);

            // Add a space only if this is not the first word.
            // This avoids leading and trailing spaces.
            if (result.length() > 0) {
                result.append(" ");
            }

            // Append the extracted word.
            result.append(word);
        }

        return result.toString();
    }
}

/*
=========================================
REVISION NOTES
=========================================

Problem:
Reverse the order of words in a string while:
- Removing leading spaces.
- Removing trailing spaces.
- Replacing multiple spaces with a single space.

-----------------------------------------
Approach (Backward Traversal)
-----------------------------------------
1. Start from the end of the string.
2. Skip all spaces.
3. Mark the end of the current word.
4. Move backwards until a space is found.
5. Extract the word using substring().
6. Append it to the answer.
7. Repeat until the beginning of the string.

Since we are reading words from right to left,
they automatically appear in reversed order.

-----------------------------------------
Why does this work?
-----------------------------------------
Input:
"the sky is blue"

Traversal:
blue → is → sky → the

Output:
"blue is sky the"

No separate reversing step is needed.

-----------------------------------------
Key Observations
-----------------------------------------
1. Skip spaces first.
   Prevents leading, trailing and multiple spaces.

2. Store the word's ending index before moving backwards.

3. substring(i + 1, end + 1)
   because substring's ending index is exclusive.

4. Add a space only if the answer already contains a word.

if(result.length() > 0)
    result.append(" ");

This guarantees:
- No leading spaces
- No trailing spaces
- Exactly one space between words

-----------------------------------------
Time Complexity
-----------------------------------------
O(n)

Every character is visited at most once.

-----------------------------------------
Space Complexity
-----------------------------------------
O(1)

(Excluding the output StringBuilder.)

-----------------------------------------
Pattern Recognition
-----------------------------------------
Whenever a problem says:
- Reverse words
- Ignore extra spaces
- Keep characters inside each word unchanged

Think:

Backward Traversal + Word Extraction

-----------------------------------------
Interview Justification
-----------------------------------------
Instead of using split(), we traverse from the end and
extract words directly.

Advantages:
- No array of words is created.
- No need to reverse an array.
- Handles multiple spaces naturally.
- Linear time solution.

-----------------------------------------
Recognition Trick
-----------------------------------------
Remember this sequence:

Skip Spaces
      ↓
Mark End
      ↓
Find Start
      ↓
Extract Word
      ↓
Append

Whenever you see "Reverse Words in a String",
this framework should immediately come to mind.
*/