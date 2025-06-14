import algorithms.MergeSort;
import algorithms.CountingSort;
import util.ArrayUtils;
import util.Metrics;

public class Main {

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 500000, 1000000};
        int[] seeds = {42, 123, 987, 555, 10}; // 5 seeds diferentes para 5 rodadas

        System.out.printf("%-12s | %-50s | %-50s\n", "Tamanho", "Merge Sort (Média de 5 rodadas)", "Counting Sort (Média de 5 rodadas)");
        System.out.println(new String(new char[120]).replace('\0', '-'));

        for (int size : sizes) {
            long totalMergeTime = 0, totalMergeSwaps = 0, totalMergeIterations = 0;
            long totalCountingTime = 0, totalCountingSwaps = 0, totalCountingIterations = 0;

            for (int seed : seeds) {
                int[] originalArray = ArrayUtils.createRandomArray(size, seed);

                // Teste do Merge Sort
                int[] arrayForMerge = ArrayUtils.copyArray(originalArray, size);
                long startTime = System.nanoTime();
                Metrics mergeMetrics = MergeSort.sort(arrayForMerge, size);
                long endTime = System.nanoTime();
                mergeMetrics.executionTime = endTime - startTime;

                totalMergeTime += mergeMetrics.executionTime;
                totalMergeSwaps += mergeMetrics.swaps;
                totalMergeIterations += mergeMetrics.iterations;

                // Teste do Couting Sort
                int[] arrayForCounting = ArrayUtils.copyArray(originalArray, size);
                startTime = System.nanoTime();
                Metrics countingMetrics = CountingSort.sort(arrayForCounting, size);
                endTime = System.nanoTime();
                countingMetrics.executionTime = endTime - startTime;

                totalCountingTime += countingMetrics.executionTime;
                totalCountingSwaps += countingMetrics.swaps;
                totalCountingIterations += countingMetrics.iterations;
            }

            // Calcular todas as medias e fazer a conversão
            int numRounds = seeds.length;

            // Métricas do Merge Sort
            long avgMergeTimeNs = totalMergeTime / numRounds;
            double avgMergeTimeMs = avgMergeTimeNs / 1_000_000.0; // Conversão para MS
            long avgMergeSwaps = totalMergeSwaps / numRounds;
            long avgMergeIterations = totalMergeIterations / numRounds;

            // Métricas do Counting Sort
            long avgCountingTimeNs = totalCountingTime / numRounds;
            double avgCountingTimeMs = avgCountingTimeNs / 1_000_000.0; // Conversão para MS
            long avgCountingSwaps = totalCountingSwaps / numRounds;
            long avgCountingIterations = totalCountingIterations / numRounds;

            // Print (Decidi mostrar o MS também para depois quando realizar os gráficos ficar mais fácil de visualizar)
            String mergeResult = String.format("Tempo: %-10d ns (%-7.2f ms), Trocas: %-12d, Iterações: %d",
                    avgMergeTimeNs, avgMergeTimeMs, avgMergeSwaps, avgMergeIterations);

            String countingResult = String.format("Tempo: %-10d ns (%-7.2f ms), Trocas: %-12d, Iterações: %d",
                    avgCountingTimeNs, avgCountingTimeMs, avgCountingSwaps, avgCountingIterations);

            System.out.printf("%-12d | %-50s | %-50s\n", size, mergeResult, countingResult);
        }
    }
}