public class Linefinder{

    public int maxArea(int[] height) {
        int left = 0; // Initialize left pointer
        int right = height.length - 1; // Initialize right pointer
        int maxArea = 0; // Initialize max area

        while (left < right) {

            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;

            // Update max area if the current area is greater
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointers inward
            if (height[left] < height[right]) {
                // Move left pointer to the right
                left++;
            } else {
                // Move right pointer to the left
                right--;
            }
        }

        return maxArea; // Return the maximum area found
    }

    public static void main(String[] args) {
        Linefinder solution = new Linefinder();

        // Test cases
        int[] height1 = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max area: " + solution.maxArea(height1)); // Output: 49

        int[] height2 = {1,1};
        System.out.println("Max area: " + solution.maxArea(height2)); // Output: 1
    }
}
