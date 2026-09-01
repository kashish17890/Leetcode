class Solution {
    public boolean canConstruct(String ran, String maga) {
        HashMap<Character,Integer> map=new HashMap<>();

        for(int i=0;i<maga.length();i++){
            map.put(maga.charAt(i),map.getOrDefault(maga.charAt(i),0)+1);
        }
        for(int i=0;i<ran.length();i++){
            char ch=ran.charAt(i);
            if(!map.containsKey(ch) || map.get(ch)==0) return false;
            map.put(ch,map.get(ch)-1);
        }
        return true;
    }
}