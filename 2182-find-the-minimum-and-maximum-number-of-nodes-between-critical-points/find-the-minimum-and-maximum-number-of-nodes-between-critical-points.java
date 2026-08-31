/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstIdx = -1, lastIdx = -1, prevIdx = -1;
        int minDist = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int idx = 1;
        
        while (curr.next != null) {
            boolean isCritical = (curr.val > prev.val && curr.val > curr.next.val) ||
                                  (curr.val < prev.val && curr.val < curr.next.val);
            
            if (isCritical) {
                if (firstIdx == -1) {
                    firstIdx = idx; 
                } else {
                    minDist = Math.min(minDist, idx - prevIdx);
                }
                lastIdx = idx;
                prevIdx = idx;
            }
            
            prev = curr;
            curr = curr.next;
            idx++;
        }
        
        if (firstIdx == -1 || firstIdx == lastIdx) {
            return new int[] {-1, -1}; 
        }
        
        int maxDist = lastIdx - firstIdx;
        return new int[] {minDist, maxDist};
        
    }
}