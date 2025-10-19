import java.util.*;

class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        String smallest = s;
        queue.offer(s);
        visited.add(s);
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            
            // Update smallest lexicographically
            if (curr.compareTo(smallest) < 0) {
                smallest = curr;
            }

            // Operation 1: Add 'a' to all digits at odd indices
            String added = addToOddIndices(curr, a);
            if (visited.add(added)) {
                queue.offer(added);
            }

            // Operation 2: Rotate right by 'b' positions
            String rotated = rotateRight(curr, b);
            if (visited.add(rotated)) {
                queue.offer(rotated);
            }
        }
        
        return smallest;
    }
    
    // Add 'a' to digits at odd indices (mod 10)
    private String addToOddIndices(String s, int a) {
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i += 2) {
            int digit = arr[i] - '0';
            digit = (digit + a) % 10;
            arr[i] = (char)(digit + '0');
        }
        return new String(arr);
    }
    
    // Rotate right by b positions
    private String rotateRight(String s, int b) {
        int n = s.length();
        b = b % n;
        return s.substring(n - b) + s.substring(0, n - b);
    }
}
