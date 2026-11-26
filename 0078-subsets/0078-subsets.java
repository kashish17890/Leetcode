class Solution {
    public void subc(List<List<Integer>> list, List<Integer> ans, int[] nums, int idx){
        if(idx==nums.length){
            list.add(new ArrayList<>(ans));
            return;
        }
        ans.add(nums[idx]);
        subc(list,ans,nums,idx+1);
        ans.remove(ans.size()-1);
        subc(list,ans,nums,idx+1);
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        List<Integer> ans=new ArrayList<>();
        subc(list,ans,nums,0);
        return list;
    }
}