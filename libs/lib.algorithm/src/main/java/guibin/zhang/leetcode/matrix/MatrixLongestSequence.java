package guibin.zhang.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a NxN matrix which contains all distinct 1 to n^2 numbers, 
 * write code to print sequence of increasing adjacent sequential numbers. 
 * 
 * ex: 
 * 1 5 9 
 * 2 3 8 
 * 4 6 7 
 * 
 * should print 
 * 6 7 8 9
 * 
 * Question link: http://www.careercup.com/question?id=5147801809846272
 * 
 * time O(n^2) space O(n^2) solution. 
 * we will need a 2D array visited[n][n] and two global vars->start and max=0. 
 * 
 * Now we will traverse the matrix row by row. 
 * for each 'not visited' element k start a recursive dfs such that next element is k+1. 
 * Mark all traversed elements as visited so that you do not start a traversal from them again 
 * the dfs() will return you the depth value till which it could go. 
 * e.g. for 6 it will return 4 (6-7-8-9). for 7 it will return 3 (7-8-9). 
 * if this value is greater than max, then update max and start value. 
 * 
 * At last print max no. of consecutive characters from start value.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class MatrixLongestSequence {
    
    /**
     * Get the longest sequence count from the start point [r, c] of matrix.
     * @param r row index
     * @param c column
     * @param matrix
     * @param visited
     * @return 
     */
    public static int printSequence(int r, int c, int[][] matrix, boolean[][] visited) {
        
        int X = matrix.length;
        int Y = matrix[0].length;
        
        // Look up
        if (r - 1 >= 0 && matrix[r - 1][c] == matrix[r][c] + 1 && !visited[r - 1][c]) {
            visited[r - 1][c] = true;
            int count =  1 + printSequence(r - 1, c, matrix, visited);
            visited[r - 1][c] = false;
            return count;
        }
        
        // Look down
        if (r + 1 < X && matrix[r + 1][c] == matrix[r][c] + 1 && !visited[r + 1][c]) {
            visited[r + 1][c] = true;
            int count = 1 + printSequence(r + 1, c, matrix, visited);
            visited[r + 1][c] = false;
            return count;
        }
        
        // Look left
        if (c - 1 >= 0 && matrix[r][c - 1] == matrix[r][c] + 1 && !visited[r][c - 1]) {
            visited[r][c - 1] = true;
            int count =  1 + printSequence(r, c - 1, matrix, visited);
            visited[r][c - 1] = false;
            return count;
        }
        
        // Look right
        if (c + 1 < Y && matrix[r][c + 1] == matrix[r][c] + 1 && !visited[r][c + 1]) {
            visited[r][c + 1] = true;
            int count =  1 + printSequence(r, c + 1, matrix, visited);
            visited[r][c + 1] = false;
            return count;
        }
        
        return 1;
    }
    
    
    /**
     * Get the longest sequence from the start point [r, c] of matrix.
     * 
     * This method only works for the **distinct** array elements.
     * Since this loop is searching for the next bigger element.
     * 
     * @param matrix
     * @param r row index of start point
     * @param c column index of start point
     * @return 
     */
    public static List<Integer> getLogestSeq(int[][] matrix, int r, int c) {
        
        List<Integer> result = new ArrayList<>();
        if (r >= matrix.length || c >= matrix[0].length) {
            return result;
        }
        
        result.add(matrix[r][c]);
        boolean done = false;
        while (!done) {
            done = true;
            //Move right to search bigger one
            if (r < matrix.length - 1 && matrix[r][c] + 1 == matrix[r + 1][c]) {
                result.add(matrix[r + 1][c]);
                r += 1;
                done = false;
            }
            //Move left to search bigger one
            if (r > 0 && matrix[r][c] + 1 == matrix[r - 1][c]) {
                result.add(matrix[r - 1][c]);
                r -= 1;
                done = false;
            }
            //Move up to search bigger one
            if (c < matrix[0].length - 1 && matrix[r][c] + 1 == matrix[r][c + 1]) {
                result.add(matrix[r][c + 1]);
                c += 1;
                done = false;
            }
            //Move down to search bigger one
            if (c > 0 && matrix[r][c] + 1 == matrix[r][c - 1]) {
                result.add(matrix[r][c - 1]);
                c -= 1;
                done = false;
            }
        }
                
        return result;
    }
    
    public static void printLogestSequence(int[][] matrix) {
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        List<Integer> maxSeq = new ArrayList<>();
        //Iterate all posible start point to get the maxSeq
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                List<Integer> currSeq = getLogestSeq(matrix, i, j);
                if (currSeq.size() > maxSeq.size()) {
                    maxSeq = currSeq;
                }
            }
        }
        
        maxSeq.forEach(i -> System.out.print(i + ", "));
        System.out.println();
    }
    
    
    public static void main(String[] args) {
        
        int[][] matrix = {{1, 5, 9},
                          {2, 7, 8},
                          {4, 6, 3}};
        int X = matrix.length;
        int Y = matrix[0].length;
        boolean[][] visited = new boolean[X][Y];
        int max = 0;
        List<Integer> result = new ArrayList<>();
        List<Integer> maxSequence = null;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                int m = printSequence(i, j, matrix, visited);
                System.out.println("[" + i + "][" + j + "]" + " is " + m);
                if (m > max) {
                    max = m;
                    maxSequence = new ArrayList<>(result);
                }
            }
        }
        System.out.println("max number of items: " + max);
        
        System.out.println("----print longest sequence------");
        printLogestSequence(matrix);
    }
}