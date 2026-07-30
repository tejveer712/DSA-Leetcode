class Solution {

    // Returns ceil(a / b) using integer arithmetic.
    // Example:
    // 7/3 = 3
    // 10/2 = 5
    public int ceilDivision(int a, int b) {
        return (a + b - 1) / b;
    }

    // Helper function:
    // Checks whether choosing 'divisor' keeps the total sum
    // of ceil divisions within the given threshold.
    public boolean checkThreshold(int[] nums, int threshold, int mid) {

        int sum = 0;

        for (int num : nums) {
            sum += ceilDivision(num, mid);

            //same thing can be done like this also 
            //sum += (nums[i] + mid - 1) / mid;
        }

        // Valid divisor if the total sum is within threshold.
        return sum <= threshold;
    }

    public int smallestDivisor(int[] nums, int threshold) {

        // Smallest possible divisor.
        int low = 1;

        // Largest possible divisor.
        // Anything larger than max(nums) behaves the same.
        int high = 0;
        for (int num : nums) {
            high = Math.max(high, num);
        }

        int ans = 0;

        // Binary Search on Answer
        while (low <= high) {

            int mid = low + (high - low) / 2;

            // If current divisor works,
            // try finding an even smaller valid divisor.
            if (checkThreshold(nums, threshold, mid)) {
                ans = mid;
                high = mid - 1;
            }
            // Current divisor is too small,
            // increase it to reduce the total sum.
            else {
                low = mid + 1;
            }
        }

        return ans;
    }
}

/*
======================== REVISION NOTES ========================

Pattern:
Binary Search on Answer

Search Space:
Minimum divisor = 1
Maximum divisor = max(nums)

Helper Function:
Simulate the process:
sum += ceil(nums[i] / divisor)

Current divisor is VALID if:
sum <= threshold

---------------------------------------------------------------

Monotonic Property:

As divisor increases,
each ceil(nums[i] / divisor) either decreases or stays the same.

Therefore,

divisor ->
1   2   3   4   5   6 ...

sum ->
17 10  7  7  5  5 ...

Validity ->
F   F   F   F   T   T   T

This forms:
False False False True True True

which is perfect for Binary Search.

---------------------------------------------------------------

Binary Search Decision:

If helper(mid) == true
    Current divisor works.
    Save answer.
    Search LEFT for a smaller valid divisor.
    high = mid - 1

If helper(mid) == false
    Divisor is too small.
    Sum is too large.
    Increase divisor.
    low = mid + 1

---------------------------------------------------------------

Ceil Division Trick:

Instead of:

if(a % b == 0)
    a / b;
else
    a / b + 1;

Use:

(a + b - 1) / b

---------------------------------------------------------------

Time Complexity:

Finding max element:
O(n)

Binary Search:
O(log(max(nums)))

Helper function:
O(n)

Overall:
O(n * log(max(nums)))

Space Complexity:
O(1)

---------------------------------------------------------------

Recognition Clues:

✓ Find minimum/maximum value satisfying a condition.
✓ Search space is a range of integers.
✓ Need to "try" an answer and verify it.
✓ Helper returns true/false.
✓ Validity changes only once (FFFFTTTT or TTTTFFFF).

Always ask yourself:

1. What am I binary searching on?
2. What makes a candidate valid?
3. What is the monotonic property?
4. Am I looking for the FIRST valid answer or the LAST valid answer?

===============================================================
*/