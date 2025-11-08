package GeneticAlgorithm.Utils;

import java.util.List;

public class SearchHelpers {

    public static int lowerBound(List<Integer> arr, int target) {
        int ans = arr.size();
        int left = 0;
        int right = arr.size() - 1; 

        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr.get(mid) >= target) {
                ans = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return ans;
    }
    
}
