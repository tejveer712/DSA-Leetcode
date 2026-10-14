class Solution {
    public int removeDuplicates(int[] nums) {

        // The first two elements can always be kept because
        // each value is allowed to appear at most twice.
        int k = 2;

        // Start scanning from index 2 because the first two
        // elements are already part of the valid portion.
        for (int i = 2; i < nums.length; i++) {

            // Compare the current element with the element
            // two positions behind the next write position.
            // If they differ, adding nums[i] will not create
            // more than two occurrences of the same value.
            if (nums[i] != nums[k - 2]) {

                // Place the valid element at the next write position.
                // This overwrites an element that was skipped earlier,
                // if necessary.
                nums[k] = nums[i];

                // Increase the length of the valid portion.
                k++;
            }
        }

        // Return the number of valid elements.
        return k;
    }
}

/*
REVISION NOTES — LEETCODE 80

1. Pattern: Two Pointers (Read and Write)

2. Key Idea:
   Since the array is sorted, duplicates are adjacent.
   Compare each new element with nums[k - 2] to determine
   whether it can be included.

3. Pointer Roles:
   i = read pointer; scans the original array.
   k = write pointer; marks the next valid position.

4. Invariant:
   The first k elements contain the processed elements,
   with each distinct value appearing at most twice.

5. Complexity:
   Time: O(n) — each element is examined once.
   Space: O(1) — modifications are performed in place.

6. Pattern Recognition:
   Sorted array + in-place modification + limited duplicates
   suggests a read/write two-pointer approach.

7. Common Mistakes:
   - Comparing nums[i] with nums[i - 2] instead of nums[k - 2].
   - Forgetting to increment k after writing.
   - Returning the last index instead of the valid length.
   - Using a separate array unnecessarily.

8. Edge Case:
   If nums.length < 2, the first two elements are not both
   present. This implementation needs a short-array guard
   for complete generality.

9. One-Line Takeaway:
   Keep a new element only when it differs from the element
   two positions behind the next write position.
*/
