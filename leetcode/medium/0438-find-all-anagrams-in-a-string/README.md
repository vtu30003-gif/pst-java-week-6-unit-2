# Find All Anagrams in a String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in  **any order**.

 

 **Example 1:** 

```
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

```

 **Example 2:** 

```
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

```

 

 **Constraints:** 

- 1 <= s.length, p.length <= 3 * 104
- s and p consist of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 12 ms (beats 52.12%)  
**Memory:** 46.8 MB (beats 70.81%)  
**Submitted:** 2026-09-21T19:29:52.882Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/find-all-anagrams-in-a-string/)