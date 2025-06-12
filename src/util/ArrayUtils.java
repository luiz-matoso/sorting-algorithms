package util;

import java.util.Random;

public class ArrayUtils {

    // Cria um vetor com números aleatórios
    public static int[] createRandomArray(int size, int seed) {
        int[] arr = new int[size];
        Random rand = new Random(seed);
        for (int i = 0; i < size; i++) {
            // Gera números em um intervalo até que bom
            // para que o Counting Sort não crie um vetor de contagem gigante.
            arr[i] = rand.nextInt(size * 2);
        }
        return arr;
    }

    // Copia um vetor
    public static int[] copyArray(int[] source, int size) {
        int[] destination = new int[size];
        for (int i = 0; i < size; i++) {
            destination[i] = source[i];
        }
        return destination;
    }
}