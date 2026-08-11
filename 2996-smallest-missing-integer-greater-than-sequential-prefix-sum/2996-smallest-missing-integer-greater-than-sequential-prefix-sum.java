class Solution {
    public int missingInteger(int[] nums) {
        int seqsum=nums[0];

        
        HashSet<Integer> set=new HashSet<>();
        
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }

        for(int i=1;i<nums.length;i++){
            if(nums[i]-1==nums[i-1]){
                seqsum+=nums[i];
                
            }
            else{
                break;
            }
        }

        while(set.contains(seqsum)){
            
            set.remove(seqsum);
            seqsum+=1;
        }

        return seqsum;
    }
}