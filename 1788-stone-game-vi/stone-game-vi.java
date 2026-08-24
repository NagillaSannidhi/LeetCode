class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> (bobValues[b] + aliceValues[b]) - (bobValues[a] + aliceValues[a]));

        long aliceScore = 0, bobScore = 0;
        for (int turn = 0; turn < n; turn++) {
            int stone = idx[turn];
            if (turn % 2 == 0) {
                aliceScore += aliceValues[stone]; 
            } else {
                bobScore += bobValues[stone];     
            }
        }

        if (aliceScore > bobScore) return 1;
        if (aliceScore < bobScore) return -1;
        return 0;
        
    }
}