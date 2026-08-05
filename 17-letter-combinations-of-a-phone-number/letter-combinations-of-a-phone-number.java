class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();

        if (digits.length() == 0) {
            return ans;
        }

        String[] map = {
            "", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"
        };

        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        for (char digit : digits.toCharArray()) {
            String letters = map[digit - '0'];
            int size = queue.size();

            while (size-- > 0) {
                String curr = queue.poll();

                for (char ch : letters.toCharArray()) {
                    queue.offer(curr + ch);
                }
            }
        }

        ans.addAll(queue);
        return ans;
        
    }
}