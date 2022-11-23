package com.example.RecyclerView.Classes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Utils {
    public static String formatCurrency(int number) {
        NumberFormat format = new DecimalFormat("#,###");
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance(Locale.US));//Or default locale
        String t = format.format(number);
        t = t.replace(',', '.');
        return t + "₫";
    }
}
