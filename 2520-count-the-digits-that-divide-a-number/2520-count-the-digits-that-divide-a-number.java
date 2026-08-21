class Solution {
    public int countDigits(int n) {
        int count=0;
        int og=n;
        while(n>0){
            int ldigit=n%10;
            if(og%ldigit==0) count++;
            ldigit=0;
            n/=10;
        }
        return count;
    }
}