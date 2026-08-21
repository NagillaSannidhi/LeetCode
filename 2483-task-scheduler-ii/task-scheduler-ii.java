class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> nextAvailable = new HashMap<>();
        long day = 0;

        for (int task : tasks) {
            day++; // move to the day we're about to use
            if (nextAvailable.containsKey(task)) {
                day = Math.max(day, nextAvailable.get(task));
            }
            nextAvailable.put(task, day + space + 1);
        }

        return day;
        
    }
}