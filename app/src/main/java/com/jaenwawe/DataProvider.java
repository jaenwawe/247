package com.jaenwawe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DataProvider {

    public static List<Product> productList = new ArrayList<>();
    public static Map<String, Product> productMap = new HashMap<>();

    static {

        addProduct("b00hqwlhj8",
                "8GB Flash Drive",
                "set of 10 - Enfain Bulk 8GB USB 2.0 Flash Drive",
                17.99);

        addProduct("b0044w4hww",
                "Trident Sugar Free Gum, Spearmint",
                "Trident Sugar Free Gum, Spearmint,18-Count (Pack of 12).",
                7.03);

        addProduct("b000ypkody",
                "Cracker Jack Original Singles",
                "1-Ounce Boxes (Pack of 25)",
                17.98);

        addProduct("b004g4ikgm",
                "Android Toy",
                "Dyzplastic Android Mini Collectible Figure, Standard Green",
                10.49);


        addProduct("b019fwwjcu",
                "# 2 Pencils",
                "No 2 Yellow Wood Pencil Great School Art Supplies For Writing, Drawing & Sketching ",
                17.99);

        addProduct("b014i8ssd0",
                "HDMI cord",
                "High-Speed HDMI Cable - 6 Feet (Latest Standard).",
                5.49);


        addProduct("b01DF1BPR2",
                "Pens",
                "Business Gift Premium Gel Ink Roller Ball Pens, Fine Point, Black Ink, Dozen Box",
                12.99);

        addProduct("b00u78nqho",
                "Colored Pencils",
                "8-color Colored Pencils/ Drawing Pencils for Sketch/Secret Garden Coloring Book",
                15.99);


        addProduct("b00jpay9lm",
                "Eraser",
                "Sargent Art 36-1012 36 Count Eraser Best Buy Pack, Pink",
                11.41);

        addProduct("b8883701127",
                "Moleskine Cahier Journal",
                "(Set of 3), Large, Ruled, Kraft Brown, Soft Cover (5 x 8.25): set of 3 Ruled Journals",
                12.34);
    }

    private static void addProduct(String itemId, String name,
                                   String description, double price) {
        Product item = new Product(itemId, name, description, price);
        productList.add(item);
        productMap.put(itemId, item);
    }

    public static List<String> getProductNames() {
        List<String> list = new ArrayList<>();
        for (Product product : productList) {
            list.add(product.getName());
        }
        return list;
    }

    public static List<Product> getFilteredList(String searchString) {

        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductId().contains(searchString)) {
                filteredList.add(product);
            }
        }

        return filteredList;

    }

}
