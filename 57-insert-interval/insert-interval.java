class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        
        int mergedStart = newInterval[0];
        int mergedEnd = newInterval[1];
        while (i < n && intervals[i][0] <= mergedEnd) {
            mergedStart = Math.min(mergedStart, intervals[i][0]);
            mergedEnd = Math.max(mergedEnd, intervals[i][1]);
            i++;
        }
        result.add(new int[]{mergedStart, mergedEnd});

        
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
        
    }
}