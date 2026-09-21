import java.io.*;

public class Solution {

    public static int marsExploration(String s) {

        int count = 0;

        String message = "SOS";

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) != message.charAt(i % 3)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine().trim();

        System.out.println(marsExploration(s));

        br.close();
    }
}
