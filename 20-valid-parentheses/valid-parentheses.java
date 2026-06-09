import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> ss = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                ss.push(')');
            } 
            else if (ch == '[') {
                ss.push(']');
            } 
            else if (ch == '{') {
                ss.push('}');
            } 
            else if (ss.isEmpty() || ch != ss.pop()) {
                return false;
            }
        }

        return ss.isEmpty();
    }
}