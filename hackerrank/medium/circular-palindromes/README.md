# Circular Palindromes

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A *palindrome* is a string that reads the same from left to right as it does from right to left.

Given a string, $S$, of $N$ lowercase English letters, we define a *$k$-length rotation* as cutting the first $k$ characters from the beginning of $S$ and appending them to the end of $S$. For each $S$, there are $N$ possible $k$-length rotations (where $0 \le k \lt N$). See the *Explanation* section for examples.

Given $N$ and $S$, find all $N$ $k$-length rotations of $S$; for each rotated string, $S_k$, print the maximum possible length of any palindromic substring of $S_k$ on a new line.

**Input Format**

The first line contains an integer, $N$ (the length of $S$).	
The second line contains a single string, $S$.

**Constraints**

- $1 \le N \le 5 \times 10^5$
- $0 \le k \lt N$
- $\textit{S is comprised of lowercase English letters.}$

**Output Format**

There should be $N$ lines of output, where each line $k$ contains an integer denoting the maximum length of any palindromic substring of rotation $S_k$.

**Sample Input 0**

    13
    aaaaabbbbaaaa
    
**Sample Output 0**

    12
    12
    10
    8
    8
    9
    11
    13
    11
    9
    8
    8
    10

**Sample Input 1**

    7
    cacbbba
    
**Sample Output 1**

    3
    3
    3
    3
    3
    3
    3


**Sample Input 2**

    12
    eededdeedede

**Sample Output 2**

    5
    7
    7
    7
    7
    9
    9
    9
    9
    7
    5
    4

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-21T19:34:52.588Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static int[] radius;
    static int[][] sparse;
    static int[] log;

    // Manacher's algorithm on transformed string
    static void manacher(int[] a) {

        int n = a.length;
        radius = new int[n];

        int left = 0;
        int right = 0;
        int center = 0;

        for (int i = 0; i < n; i++) {

            if (i < right) {
                radius[i] =
                    Math.min(radius[2 * center - i],
                             right - i);
            } else {
                radius[i] = 1;
            }

            while (i - radius[i] >= 0 &&
                   i + radius[i] < n &&
                   a[i - radius[i]] == a[i + radius[i]]) {

                radius[i]++;
            }

            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }
        }

        // Convert to palindrome radius
        for (int i = 0; i < n; i++) {
            radius[i]--;
        }
    }

    // Build Sparse Table for range maximum
    static void buildSparseTable() {

        int n = radius.length;

        log = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        int levels = log[n] + 1;

        sparse = new int[levels][n];

        System.arraycopy(radius, 0, sparse[0], 0, n);

        for (int k = 1; k < levels; k++) {

            int len = 1 << k;
            int half = len >> 1;

            for (int i = 0; i + len <= n; i++) {

                sparse[k][i] =
                    Math.max(
                        sparse[k - 1][i],
                        sparse[k - 1][i + half]
                    );
            }
        }
    }

    // Range maximum query
    static int rangeMax(int left, int right) {

        if (left > right) {
            return 0;
        }

        int length = right - left + 1;
        int k = log[length];

        return Math.max(
            sparse[k][left],
            sparse[k][right - (1 << k) + 1]
        );
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
            new BufferedReader(
                new InputStreamReader(System.in)
            );

        int n = Integer.parseInt(br.readLine().trim());

        String s = br.readLine().trim();

        /*
         * Duplicate the string because we need
         * all circular rotations.
         */
        String doubled = s + s;

        /*
         * Transform:
         *
         *     abc
         *
         * becomes
         *
         *     #a#b#c#
         *
         * We use 26 as the separator because
         * input contains only lowercase letters.
         */
        int length = 4 * n + 1;

        int[] transformed = new int[length];

        Arrays.fill(transformed, 26);

        for (int i = 0; i < 2 * n; i++) {
            transformed[2 * i + 1] =
                doubled.charAt(i) - 'a';
        }

        /*
         * Find every palindrome in the doubled string.
         */
        manacher(transformed);

        /*
         * RMQ allows us to quickly find the largest
         * palindrome radius in an interval.
         */
        buildSparseTable();

        StringBuilder output = new StringBuilder();

        /*
         * Process every rotation.
         */
        for (int rotation = 0; rotation < n; rotation++) {

            /*
             * Start and end positions of this rotation
             * in the transformed string.
             */
            int L = rotation * 2 + 1;

            int R =
                (rotation + n - 1) * 2 + 1;

            /*
             * Binary search the answer.
             */
            int low = 1;
            int high = n;
            int answer = 1;

            while (low <= high) {

                int mid = (low + high) >>> 1;

                /*
                 * For a palindrome of length mid to fit
                 * inside [L, R], its center must be inside:
                 *
                 * [L + mid - 1, R - mid + 1]
                 */
                int qLeft = L + mid - 1;
                int qRight = R - mid + 1;

                if (qLeft <= qRight &&
                    rangeMax(qLeft, qRight) >= mid) {

                    answer = mid;
                    low = mid + 1;

                } else {
                    high = mid - 1;
                }
            }

            output.append(answer).append('\n');
        }

        System.out.print(output);
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/circular-palindromes/problem)