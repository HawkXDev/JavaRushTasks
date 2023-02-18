package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {

    public static void main(String[] args) {

        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        List<Word> detectedAllWords = detectAllWords(crossword, "home", "same");
        System.out.println(detectedAllWords);

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

    }

    public static class XY {
        int x;
        int y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Word fillWord(Word word, XY xyFirst) {
            word.setStartPoint(xyFirst.x, xyFirst.y);
            word.setEndPoint(x, y);
            return word;
        }
    }

    private static class Crossword {
        int[][] crossword;

        public Crossword(int[][] crossword) {
            this.crossword = crossword;
        }

        public XY[] findAllChars(char c) {
            List<XY> list = new ArrayList<>();
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[0].length; j++) {
                    Character crosswordChar = (char) crossword[i][j];
                    if (crosswordChar.equals(c)) {
                        list.add(new XY(j, i));
                    }
                }
            }
            return list.toArray(new XY[0]);
        }

        public XY findHorizonLeft(XY xy, String word) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = xy.x, k = 0; i >= 0 && k < chars.length; i--, k++) {
                sb.append((char) crossword[xy.y][i]);
            }
            if (sb.toString().equals(word)) {
                return new XY(xy.x - word.length() + 1, xy.y);
            }
            return null;
        }

        public XY findHorizonRight(XY xy, String word) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = xy.x, k = 0; i < crossword[0].length && k < chars.length; i++, k++) {
                sb.append((char) crossword[xy.y][i]);
            }
            if (sb.toString().equals(word)) {
                return new XY(xy.x + word.length() - 1, xy.y);
            }
            return null;
        }

        public XY findVerticalUp(XY xy, String word) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = xy.y, k = 0; i >= 0 && k < chars.length; i--, k++) {
                sb.append((char) crossword[i][xy.x]);
            }
            if (sb.toString().equals(word)) {
                return new XY(xy.x, xy.y - word.length() + 1);
            }
            return null;
        }

        public XY findVerticalDown(XY xy, String word) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = xy.y, k = 0; i < crossword.length && k < chars.length; i++, k++) {
                sb.append((char) crossword[i][xy.x]);
            }
            if (sb.toString().equals(word)) {
                return new XY(xy.x, xy.y + word.length() - 1);
            }
            return null;
        }

        public XY findUpLeft(XY xy, String word) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            int q = Math.min(xy.x, xy.y);
            for (int i = q, k = 0; i >= 0 && k < chars.length; i--, k++) {
                sb.append((char) crossword[xy.y - (q - i)][xy.x - (q - i)]);
            }
            if (sb.toString().equals(word)) {
                return new XY(xy.x - word.length() + 1, xy.y - word.length() + 1);
            }
            return null;
        }

        public XY findDownRight(XY xy, String word) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            int q, border;
            if ((crossword[0].length - xy.x) < (crossword.length - xy.y)) {
                q = xy.x;
                border = crossword[0].length;
            } else {
                q = xy.y;
                border = crossword.length;
            }
            for (int i = q, k = 0; i < border && k < chars.length; i++, k++) {
                sb.append((char) crossword[xy.y - (q - i)][xy.x - (q - i)]);
            }
            if (sb.toString().equals(word)) {
                return new XY(xy.x + word.length() - 1, xy.y + word.length() - 1);
            }
            return null;
        }

        public XY findDownLeft(XY xy, String word) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            int q, border;
            if (xy.x + 1 > (crossword.length - xy.y)) {
                q = xy.y;
                border = crossword.length;
            } else {
                q = xy.x;
                border = 0;
            }
            for (int i = q, k = 0; (border != 0 ? i < border : i >= 0) && k < chars.length;
                 i = i + (border != 0 ? 1 : -1), k++) {
                sb.append((char) crossword
                        [(border != 0 ? i : xy.y + (q - i))]
                        [(border != 0 ? xy.x - (i - q) : i)]
                );
            }
            if (sb.toString().equals(word)) {
                return new XY(xy.x - word.length() + 1, xy.y + word.length() - 1);
            }
            return null;
        }

        public XY findUpRight(XY xy, String word) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            int q, border;
            if ((crossword[0].length - xy.x) < xy.y + 1) {
                q = xy.x;
                border = crossword[0].length;
            } else {
                q = xy.y;
                border = 0;
            }
            for (int i = q, k = 0; (border != 0 ? i < border : i >= 0) && k < chars.length;
                 i = i + (border == 0 ? -1 : 1), k++) {
                sb.append((char) crossword
                        [(border == 0 ? i : xy.y - (i - q))]
                        [(border == 0 ? xy.x + (q - i) : i)]
                );
            }
            if (sb.toString().equals(word)) {
                return new XY(xy.x + word.length() - 1, xy.y - word.length() + 1);
            }
            return null;
        }
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        Crossword cw = new Crossword(crossword);
        List<Word> list = new ArrayList<>();

        for (String word : words) {
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(word.length() - 1);

            XY[] xyFirsts = cw.findAllChars(firstChar);
            for (XY xyFirst : xyFirsts) {
                XY horizonLeft = cw.findHorizonLeft(xyFirst, word);
                if (horizonLeft != null)
                    list.add(horizonLeft.fillWord(new Word(word), xyFirst));

                XY horizonRight = cw.findHorizonRight(xyFirst, word);
                if (horizonRight != null)
                    list.add(horizonRight.fillWord(new Word(word), xyFirst));

                XY verticalUp = cw.findVerticalUp(xyFirst, word);
                if (verticalUp != null)
                    list.add(verticalUp.fillWord(new Word(word), xyFirst));

                XY verticalDown = cw.findVerticalDown(xyFirst, word);
                if (verticalDown != null)
                    list.add(verticalDown.fillWord(new Word(word), xyFirst));

                XY upLeft = cw.findUpLeft(xyFirst, word);
                if (upLeft != null)
                    list.add(upLeft.fillWord(new Word(word), xyFirst));

                XY downRight = cw.findDownRight(xyFirst, word);
                if (downRight != null)
                    list.add(downRight.fillWord(new Word(word), xyFirst));

                XY downLeft = cw.findDownLeft(xyFirst, word);
                if (downLeft != null)
                    list.add(downLeft.fillWord(new Word(word), xyFirst));

                XY upRight = cw.findUpRight(xyFirst, word);
                if (upRight != null)
                    list.add(upRight.fillWord(new Word(word), xyFirst));
            }

        }

        return list;
    }

    public static class Word {
        private final String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
