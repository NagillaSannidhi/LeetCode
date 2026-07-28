class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt=new int[26];
        for (char c: s.toCharArray())cnt[c -'a']++;
        StringBuilder left=new StringBuilder();
        char mid=0;
        for(int i=0;i<26;i++){
            while(cnt[i]>1){
                left.append((char)('a'+i));
                cnt[i]-=2;
            }
            if(cnt[i]==1) mid=(char)('a'+i);
        }
        return left.toString() + (mid==0 ? "" : mid) + left.reverse().toString();
        
    }
}