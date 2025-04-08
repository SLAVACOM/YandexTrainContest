package com.slavacom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class TaskB {
    public static class Log {
        int id;
        int time;
        char status;

        public Log(String[] log) {
            this.id = Integer.parseInt(log[3]);
            this.time = Integer.parseInt(log[0]) * 24 * 60 + Integer.parseInt(log[1]) * 60 + Integer.parseInt(log[2]);
            this.status = log[4].charAt(0);
        }

        @Override
        public String toString() {
            return "Log{" +
                    "id=" + id +
                    ", time=" + time +
                    ", status=" + status +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        Log[] logs = new Log[n];

        HashMap<Integer, Integer> rockets = new HashMap<>();
        LinkedHashMap<Integer, Integer> rocketsStatus = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            logs[i] = new Log(reader.readLine().split(" "));
        }
        reader.close();
        Arrays.sort(logs, Comparator.comparing((Log log) -> log.time).thenComparing(log -> log.status));

        for (Log log : logs) {
            switch (log.status) {
                case 'A': {
                    rocketsStatus.put(log.id, log.time);
                    break;
                }
                case 'S':
                case 'C':
                    int finishTime = log.time - rocketsStatus.getOrDefault(log.id, 0);
                    rockets.put(log.id, rockets.getOrDefault(log.id, 0) + finishTime);
            }
        }

        List<Integer> sortedKeys = new ArrayList<>(rockets.keySet());
        Collections.sort(sortedKeys);

        for (Integer key : sortedKeys) {
            writer.write(rockets.get(key) + " ");
        }

        writer.flush();
        writer.close();
    }
}
