class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len=0;
        int left=0;
        HashMap<Character,Integer> map=new HashMap<>();

        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);

            while(map.get(s.charAt(i))>1){
                map.put(s.charAt(left),map.get(s.charAt(left))-1);
                left++;
            }

            len=Math.max(len,i-left+1);
        }
        return len;
    }
}