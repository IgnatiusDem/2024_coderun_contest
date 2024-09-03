package demignatius.coderun.gcd;

import java.io.*;
import java.math.BigInteger;

public class GreatestCommonDivisor {
    private static StringBuilder lastResult;

    public static String getLastResult() {
        return lastResult.toString();
    }

    private static BigInteger getNumber(BufferedReader reader) throws IOException {
        String[] nums = reader.readLine().split(" ");
        BigInteger number = new BigInteger("1");
        for (String num : nums) {
            BigInteger next = new BigInteger(num);
            number = number.multiply(next);
        }
        return number;
    }

    public static void findGCD() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        lastResult = new StringBuilder();

        int cntFirst = Integer.parseInt(reader.readLine());
        BigInteger first = getNumber(reader);

        int cntSecond = Integer.parseInt(reader.readLine());
        BigInteger second = getNumber(reader);

        BigInteger limit = new BigInteger("1000000000");

        BigInteger gdc = first.gcd(second);

        String result;
        if (gdc.compareTo(limit) >= 0) {
            String strGdc = gdc.toString();
            result = strGdc.substring(strGdc.length() - 9);
        } else {
            result = gdc.toString();
        }

        writer.write(result);
        writer.write("\n");
        writer.flush();
        lastResult.append(result);
    }
}
