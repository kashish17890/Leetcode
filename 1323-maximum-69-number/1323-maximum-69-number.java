class Solution {
    public int maximum69Number (int num) {
        // String str=Integer.toString(num);
        // int[] arr=new int[str.length];

        // for(int i=0;i<str.length();i++){
        //     arr[i]=Characters.getNumericValue(str.charAt(i));
        // }

        // for(int i=0;i<arr.length;i++){
        //     if(arr[i]==9){
        //         arr[i]=6;
        //     }
        //     else {
        //         arr[i]=9;
        //     }

            
        // }

        char[] ans = String.valueOf(num).toCharArray();
        for (int i = 0; i < ans.length; ++i) {
            if (ans[i] == '6') {
                ans[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(ans));

    }
}