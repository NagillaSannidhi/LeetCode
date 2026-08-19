class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rows = new HashMap<>();
        
        final int LEFT  = 0b0000011110;   
        final int MID   = 0b0001111000;  
        final int RIGHT = 0b0111100000;  
        
        for (int[] seat : reservedSeats) {
            int r = seat[0], c = seat[1];
            if (c >= 2 && c <= 9) {
                int bit = 1 << (c - 1);
                rows.merge(r, bit, (a, b) -> a | b);
            }
        }
        
       
        int total = (n - rows.size()) * 2;
        
        for (int mask : rows.values()) {
            if ((mask & LEFT) == 0 && (mask & RIGHT) == 0) {
                total += 2;
            } else if ((mask & LEFT) == 0 || (mask & MID) == 0 || (mask & RIGHT) == 0) {
                total += 1;
            }
        }
        
        return total;
        
    }
}