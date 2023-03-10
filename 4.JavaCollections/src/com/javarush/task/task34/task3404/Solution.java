package com.javarush.task.task34.task3404;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Рекурсия для мат. выражения
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); // Expected output: 0.5 6
    }

    public void recurse(final String expression, int countOperation) {
        String result = "";
        int count = countOperation;

        Pattern compareWithDecimals = Pattern.compile("^-?((\\d*\\.\\d*([eE][+\\-]?\\d+)?)|\\d+)$");
        Matcher matcher = compareWithDecimals.matcher(expression);

        if (matcher.matches()) {
            Double d = Double.parseDouble(expression);
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
            numberFormat.setRoundingMode(RoundingMode.HALF_EVEN);
            DecimalFormat df = (DecimalFormat) numberFormat;
            df.applyPattern("#.##");
            String stringWeNeed = df.format(d);
            System.out.println(stringWeNeed + " " + countOperation);
            return;
        }

        if (!expression.contains("!")) {
            String s = doSomething(expression);
            String[] ss = s.split(" ");
            result = ss[0];
            count = Integer.parseInt(ss[1].trim());
        } else {
            result = expression.replaceFirst("!", "");
        }

        recurse(result, count);
    }

    private double makeOperation(String s, double first, double second) {
        switch (s) {
            case "+" -> {
                return first + second;
            }
            case "=", "%" -> {
                return 0.0 - second;
            }
            case "-", "@" -> {
                return first - second;
            }
            case "*" -> {
                return first * second;
            }
            case "/" -> {
                return first / second;
            }
            case "^" -> {
                return Math.pow(first, second);
            }
            default -> {
                return -1;
            }
        }
    }

    private double makeFunction(String s, double first) {
        switch (s) {
            case "s" -> {
                return Math.sin(Math.toRadians(first));
            }
            case "c" -> {
                return Math.cos(Math.toRadians(first));
            }
            case "t" -> {
                return Math.tan(Math.toRadians(first));
            }
            default -> {
                return -1;
            }
        }
    }

    private int getPriority(String s) {
        return switch (s) {
            case "+", "-", "=", "%", "@" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            case "c", "s", "t" -> 4;
            default -> -1;
        };
    }

    private boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("@") || c.equals("%") || c.equals("=") || c.equals("*") || c.equals("/") || c.equals("^");
    }

    private boolean isFunction(String c) {
        return c.equals("s") || c.equals("c") || c.equals("t");
    }

    private String doSomething(String expression) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
        numberFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        DecimalFormat df = (DecimalFormat) numberFormat;
        df.applyPattern("#.##");

        LinkedList<Double> doubles = new LinkedList<>();
        StringBuilder sb;

        String workWith = expression.replaceAll("([Ss])([Ii])([Nn])", "s"); //Заменяем все функции на одиночный аналог
        workWith = workWith.replaceAll("[Cc][Oo][Ss]", "c"); //Заменяем все функции на одиночный аналог
        workWith = workWith.replaceAll("[Tt][Aa][Nn]", "t"); //Заменяем все функции на одиночный аналог

        //посчитаем количество операций
        workWith = workWith.replaceAll("^-", "=");

        for (int i = 0; i < 2; i++) {
            Pattern binaryMinus = Pattern.compile("(\\d+\\s?-\\s?\\d+)|([cst]\\s?-\\s?\\d)|(\\d\\s?-\\s?[cst])" +
                    "|([cst]\\s?-\\s?[cst])|(\\)\\s?-\\s?\\()|(\\d+\\s?-\\s?\\()" +
                    "|(\\)\\s?-\\s?\\d+)|([cst]\\s?-\\s?\\()|(\\)\\s?-\\s?[cst])");
            Matcher minusMatcher = binaryMinus.matcher(workWith);

            while (minusMatcher.find()) {
                String group = minusMatcher.group();
                String newString = group.replace('-', '@');
                workWith = workWith.replace(group, newString);
            }
        }

        Pattern strange = Pattern.compile("[^\\d)]\\s?-\\s?[\\dcst(]");
        Matcher mimi = strange.matcher(workWith);

        while (mimi.find()) {
            String lookfor = mimi.group();
            String replTo = lookfor.replace("-", "%");
            workWith = workWith.replace(lookfor, replTo);
        }

        int numberOfOperations = 0;
        {
            Pattern operation = Pattern.compile("[sct+\\-*/^=%@]");
            Matcher matcher = operation.matcher(workWith);
            while (matcher.find()) {
                numberOfOperations++;
            }
        }

        //в следующем блоке заменяем все цифры символом D
        Pattern compareWithDecimals = Pattern.compile("-?((\\d*\\.\\d*([eE][+\\-]?\\d+)?)|\\d+)");
        Matcher m = compareWithDecimals.matcher(workWith);

        while (m.find()) {
            String ourDouble = m.group();
            doubles.add(Double.parseDouble(ourDouble));
            workWith = workWith.replaceFirst(ourDouble, "D");
        }

        workWith = workWith.replaceAll(" ", "");
        String[] p = workWith.split("");

        LinkedList<String> operators = new LinkedList<>();
        LinkedList<Double> d = new LinkedList<>();

        for (String s : p) {
            if (s.equals("D")) {
                d.add(doubles.removeFirst());
            }

            if (isFunction(s) || isOperator(s)) {
                if (operators.size() == 0) {
                    operators.add(s);
                    continue;
                } else {
                    String lastOperator = operators.getLast();
                    int lastOperPriority = getPriority(lastOperator);
                    int thisPriority = getPriority(s);

                    while (thisPriority <= lastOperPriority && operators.size() > 0) {
                        if (thisPriority == 3 && lastOperPriority == thisPriority) {
                            operators.add(s);
                            break;
                        }

                        Double res = 0.0;
                        String operation = operators.removeLast();

                        if (operation.equals("=") || operation.equals("%")) {
                            Double d2 = d.removeLast();
                            res = makeOperation(operation, 0.0, d2);
                            res = Double.parseDouble(df.format(res));
                            d.add(res);

                            if (operators.size() > 0) {
                                lastOperPriority = getPriority(operators.getLast());
                            } else {
                                lastOperPriority = -1;
                            }

                            continue;
                        }

                        Double d2;
                        Double d1 = 0.0;

                        if (isOperator(operation)) {
                            d2 = d.removeLast();
                            if (d.size() > 0) {
                                d1 = d.removeLast();
                            }
                            res = makeOperation(operation, d1, d2);
                        }

                        if (isFunction(operation)) {
                            d2 = d.removeLast();
                            res = makeFunction(operation, d2);
                        }

                        res = Double.parseDouble(df.format(res));
                        d.add(res);

                        if (operators.size() > 0) {
                            lastOperPriority = getPriority(operators.getLast());
                        } else {
                            lastOperPriority = -1;
                        }
                    }
                }
                operators.add(s);
            }

            if (s.equals("(")) {
                operators.add(s);
            }

            if (s.equals(")")) {
                String operation;

                while (!(operation = operators.removeLast()).equals("(")) {
                    Double res = 0.0;
                    Double d2;

                    if (operation.equals("=") || operation.equals("%")) {
                        d2 = d.removeLast();
                        res = makeOperation(operation, 0.0, d2);
                        res = Double.parseDouble(df.format(res));
                        d.add(res);
                        continue;
                    }

                    Double d1 = 0.0;

                    if (isOperator(operation)) {
                        d2 = d.removeLast();
                        if (d.size() > 0) {
                            d1 = d.removeLast();
                        }
                        res = makeOperation(operation, d1, d2);
                    }

                    if (isFunction(operation)) {
                        d2 = d.removeLast();
                        res = makeFunction(operation, d2);
                    }

                    res = Double.parseDouble(df.format(res));
                    d.add(res);
                }
            }
        }

        String operation;

        while (operators.size() != 0) {
            operation = operators.removeLast();
            Double d2;
            Double res = 0.0;

            if (operation.equals("=") || operation.equals("%")) {
                d2 = d.removeLast();
                res = makeOperation(operation, 0.0, d2);
                res = Double.parseDouble(df.format(res));
                d.add(res);
                continue;
            }

            Double d1 = 0.0;

            if (isOperator(operation)) {
                d2 = d.removeLast();
                if (d.size() > 0) {
                    d1 = d.removeLast();
                }
                res = makeOperation(operation, d1, d2);
            }

            if (isFunction(operation)) {
                d2 = d.removeLast();
                res = makeFunction(operation, d2);
            }

            res = Double.parseDouble(df.format(res));
            d.add(res);
        }

        sb = new StringBuilder();
        sb.append(d.get(0));

        sb.append("!".repeat(Math.max(0, numberOfOperations)));

        sb.append(" ");
        sb.append(numberOfOperations);

        return sb.toString();
    }

    public Solution() {
        //don't delete
    }
}
