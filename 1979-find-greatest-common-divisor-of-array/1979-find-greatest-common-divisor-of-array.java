class Solution {
    public int gcd(int a,int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
    public int findGCD(int[] arr) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            min=Math.min(min,arr[i]);
            max=Math.max(max,arr[i]);
        }
        return gcd(max,min);
    }
}