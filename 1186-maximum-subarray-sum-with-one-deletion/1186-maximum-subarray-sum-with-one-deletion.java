class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;

        // Maximum subarray sum ending at current index
        // without using any deletion.
        int noDelete = arr[0];

        // Maximum subarray sum ending at current index
        // after using exactly one deletion.
        // Initially invalid because no deletion has been used yet.
        int oneDelete = Integer.MIN_VALUE;

        // Stores the overall maximum subarray sum.
        int res = arr[0];

        for (int i = 1; i < n; i++) {

            // Save the previous noDelete value because it is needed
            // for the "delete current element" transition.
            int prevNoDelete = noDelete;

            // Standard Kadane's Algorithm.
            // Either:
            // 1. Start a new subarray from arr[i]
            // 2. Extend the previous subarray
            noDelete = Math.max(arr[i], noDelete + arr[i]);

            // DP transition for one deletion.
            //
            // Option 1:
            // Delete the current element.
            // The best sum becomes the previous noDelete.
            //
            // Option 2:
            // Keep the current element and extend a subarray
            // where one deletion has already been used.
            //
            // NOTE:
            // oneDelete is initialized to Integer.MIN_VALUE.
            // Adding arr[i] to it causes integer overflow.
            // Therefore we must check whether oneDelete is valid first.
            if (oneDelete != Integer.MIN_VALUE) {
                oneDelete = Math.max(prevNoDelete, oneDelete + arr[i]);
            } else {
                oneDelete = prevNoDelete;
            }

            // Update the global answer.
            res = Math.max(res, Math.max(noDelete, oneDelete));
        }

        return res;
    }
}

/*
======================== REVISION NOTES ========================

Problem:
Maximum Subarray Sum with One Deletion.

Core Idea:
Maintain two Kadane states.

------------------------------------------------------------

State 1:
noDelete

Meaning:
Maximum subarray sum ending at the current index
without deleting any element.

Transition:
noDelete = max(arr[i], noDelete + arr[i])

------------------------------------------------------------

State 2:
oneDelete

Meaning:
Maximum subarray sum ending at the current index
after exactly one deletion has been used.

Two possibilities:

1. Delete the current element.
   Sum = previous noDelete

2. Keep the current element.
   Extend a subarray where one deletion
   has already been used.

Transition:
oneDelete = max(prevNoDelete,
                oneDelete + arr[i])

------------------------------------------------------------

Why store prevNoDelete?

Because noDelete gets updated first.
If we don't save its previous value, we lose the information
needed when deleting the current element.

------------------------------------------------------------

Common Bug (Your Bug):

Initializing

    oneDelete = Integer.MIN_VALUE

is fine.

But doing

    oneDelete + arr[i]

before oneDelete becomes valid causes integer overflow.

Example:

Integer.MIN_VALUE + (-2)

wraps around to

2147483646

This produces an incorrect answer.

Always check that oneDelete is valid before adding.

------------------------------------------------------------

Time Complexity:
O(n)

Space Complexity:
O(1)

Pattern:
Kadane's Algorithm + Dynamic Programming (State Machine)

Interview Clue:
Whenever a problem allows:

- one deletion
- one modification
- one transaction
- one special operation

Think:
"Can I maintain multiple DP states representing whether
the operation has been used yet?"

===============================================================
*/