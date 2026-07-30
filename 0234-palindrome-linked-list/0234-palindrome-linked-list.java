/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.next = next; this.val = val; }
 * }
 */
class Solution {

    // Reverses the linked list starting from 'head'
    // Returns the new head of the reversed list
    ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {

            // Save the next node before breaking the link
            next = curr.next;

            // Reverse the current node's pointer
            curr.next = prev;

            // Move prev and curr one step forward
            prev = curr;
            curr = next;
        }

        // 'prev' becomes the new head of the reversed list
        return prev;
    }

    public boolean isPalindrome(ListNode head) {

        // Empty list or single node is always a palindrome
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Find the middle of the linked list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode middle;

        // Even length:
        // slow is already at the start of the second half.
        if (fast == null) {
            middle = slow;
        }
        // Odd length:
        // Skip the middle element because it doesn't affect palindrome.
        else {
            middle = slow.next;
        }

        // Reverse the second half
        ListNode second = reverse(middle);

        // Start comparing from the beginning of both halves
        ListNode first = head;

        while (second != null) {

            // Mismatch found
            if (first.val != second.val) {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        // All nodes matched
        return true;
    }
}

/*
========================
Revision Notes
========================

Pattern:
- Fast & Slow Pointer + Reverse Linked List

Intuition:
- A palindrome requires comparing the first half with the second half.
- Since a singly linked list cannot move backwards,
  reverse the second half so both pointers can move forward.

Algorithm:
1. Find the middle using slow & fast pointers.
2. If the list length is even:
      Second half starts at slow.
3. If the list length is odd:
      Skip the middle node.
      Second half starts at slow.next.
4. Reverse the second half.
5. Compare the first half and reversed second half.
6. If every value matches -> Palindrome.
7. (Optional) Reverse the second half again to restore the original list.

Reverse Linked List Pattern:
prev = null
curr = head

while(curr != null){
    next = curr.next;    // Save
    curr.next = prev;    // Reverse
    prev = curr;         // Grow reversed list
    curr = next;         // Move ahead
}

return prev;

Time Complexity:
- Finding middle      : O(n)
- Reversing half      : O(n/2)
- Comparing halves    : O(n/2)

Overall Time : O(n)

Space Complexity:
- O(1)

Interview Takeaway:
Whenever you need to compare values from both ends of a singly linked list,
ask yourself:
1. Can I move backwards? -> No.
2. Can I transform the list? -> Reverse one half.
3. Then compare both halves using forward traversal only.
*/


/*
========================
Interview Follow-up
========================

Q. What if the interviewer asks:
   "Can you restore the original linked list after checking?"

Answer:
Yes.

During the algorithm, the second half of the linked list is reversed
to make comparison possible.

Before comparing, store the head of the reversed second half:

    ListNode secondHead = reverse(middle);
    ListNode second = secondHead;

After the comparison is complete, simply reverse the second half again:

    reverse(secondHead);

Why does this work?
- Reversing a linked list twice restores it to its original order.
- The reverse operation is performed in-place, so no extra space is required.
- Since the first half was never modified, restoring the second half
  automatically restores the entire linked list.

Interview Explanation:
"I keep a reference to the head of the reversed second half before
starting the comparison. Once the comparison is finished, I call the
same reverse() function again on that half. Since reversing a linked
list twice restores its original order, the input linked list remains
unchanged."

Complexity after restoration:
Time  : O(n)      // One additional reverse of half the list
Space : O(1)
*/