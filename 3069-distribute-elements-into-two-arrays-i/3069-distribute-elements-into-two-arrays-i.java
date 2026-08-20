import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] resultArray(int[] nums) {
        // Step 1: Initialize list containers for distribution
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // Step 2: Seed the initial elements according to rules
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        
        // Step 3: Simulate distribution based on last element comparisons
        for (int i = 2; i < nums.length; i++) {
            int last1 = arr1.get(arr1.size() - 1);
            int last2 = arr2.get(arr2.size() - 1);
            
            if (last1 > last2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Step 4: Concatenate arr1 and arr2 into the final array
        int[] result = new int[nums.length];
        int index = 0;
        
        for (int num : arr1) {
            result[index++] = num;
        }
        for (int num : arr2) {
            result[index++] = num;
        }
        
        return result;
    }
}
