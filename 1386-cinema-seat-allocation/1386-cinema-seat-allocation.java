class Solution {
    public int maxNumberOfFamilies(int n, int[][] arr) {
        HashMap<Integer,boolean[]> map=new HashMap<>();

        for(int i=0;i<arr.length;i++){
            int row=arr[i][0];
            int seat=arr[i][1];

            if(!map.containsKey(row)){
                map.put(row,new boolean[11]);
            }
            map.get(row)[seat]=true;
        }
        int ans=(n-map.size())*2;

        for(boolean[] seats:map.values()){
            boolean left=true;
            boolean mid=true;
            boolean right=true;

            for(int reserve=2;reserve<=5;reserve++){
                if(seats[reserve]){
                    left=false;
                    break;
            }
            }
            for(int reserve=4;reserve<=7;reserve++){
                if(seats[reserve]){
                    mid=false;
                    break;
                }
            }
            for(int reserve=6;reserve<=9;reserve++){
                if(seats[reserve]){
                    right=false;
                    break;
                }
            }
            if(left && right) ans+=2;
            else if(left || right || mid) ans++;
        }
        return ans;
    }
}