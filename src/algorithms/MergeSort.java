package algorithms;

import util.Metrics;

public class MergeSort {

    public static Metrics sort(int[] arr, int size) {
        Metrics metrics = new Metrics();
        if (arr == null || size <= 1) {
            return metrics;
        }
        int[] temp = new int[size]; // Vetor temporário
        mergeSortRecursive(arr, temp, 0, size - 1, metrics);
        return metrics;
    }

    private static void mergeSortRecursive(int[] arr, int[] temp, int left, int right, Metrics metrics) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSortRecursive(arr, temp, left, mid, metrics);
            mergeSortRecursive(arr, temp, mid + 1, right, metrics);

            merge(arr, temp, left, mid, right, metrics);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, Metrics metrics) {
        // Copia a parte correspondente para o vetor temporário
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
            metrics.swaps++; // Considera a cópia para o array temp como se fosse um "troc"
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            metrics.iterations++;
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            metrics.swaps++; // Conta a cópia de volta para o array original
            k++;
        }

        //  Se tiver, Copia o resto da metade esquerda
        while (i <= mid) {
            metrics.iterations++;
            arr[k] = temp[i];
            metrics.swaps++;
            k++;
            i++;
        }

        // Copia o resto da metade direita
        while (j <= right) {
            metrics.iterations++;
            arr[k] = temp[j];
            metrics.swaps++;
            k++;
            j++;
        }
    }
}