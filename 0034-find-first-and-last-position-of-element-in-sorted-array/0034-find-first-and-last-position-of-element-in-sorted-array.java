class Solution {
    public int[] searchRange(int[] arr, int target) {
        int[] ans=new int[2];
        ans[0]=-1;
        ans[1]=-1;
        int l=0;int h=arr.length-1;
        while(l<=h){
            int mid=l+(h-l)/2;
            if(arr[mid]>=target){
                if(arr[mid]==target) ans[0]=mid;
                h=mid-1;
            }
            else{
                l=mid+1;
            }
        }
        l=0;h=arr.length-1;
        while(l<=h){
            int mid=l+(h-l)/2;
            if(arr[mid]<=target){
                if(arr[mid]==target) ans[1]=mid;
                l=mid+1;
            }
            else{
                h=mid-1;
            }
        }
        return ans;
    }
}