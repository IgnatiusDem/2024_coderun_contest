package demignatius.coderun.pairedcodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairedCodes {

    private static StringBuilder lastResult;

    public static String getLastResult() {
        return lastResult.toString();
    }

    private static void addProductToProducts(Map<String, Integer> groupProducts,
                                     List<String> products,
                                     boolean[] product) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < product.length; i++) {
            if (product[i]) {
                sb.append(i);
            }
        }
        String strProduct = sb.toString();

        int count = groupProducts.getOrDefault(strProduct, 0);
        count++;
        if (count == 1) {
            products.add(strProduct);
        }
        groupProducts.put(strProduct, count);
    }

    private static void getProductsFromReader(Map<String, Integer> groupProducts,
                                             List<String> products,
                                             BufferedReader bufferedReader,
                                             int cntProducts) throws IOException {
        int i = 0;
        while (bufferedReader.ready() && i < cntProducts) {
            boolean[] product = new boolean[10];
            char number = (char) bufferedReader.read();
            product[number - '0'] = true;
            while (bufferedReader.ready()) {
                number = (char) bufferedReader.read();
                if (number == ' ' || number == '\n') {
                    break;
                }
                product[number - '0'] = true;
            }
            addProductToProducts(groupProducts, products, product);
            i++;
        }
    }

    private static boolean isCanBePair(String product1,
                                      String product2) {
        for (int i = 0; i < product1.length(); i++) {
            char letter = product1.charAt(i);

            if (product2.contains("" + letter)) {
                return true;
            }
        }
        return false;
    }

    public static void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        lastResult = new StringBuilder();

        int cntProducts = Integer.parseInt(reader.readLine());

        Map<String, Integer> groupProducts = new HashMap<>();
        List<String> products = new ArrayList<>();

        getProductsFromReader(groupProducts, products, reader, cntProducts);

        long cnt = 0;

        for (int i = 0; i < products.size(); i++) {
            cnt += ((long) groupProducts.get(products.get(i)) * groupProducts.get(products.get(i)) - groupProducts.get(products.get(i)))/2;
            for (int j = i + 1; j < products.size(); j++) {
                if (isCanBePair(products.get(i), products.get(j))) {
                    cnt += (long) groupProducts.get(products.get(i)) * groupProducts.get(products.get(j));
                }
            }
        }

        writer.write(String.valueOf(cnt));
        writer.write("\n");
        writer.flush();

        lastResult.append(cnt);
    }
}
