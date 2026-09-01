class Solution {
    public boolean canConstruct(String ran, String maga) {
        HashMap<Character,Integer> map=new HashMap<>();

        for(int i=0;i<ran.length();i++){
            map.put(ran.charAt(i),map.getOrDefault(ran.charAt(i),0)+1);
        }

        for(int i=0;i<maga.length();i++){
            char ch=maga.charAt(i);
            if(map.containsKey(ch) && map.get(ch)>0){
                map.put(ch,map.get(ch)-1);
            }
        }

        for(int n:map.values()){
            if(n>0) return false;
        }

        return true;
    }
}