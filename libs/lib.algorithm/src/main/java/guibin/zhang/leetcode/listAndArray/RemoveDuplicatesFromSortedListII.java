package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * 
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 * 
 * Run Status: Accepted!
 * Program Runtime: 672 milli secs
 * Progress: 166/166 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveDuplicatesFromSortedListII {
    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    /**
     * This method is easy to understand.
     * 1. Use safeguard as the new head.
     * 2. Use (nxt.next.val == nxt.val) to find out all duplicates.
     * 3. Use curr.next = nxt.next to skip all duplicates.
     * 
     * reference: http://gongxuns.blogspot.com/2012/12/leetcode-remove-duplicates-from-sorted_11.html
     * 
     * @param head
     * @return 
     */
    public ListNode deleteDuplicates_v3(ListNode head) {
        
        //Add a safeguard as the new head to simplify processing
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = prev;
        
        while (curr.next != null) {
            ListNode nxt = curr.next;
            while (nxt.next != null && nxt.next.val == nxt.val) {
                nxt = nxt.next;
            }
            //If nxt == curr.next means nxt is next to curr, no duplications.
            //Otherwise there is duplication from [curr.next to nxt] inclusive.
            if (nxt != curr.next) {//Skip/remove the duplicates
                curr.next = nxt.next;
            } else {
                curr = curr.next;
            }
        }
        
        return dummy.next;
    }
    
    public ListNode deleteDuplicates_v2(ListNode head) {

        ListNode cur = head, pre = null;
        ListNode duplicate = null;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                duplicate = cur;
                cur.next = cur.next.next;
            } else if (duplicate != null && cur.val == duplicate.val) {
                cur = cur.next;
                if (pre != null) {
                    pre.next = cur;
                } else {
                    head = cur;
                }
            } else {
                duplicate = null;
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
    
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedListII rd = new RemoveDuplicatesFromSortedListII();
        ListNode a = rd.new ListNode(0);
        ListNode b = rd.new ListNode(0);
        ListNode c = rd.new ListNode(1);
        ListNode d = rd.new ListNode(1);
        ListNode e = rd.new ListNode(3);
        ListNode f = rd.new ListNode(3);
        ListNode g = rd.new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        ListNode res = rd.deleteDuplicates_v3(a);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
