package com.app.estation.util;
import com.app.estation.dto.ActionDateDto;

import java.text.DecimalFormat;
import java.util.List;

public class PriceCalculator {
 public static String calculatePercentageDifference(double oldPrice, double newPrice) {
    if (0 == oldPrice) {
        return "0%";
    }
    final double difference = oldPrice - newPrice;
    System.out.println("prices is: " + newPrice + " old: " + oldPrice);
    final double percentageDifference = (difference / Math.abs(oldPrice)) * 100;
    System.out.println("difference is: " + percentageDifference);
    final DecimalFormat df = new DecimalFormat("#.##");
    final String formattedPercentageDifference;
    if (difference < 0) {
        formattedPercentageDifference = "+" + df.format(Math.abs(percentageDifference));
    } else if(difference > 0) {
        formattedPercentageDifference = "-" + df.format(Math.abs(percentageDifference));
    }
   System.out.println("difference is: " + formattedPercentageDifference);
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
