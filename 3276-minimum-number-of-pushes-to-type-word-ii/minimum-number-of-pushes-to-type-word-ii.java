class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        Arrays.sort(freq);
        for (int i = 0, j = 25; i < j; i++, j--) {
            int temp = freq[i];
            freq[i] = freq[j];
            freq[j] = temp;
        }
        
        int total = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) break; 
            int cost = (i / 8) + 1;
            total += freq[i] * cost;
        }
        
        return total;
        
    }
}