class Solution {
    public boolean unique(int[] arr,int target){
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==target){
                count++;
            }
        }
        return count==1;
    }
    public int largestInteger(int[] nums, int k) {
        if(k==1){
            int ans=-1;
            HashMap<Integer,Integer> map=new HashMap<>();

            for(int i:nums){
                map.put(i,map.getOrDefault(i,0)+1);
            }

            for(int i:map.keySet()){
                if(map.get(i)==1){
                    ans=Math.max(ans,i);
                }
            }
            return ans;
        }

        if(k==nums.length){
            int ans=-1;
            for(int i:nums){
                ans=Math.max(ans,i);
            }
            return ans;
        }

        int ans=-1;

        if(unique(nums,nums[0])){
            ans=Math.max(ans,nums[0]);
        }
        if(unique(nums,nums[nums.length-1])){
            ans=Math.max(ans,nums[nums.length-1]);
        }

        return ans;
    }
}