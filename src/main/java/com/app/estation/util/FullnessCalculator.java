package com.app.estation.util;

public class FullnessCalculator {
    public static Double calculateFullnessPercentage(double currentCapacity, double maxCapacity) {
        double percentage = (currentCapacity / maxCapacity) * 100;
        return Math.round(percentage * 100.0) / 100.0;
    }
}
