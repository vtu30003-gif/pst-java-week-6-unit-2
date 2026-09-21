import java.io.*;

public class Solution {

    public static int palindromeIndex(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {

                // Try removing the left character
                if (isPalindrome(s, left + 1, right)) {
                    return left;
                }

                // Otherwise remove the right character
                if (isPalindrome(s, left, right - 1)) {
                    return right;
                }

                return -1;
            }

            left++;
            right--;
        }

        // Already a palindrome
        return -1;
    }

    private static boolean isPalindrome(String s, int left, int right) {

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(br.readLine().trim());

        while (q-- > 0) {

            String s = br.readLine().trim();

            System.out.println(palindromeIndex(s));
        }

        br.close();
    }
}
