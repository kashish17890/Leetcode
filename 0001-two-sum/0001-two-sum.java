class Solution {
    public int[] twoSum(int[] arr, int k) {
        int[] ans={-1,-1};
        HashMap<Integer,Integer> map=new HashMap<>();

        for(int i=0;i<arr.length;i++){
            if(map.containsKey(k-arr[i])){
                ans[0]=map.get(k-arr[i]);
                ans[1]=i;
            }
            map.put(arr[i],i);
        }  
        return ans; 
    }
}