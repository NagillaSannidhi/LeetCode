class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int total = 0;
        int cost = 1;
        int remaining = n;
        
        while (remaining > 0) {
            int count = Math.min(8, remaining);
            total += count * cost;
            remaining -= count;
            cost++;
        }
        
        return total;
    
        
    }
}