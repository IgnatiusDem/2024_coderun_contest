package demignatius.coderun.luckynumber;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class LuckyNumber {
    private static StringBuilder lastResult;

    public static String getLastResult() {
        return lastResult.toString();
    }

    public static void solution() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(System.out)
        );

        lastResult = new StringBuilder();

        char[] line = reader.readLine().toCharArray();
        int n = line.length;
        int[] number = new int[n];

        for (int i = 0; i < n; i++) {
            number[i] = (int) line[i] - (int) '0';
        }

        long leftSum = 0;
        for (int i = 0; i < n / 2; i++) {
            leftSum += number[i];
        }

        long rightSum = 0;
        for (int i = n / 2; i < n; i++) {
            rightSum += number[i];
        }

        int remain = 1;
        int i = number.length - 1;
        while (i >= 0 && remain > 0) {
            int prev = number[i];
            number[i] += remain;

            if (number[i] >= 10) {
                remain = number[i] / 10;
                number[i] %= 10;
            } else {
                remain = 0;
            }

            if (i >= n / 2) {
                rightSum = rightSum - prev + number[i];
            } else {
                leftSum = leftSum - prev + number[i];
            }
            i--;
        }

        if (remain > 0) {
            Arrays.fill(number, 0);
            number[n / 2 - 1] = 1;
            number[n - 1] = 1;
            leftSum = 1;
            rightSum = 1;
        }


        if (leftSum < rightSum) {
            remain = 0;
            i = number.length - 1;

            while (i >= n / 2) {
                int prev = number[i];
                number[i] += remain;

                if (number[i] >= 10) {
                    remain = number[i] / 10;
                    number[i] %= 10;
                } else {
                    remain = 0;
                }

                rightSum = rightSum - prev + number[i];
                prev = number[i];

                if (remain == 0 && leftSum >= rightSum) {
                    break;
                }

                if (number[i] > 0) {
                    number[i] = 0;
                    remain++;
                }
                rightSum = rightSum - prev + number[i];
                i--;
            }

            while (i >= 0 && remain > 0) {
                int prev = number[i];
                number[i] += remain;

                if (number[i] >= 10) {
                    remain = number[i] / 10;
                    number[i] %= 10;
                } else {
                    remain = 0;
                }

                leftSum = leftSum - prev + number[i];
                i--;
            }
        }

        if (leftSum > rightSum) {
            long dif = leftSum - rightSum;
            i = number.length - 1;

            while (i >= n / 2 && dif != 0) {
                int prev = number[i];
                number[i] += (int) dif;

                if (number[i] >= 9) {
                    number[i] = 9;
                }

                dif = dif + prev - number[i];
                rightSum = rightSum - prev + number[i];
                i--;
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int num : number) {
            sb.append(num);
        }

        writer.write(sb.toString());
        writer.write("\n");
        writer.flush();
        lastResult = sb;
    }
}