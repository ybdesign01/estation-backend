package com.app.estation.util;

import java.text.DecimalFormat;

public class PriceCalculator {
    public static String calculatePercentageDifference(double oldPrice, double newPrice) {
        if (0 == oldPrice) {
            return "0%";
        }
        final double difference = newPrice - oldPrice;
        final double percentageDifference = (difference / oldPrice) * 100;
        final DecimalFormat df = new DecimalFormat("#.##");
        final String formattedPercentageDifference = df.format(percentageDifference);
        return formattedPercentageDifference + "%";
    }
}
