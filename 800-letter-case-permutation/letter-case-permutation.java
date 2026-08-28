class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }

    private void backtrack(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        backtrack(chars, index + 1, result);
        if (Character.isLetter(chars[index])) {
            chars[index] ^= 1 << 5;
            backtrack(chars, index + 1, result);
            chars[index] ^= 1 << 5; 
        }
    }
}