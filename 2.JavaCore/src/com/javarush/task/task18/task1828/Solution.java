package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/* 
Прайсы 2
*/

public class Solution {
    public static class Product {
        private long id;
        private String productName;
        private String price;
        private String quantity;

        private Product(long id, String productName, String price, String quantity) {
            this.id = id;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public static Product fromArgs(long id, String[] args) {
            Product product = new Product(id,
                    String.format("%-30s", args[2]),
                    String.format("%-8s", args[3]),
                    String.format("%-4s", args[4]));
            product.isForUpdate = true;
            return product;
        }

        private boolean isForUpdate;

        public Product() {
        }

        public static Product fromId(long id) {
            Product product = new Product();
            product.id = id;
            return product;
        }

        public static Product fromString(String s) {
            long id = Long.parseLong(s.substring(0, 8).trim());
            Product product = new Product(id,
                    s.substring(8, 30),
                    s.substring(38, 46),
                    s.substring(46));
            return product;
        }

        public String getIdStr() {
            return String.format("%-8d", id);
        }

        @Override
        public String toString() {
            return getIdStr() + productName + price + quantity + "\n";
        }
    }

    public static void main(String[] args) throws IOException {
        String flag1 = getString(args);
        if (flag1 == null) return;

        long id = Long.parseLong(args[1].trim());
        Product productFor = (flag1.equals("-u"))
                ? Product.fromArgs(id, args)
                : Product.fromId(id);

        try (Scanner scanner = new Scanner(System.in)) {
            String fn = scanner.nextLine();

            StringBuilder data = new StringBuilder();
            try (FileReader fileReader = new FileReader(fn)) {
                while (fileReader.ready()) {
                    data.append((char) fileReader.read());
                }
            }
            String[] arr = data.toString().split("\n");

            List<Product> list = new ArrayList<>(arr.length);
            for (String s : arr) {
                if (s.length() > 0) {
                    list.add(Product.fromString(s));
                }
            }

            try (FileWriter fileWriter = new FileWriter(fn)) {
                for (Product product : list) {
                    if (productFor.isForUpdate) {
                        if (productFor.id == product.id) {
                            product.productName = productFor.productName;
                            product.price = productFor.price;
                            product.quantity = productFor.quantity;
                        }
                        fileWriter.write(product.toString());
                    } else {
                        if (productFor.id != product.id) {
                            fileWriter.write(product.toString());
                        }
                    }
                }
            }

        }
    }

    private static String getString(String[] args) {
        String flag1 = args[0];
        if ((!flag1.equals("-u") && !flag1.equals("-d")) ||
                ((args.length != 5) && (args.length != 2))) return null;
        return flag1;
    }
}
