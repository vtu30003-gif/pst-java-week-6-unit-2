# Repeated Substring Pattern

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a string `s`, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

 

 **Example 1:** 

```
Input: s = "abab"
Output: true
Explanation: It is the substring "ab" twice.

```

 **Example 2:** 

```
Input: s = "aba"
Output: false

```

 **Example 3:** 

```
Input: s = "abcabcabcabc"
Output: true
Explanation: It is the substring "abc" four times or the substring "abcabc" twice.

```

 

 **Constraints:** 

- 1 <= s.length <= 104
- s consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 75 ms (beats 43.60%)  
**Memory:** 46.8 MB (beats 66.51%)  
**Submitted:** 2026-09-21T19:26:10.912Z  

```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String doubled = s + s;
        // Search inside doubled excluding the first and last characters
        return doubled.substring(1, doubled.length() - 1).contains(s);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/repeated-substring-pattern/)