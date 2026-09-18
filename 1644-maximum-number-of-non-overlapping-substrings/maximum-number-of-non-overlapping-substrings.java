class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        
        int[] windowStart = new int[26];
        int[] windowEnd = new int[26];
        Arrays.fill(windowStart, -1);

        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue; 

            int start = first[c], end = last[c];
            int i = start;
            while (i <= end) {
                int ch = s.charAt(i) - 'a';
                if (first[ch] < start) {
                    start = first[ch];
                    i = start; 
                    continue;
                }
                if (last[ch] > end) {
                    end = last[ch];
                }
                i++;
            }

            windowStart[c] = start;
            windowEnd[c] = end;
        }
        List<int[]> intervals = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (windowStart[c] != -1) {
                intervals.add(new int[]{windowStart[c], windowEnd[c]});
            }
        }
        
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return (a[1] - a[0]) - (b[1] - b[0]);
        });

        List<String> result = new ArrayList<>();
        int lastEnd = -1;

        for (int[] interval : intervals) {
            if (interval[0] > lastEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1];
            }
        }

        return result;
        
    }
}