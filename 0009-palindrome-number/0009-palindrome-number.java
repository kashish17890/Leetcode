class Solution {
    public boolean isPalindrome(int x) {
        int og=x;
        int rev=0;
        while(x>0){
            int ldigit=x%10;
            rev=rev*10+ldigit;
            x/=10;
        }
        if(og==rev) return true;
        return false;
    }
}