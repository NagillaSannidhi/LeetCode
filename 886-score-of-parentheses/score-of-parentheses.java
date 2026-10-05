class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); 

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); 
            } else {
                int inner = stack.pop();
                int score = (inner == 0) ? 1 : 2 * inner;  
                stack.push(stack.pop() + score); 
            }
        }

        return stack.pop();
        
    }
}