import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Populate frequency count for target pattern p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int pLen = p.length();
        int sLen = s.length();

        for (int i = 0; i < sLen; i++) {
            // Add current character to sliding window count
            sCount[s.charAt(i) - 'a']++;

            // Remove character outside the left of the window
            if (i >= pLen) {
                sCount[s.charAt(i - pLen) - 'a']--;
            }

            // Compare window frequency with pattern frequency
            if (i >= pLen - 1 && Arrays.equals(pCount, sCount)) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }
}