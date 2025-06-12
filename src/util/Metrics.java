package util;

public class Metrics {
    public long swaps = 0;
    public long iterations = 0;
    public long executionTime = 0; // em nanossegundos

    @Override
    public String toString() {
        return "Tempo: " + executionTime + " ns, Trocas: " + swaps + ", Iterações: " + iterations;
    }
}