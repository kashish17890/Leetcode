class Solution {
    public void reverseString(char[] arr) {
        int start=0;int end=arr.length-1;
        while(start<=end){
            char temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }
}