package demignatius.coderun.largestsquare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LargestSquare {
    private static StringBuilder lastResult;

    public static String getLastResult() {
        return lastResult.toString();
    }

    public static void findLargestSquare() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        lastResult = new StringBuilder();

        String[] values = reader.readLine().split(" ");
        int n = Integer.parseInt(values[0]);
        int m = Integer.parseInt(values[1]);

        char[][] matrix = new char[n][m];

        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line[j].charAt(0);
            }
        }

        int maxLength = 0;
        int[][] dp = new int[n + 1][m + 1];

        int x = 0;
        int y = 0;


        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (matrix[i - 1][j - 1] != '0') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j - 1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                    dp[i][j]++;

                    if (maxLength <= dp[i][j]) {
                        maxLength = dp[i][j];

                        x = i - maxLength + 1;
                        y = j - maxLength + 1;

                    }
                }
            }
        }

        String result = String.format("%d\n%d %d", maxLength, x, y);

        writer.write(result);
        writer.write("\n");
        writer.flush();

        lastResult.append(result);
    }
}
