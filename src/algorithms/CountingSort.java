package algorithms;

import util.Metrics;

public class CountingSort {

    public static Metrics sort(int[] arr, int size) {
        Metrics metrics = new Metrics();
        if (arr == null || size == 0) {
            return metrics;
        }

        // 1. Encontra o valor máximo para definir o tamanho do array de contagem
        int maxVal = arr[0];
        for (int i = 1; i < size; i++) {
            metrics.iterations++;
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }

        int countSize = maxVal + 1;
        int[] count = new int[countSize];

        // 2. Conta a frequência de cada elemnto
        for (int i = 0; i < size; i++) {
            count[arr[i]]++;
            metrics.iterations++;
        }

        // 3. Calcular a soma que se acumula
        for (int i = 1; i < countSize; i++) {
            count[i] += count[i - 1];
            metrics.iterations++;
        }

        // 4. Constroi o vetor de saída
        int[] output = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            int value = arr[i];
            int position = count[value] - 1;
            output[position] = value;
            count[value]--;
            metrics.swaps++; // Cada elemento é movido uma vez para o vetor de saída
            metrics.iterations++;
        }

        // 5. Copia o vetor ordenado de volta para o original
        for (int i = 0; i < size; i++) {
            arr[i] = output[i];
            metrics.swaps++; // Esta cópia final é considerada como se fosse uma "troca"
        }

        return metrics;
    }
}