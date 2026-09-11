class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        for (int num = 100; num <= 998; num += 2) {
            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int units = num % 10;

            int[] need = new int[10];
            need[hundreds]++;
            need[tens]++;
            need[units]++;

            boolean canForm = true;
            for (int d = 0; d < 10; d++) {
                if (need[d] > freq[d]) {
                    canForm = false;
                    break;
                }
            }

            if (canForm) count++;
        }

        return count;
        
    }
}