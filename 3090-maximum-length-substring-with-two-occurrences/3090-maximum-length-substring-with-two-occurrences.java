
class Solution {
    public int maximumLengthSubstring(String s) {
        int len=0;
        int left=0;

        HashMap<Character,Integer> map=new HashMap<>();

        for(int i=0;i<s.length();i++){
            char rtchar=s.charAt(i);

            map.put(rtchar,map.getOrDefault(rtchar,0)+1);

            while(map.get(rtchar)>2){
                char lftchar=s.charAt(left);
                map.put(lftchar,map.get(lftchar)-1);

                left++;
            }
            len=Math.max(len,i-left+1);
        }
        return len;
    }
}
