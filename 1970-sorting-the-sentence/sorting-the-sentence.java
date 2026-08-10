class Solution {
    public String sortSentence(String s) {
        String[] tokens = s.split(" ");
        String[] result = new String[tokens.length];

        for (String token : tokens) {
            int pos = token.charAt(token.length() - 1) - '0'; // digit at the end
            String word = token.substring(0, token.length() - 1); // strip digit
            result[pos - 1] = word; // place at correct 0-based index
        }

        return String.join(" ", result);
        
    }
}