class Solution {
    public int peakIndexInMountainArray(int[] arr) {

        int low = 0;
        int high = arr.length - 1;
        int peak = -1;   // Stores the current possible peak.

        // Use low < high because we compare arr[mid] with arr[mid + 1].
        // This guarantees mid is never the last index,
        // so arr[mid + 1] is always a valid access.
        while (low < high) {

            int mid = low + (high - low) / 2;

            // If current element is smaller than the next,
            // we are on the increasing slope.
            // Therefore, the peak must lie on the right.
            if (arr[mid] < arr[mid + 1]) {

                low = mid + 1;
            }

            // If current element is greater than the next,
            // we are on the decreasing slope or exactly at the peak.
            // Since mid itself can be the answer,
            // DON'T discard it by doing high = mid - 1.
            else if (arr[mid] > arr[mid + 1]) {

                peak = mid;      // Store current possible answer.
                high = mid;      // Keep mid in the search space.
            }
        }

        return peak;
    }
}
// Compare arr[mid] and arr[mid + 1]

// arr[mid] < arr[mid + 1]
// -> We are on the increasing slope.
// -> Peak is to the right.
// -> low = mid + 1

// arr[mid] > arr[mid + 1]
// -> We are on the decreasing slope OR at the peak.
// -> mid might be the answer.
// -> high = mid

// Use while(low < high)
// This guarantees mid + 1 is always a valid index.

// At the end:
// low == high == peak index.