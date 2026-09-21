# Palindrome Index

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a string of lowercase letters in the range ascii[a-z], determine the index of a character that can be removed to make the string a [palindrome](https://en.wikipedia.org/wiki/Palindrome).  There may be more than one solution, but any will do.  If the word is already a palindrome or there is no solution, return _-1_.  Otherwise, return the index of a character to remove.  

**Example**  
$s = \text{"bcbc"}$  

Either remove *'b'* at index $0$ or *'c'* at index $3$.  

**Function Description**  

Complete the *palindromeIndex* function in the editor below.    

palindromeIndex has the following parameter(s):  

- *string s:* a string to analyze  

**Returns**  

- *int:* the index of the character to remove or $-1$  

**Input Format**

The first line contains an integer $q$, the number of queries.		
Each of the next $q$ lines contains a query string $s$.

**Constraints**

- $1 \le q \le 20$  
- $1 \le \text{length of } s \le 10^5 + 5$  
- All characters are in the range ascii[a-z].

**Output Format**

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-21T19:30:59.432Z  

```java
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

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/palindrome-index/problem)