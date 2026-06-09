import java.util.Stack;

class Solution {
    public String removeStars(String s) {
        Stack<Character> ss = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch != '*') {
                ss.push(ch);
            } else {
                ss.pop();
            }
        }

        StringBuilder result = new StringBuilder();

        while (!ss.isEmpty()) {
            result.append(ss.pop());
        }

        return result.reverse().toString();
    }
}