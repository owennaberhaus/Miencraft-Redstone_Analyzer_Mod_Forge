package net.owen.redstoneanalyzermod.client;

import java.util.LinkedList;
import java.util.List;

public class OscilloscopeData {
    private static final int MAX_SAMPLES = 200;
    private static final LinkedList<Integer> samples = new LinkedList<>();

    public static void addSample(int value) {
        if (samples.size() > MAX_SAMPLES) {
            samples.removeFirst();
        }
        samples.add(value);
    }

    public static List<Integer> getSamples() {
        return samples;
    }

    public static void clear() {
        samples.clear();
    }
}
