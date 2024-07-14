public class palidrom {

    public static boolean isPalindrome(String str) {
        if (str == null || str.isEmpty()) {
            return true; // Empty string or null is considered a palindrome
        }

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            char leftChar = Character.toLowerCase(str.charAt(left));
            char rightChar = Character.toLowerCase(str.charAt(right));

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else {
                if (leftChar != rightChar) {
                    return false;
                }
                left++;
                right--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String str1 = "A man, a plan, a canal: Panama";
        System.out.println("\"" + str1 + "\" is a palindrome: " + isPalindrome(str1)); // Output: true

        String str2 = "race a car";
        System.out.println("\"" + str2 + "\" is a palindrome: " + isPalindrome(str2)); // Output: false

        String str3 = "Was it a car or a cat I saw?";
        System.out.println("\"" + str3 + "\" is a palindrome: " + isPalindrome(str3)); // Output: true

        String str4 = "No 'x' in Nixon";
        System.out.println("\"" + str4 + "\" is a palindrome: " + isPalindrome(str4)); // Output: true
    }
}
