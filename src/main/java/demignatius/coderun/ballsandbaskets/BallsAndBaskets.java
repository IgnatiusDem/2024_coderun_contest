package demignatius.coderun.ballsandbaskets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Set;
import java.util.TreeSet;

public class BallsAndBaskets {
    public static final int MOD = 1_000_000_007;

    private static StringBuilder lastResult;

    public static String getLastResult() {
        return lastResult.toString();
    }

    static void findMethodsOfSelection() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        lastResult = new StringBuilder();

        // Количество корзин
        int cntBaskets = Integer.parseInt(reader.readLine());
        long[] baskets = new long[cntBaskets + 1];

        // Произведение элементов
        long[] dp = new long[cntBaskets + 1];
        dp[0] = 1;

        // Чтение изначального количества шаров в каждой корзине
        for (int i = 1; i < baskets.length; i++) {
            baskets[i] = getBasket(reader);
            dp[i] = (dp[i - 1] * baskets[i]) % MOD;
        }

        // Количество запросов
        int cntRequests = Integer.parseInt(reader.readLine());

        // Отрезки для увеличения
        Set<Segment> segments = new TreeSet<>();

        for (int i = 0; i < cntRequests; i++) {
            String[] query = reader.readLine().split(" ");
            int type = Integer.parseInt(query[0]);
            int l = Integer.parseInt(query[1]);
            int r = Integer.parseInt(query[2]);

            if (type == 0) {
                segments.add(new Segment(l, r));
            } else {
                updateDp(baskets, dp, segments);
                // Запрос на подсчет способов выбора шаров
                // (a / b) mod p = ((a mod p) * (b^(-1) mod p)) mod p
                // b^(-1) mod p = b^(p - 2) mod p
                long cntMethods = (dp[r] % MOD * binpow(dp[l - 1], MOD - 2) % MOD) % MOD;
                writer.write(String.valueOf(cntMethods));
                writer.write("\n");
                lastResult.append(cntMethods);
                if (i + 1 < cntRequests) {
                    lastResult.append("\n");
                }
            }
        }
        writer.flush();
    }

    // бинарное возведение в степень по модулю
    static long binpow(long a, long n) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            n >>= 1;
        }
        return res;
    }

    private static void updateDp(long[] baskets, long[] dp, Set<Segment> segments) {
        if (segments.isEmpty()) {
            return;
        }
        for (int i = 1; i < baskets.length; i++) {
            int cnt = 0;
            for (Segment segment : segments) {
                if (i >= segment.l && i <= segment.r) {
                    cnt++;
                }
            }
            baskets[i] += cnt;
            dp[i] = (dp[i - 1] * baskets[i]) % MOD;
        }
        segments.clear();
    }

    private static long getBasket(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char number = (char) reader.read();
        while (number != ' ' && number != '\n') {
            sb.append(number);
            number = (char) reader.read();
        }
        return Long.parseLong(sb.toString());
    }
}
