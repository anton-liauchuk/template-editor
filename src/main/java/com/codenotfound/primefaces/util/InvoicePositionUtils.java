package com.codenotfound.primefaces.util;

import com.codenotfound.primefaces.InvoicePosition;

import java.util.ArrayList;
import java.util.List;

public class InvoicePositionUtils {

    public static List<InvoicePosition> generate(int quantity) {
        List<InvoicePosition> result = new ArrayList<>();

        for (int i = 1; i <= quantity; i++) {
            InvoicePosition position = new InvoicePosition();
            position.setPosition(1);
            position.setItemNo("B1");
            position.setDescription("Example item 1\n" +
                    "Lorem ipsum dolor sit amet, consetetur\n" +
                    "sadipscing elitr, sed diam nonumy\n" +
                    "eirmod tempor invidunt ut labore et\n" +
                    "dolore magna aliquyam erat, sed diam\n" +
                    "voluptua. At vero eos et accusam et\n" +
                    "justo duo dolores et ea rebum. Stet clita\n" +
                    "kasd gubergren, no sea takimata\n" +
                    "sanctus est Lorem ipsum dolor sit amet.\n" +
                    "Lorem ipsum dolor sit amet, consetetur\n" +
                    "sadipscing elitr, sed diam nonumy\n" +
                    "eirmod tempor invidunt ut labore et\n" +
                    "dolore magna aliquyam erat, sed diam\n" +
                    "voluptua. At vero eos et accusam et\n" +
                    "justo duo dolores et ea rebum. Stet clita\n" +
                    "kasd gubergren, no sea takimata\n" +
                    "sanctus est Lorem ipsum dolor sit amet.");
            position.setAmount(2.00);
            position.setUnitPrice(24.00);
            position.setTotalPrice(48.00);
            result.add(position);
        }

        return result;
    }

}
