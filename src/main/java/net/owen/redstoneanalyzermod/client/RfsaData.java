package net.owen.redstoneanalyzermod.client;

import java.util.LinkedList;
import java.util.List;

public class RfsaData {
    private static final int MAX_SAMPLES = 200;
    private static final LinkedList<Integer> samples = new LinkedList<>();

    private static int lastValue = -1;
    private static int toggleCount = 0;

    public static void addSample(int value) {
        if (samples.size() > MAX_SAMPLES) {
            samples.removeFirst();
        }
        samples.add(value);

        // Count toggles
        if (lastValue != -1 && lastValue != value) {
            toggleCount++;
        }
        lastValue = value;
    }

    public static List<Integer> getSamples() {
        return samples;
    }

    public static int getToggleCount() {
        return toggleCount;
    }

    public static void clear() {
        samples.clear();
        toggleCount = 0;
        lastValue = -1;
    }
}
