class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String doubled = s + s;
        // Search inside doubled excluding the first and last characters
        return doubled.substring(1, doubled.length() - 1).contains(s);
    }
}