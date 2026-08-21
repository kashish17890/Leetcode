// class Solution {
//     public int[] resultArray(int[] nums) {
//         int[] A=new int[nums.length];
//         int[] B=new int[nums.length];
//         A[0]=nums[1];
//         B[0]=nums[2];

//         int aidx=0;int bidx=0;

//         for(int i=3;i<nums.length;i++){
//             if(A[aidx]>B[bidx]){
//                 A[aidx+1]=nums[i];
//                 aidx++;
//             }else{
//                 B[bidx+1]=nums[i];
//                 bidx++;
//             }
//         }

//         int[] ans=new int[nums.length];
//         for(int i=0;i<A.length;i++){
//             ans[i]=A[i];
//         }
//         int n=aidx+1;
//         for(int i=0;i<B.length;i++){
//             ans[n]=B[i];
//             n++;
//         }

//         return ans;
//     }
// }

class Solution {
    public int[] resultArray(int[] nums) {
        int[] A = new int[nums.length];
        int[] B = new int[nums.length];

        A[0] = nums[0];
        B[0] = nums[1];

        int aidx = 0;
        int bidx = 0;

        for(int i = 2; i < nums.length; i++) {
            if(A[aidx] > B[bidx]) {
                A[++aidx] = nums[i];
            } else {
                B[++bidx] = nums[i];
            }
        }

        int[] ans = new int[nums.length];
        int k = 0;

        for(int i = 0; i <= aidx; i++) {
            ans[k++] = A[i];
        }

        for(int i = 0; i <= bidx; i++) {
            ans[k++] = B[i];
        }

        return ans;
    }
}