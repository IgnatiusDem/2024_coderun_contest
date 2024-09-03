package demignatius.coderun.workschedule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class WorkSchedule {
    private static StringBuilder lastResult;

    public static String getLastResult() {
        return lastResult.toString();
    }
    
    public static void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        lastResult = new StringBuilder();

        // count of tasks
        int n = Integer.parseInt(reader.readLine());
        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            String[] values = line.split(" ");

            int day = Integer.parseInt(values[0]);
            int stress = Integer.parseInt(values[1]);

            Task task = new Task(day, stress);
            tasks[i] = task;
        }

        Arrays.sort(tasks);

        long totalMinStress = getTotalMinStress(tasks);

        writer.write(String.valueOf(totalMinStress));
        writer.write("\n");
        writer.flush();
        lastResult.append(totalMinStress);
    }

    private static long getTotalMinStress(Task[] tasks) {
        int last = tasks.length - 1;
        int lastDay = tasks[last].day;
        long totalMinStress = 0;

        PriorityQueue<Integer> max =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int day = lastDay, i = last; day > 0; day--) {
            while (i >= 0 && tasks[i].day >= day) {
                totalMinStress += tasks[i].stress;
                max.add(tasks[i].stress);
                i--;
            }

            if(!max.isEmpty()){
                totalMinStress -= max.poll();
            }
        }
        return totalMinStress;
    }
}
