package guibin.zhang.leecode;

/**
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * 
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 * 
 * http://fenghaolw.blogspot.com/2012/11/decode-ways.html
 * http://besttony.wordpress.com/2013/06/27/decode-ways-dynamic-programming-dp/
 * http://fisherlei.blogspot.com/2013/01/leetcode-decode-ways-solution.html
 * https://github.com/mengli/leetcode/blob/master/Decode%20Ways.java
 * http://discuss.leetcode.com/questions/266/decode-ways
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class DecodeWays {
    
    //brute force with recursive dfs method,  Time Limit Exceeded for big data set.
    public int numDecodings(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }
        //edge case
        if(s.length() == 1) {
            if(s.charAt(0) < '1' || s.charAt(0) > '9') {
                return 0;
            } else {
                return 1;
            }
        }
        //edge case
        else if(s.length() == 2) {
            if(s.charAt(0) < '1' || s.charAt(0) > '9') {
                return 0;
            } else if(s.charAt(0) > '2' && s.charAt(1) == '0') {
                return 0;
            } else if(s.charAt(0) > '2' && s.charAt(1) != '0') {
                return 1;
            } else if(s.charAt(1) > '6' || s.charAt(1) == '0') {
                return 1;
            } else {
                return 2;
            }
        }
        else {
            //The final result is:
            //# ways of remaining n-1 characters + # ways of remaining n-2 characters.
            int tmp = numDecodings(s.substring(0, 2)) > 0 ? 1 : 0;
            return numDecodings(s.substring(0, 1)) * numDecodings(s.substring(1)) + 
                    tmp * numDecodings(s.substring(2));
        }
        
    }
    
    public int numDecodings_v2(String s, int start) {
        
        if(s.length() == 0) {
            return 0;
        }
        
        //c[i]: The # of decoding for s whose **length** is i;
        int[] c = new int[s.length() + 1];
        c[0] = 1;//s's length is 0, empty string, has one decoding means nothing.
        if(s.charAt(0) != '0') {//s's length is 1, so if the character is not '0', it has one decoding just as c[0]
            c[1] = c[0];
        } else {//otherwise, it is invalid and has 0 decodings.
            c[1] = 0;
        }
        /**
         * Transformation function as:
         * Count[i] = Count[i-1]  if S[i-1] is a valid char
         *     or   = Count[i-1]+ Count[i-2]  if S[i-1] and S[i-2] together is still a valid char.
         */
        for(int i = 2; i <= s.length(); i++) {
            if(s.charAt(i - 1) == '0') {
                c[i] = 0;
            } else {
                c[i] = c[i - 1];
            }
            if(s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6') {
                c[i] += c[i - 2];
            }
        }
        
        return c[s.length()];
    }
    
    
}
