package com.app.estation.util;
import com.app.estation.dto.ActionDateDto;

import java.text.DecimalFormat;
import java.util.List;

public class PriceCalculator {
    public static String calculatePercentageDifference(double oldPrice, double newPrice) {
    if (0 == oldPrice) {
        return "0%";
    }
    final double difference = newPrice - oldPrice;
    final double percentageDifference = (difference / oldPrice) * 100;
    final DecimalFormat df = new DecimalFormat("#.##");
    df.setPositivePrefix("+");
    df.setNegativePrefix("-");
    final String formattedPercentageDifference = df.format(percentageDifference);
    return formattedPercentageDifference + "%";
}
    

    public static Double calculateTotal(List<ActionDateDto> entrees) {
        Double total = 0.0;
        for (ActionDateDto entree : entrees) {
            total += entree.getMontant();
        }
        return total;
    }
}
