

import java.util.Random;

public class  kthlargest {

    private static final Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        // Convert k-th largest to index
        int index = nums.length - k;
        return quickselect(nums, 0, nums.length - 1, index);
    }

    private int quickselect(int[] nums, int left, int right, int k) {
        if (left == right) { // If the list contains only one element,
            return nums[left]; // return that element
        }

        // Select a random pivot_index
        int pivotIndex = left + random.nextInt(right - left + 1);

        pivotIndex = partition(nums, left, right, pivotIndex);

        // The pivot is in its final sorted position
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickselect(nums, left, pivotIndex - 1, k);
        } else {
            return quickselect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        // Move pivot to end
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        // Move all smaller elements to the left
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // Move pivot to its final place
        swap(nums, right, storeIndex);

        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        kthlargest  solution = new  kthlargest ();
        int[] nums1 = {3,2,1,5,6,4};
        int k1 = 2;
        System.out.println("The " + k1 + "th largest element is " + solution.findKthLargest(nums1, k1)); // Output: 5

        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int k2 = 4;
        System.out.println("The " + k2 + "th largest element is " + solution.findKthLargest(nums2, k2)); // Output: 4
    }
}

