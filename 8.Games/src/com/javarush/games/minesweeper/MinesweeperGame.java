package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;

public class MinesweeperGame extends Game {
    private static final int SIDE = 15;
    private static final String MINE = "☠";
    private static final String FLAG = "⚑";
    private final GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countClosedTiles = SIDE * SIDE;
    private int countMinesOnField;
    private int countFlags;
    private int score;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        while (countMinesOnField == 0) {
            for (int y = 0; y < SIDE; y++) {
                for (int x = 0; x < SIDE; x++) {
                    boolean isMine = getRandomNumber(10) < 1;
                    if (isMine) {
                        countMinesOnField++;
                    }

                    gameField[y][x] = new GameObject(x, y, isMine);

                    setCellColor(x, y, Color.ORANGE);
                    setCellValue(x, y, "");
                }
            }
        }

        countMineNeighbors();
        countFlags = countMinesOnField;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.WHITE, String.format("!!! Победа ☺ !!!%n    score: %d", score), Color.BLUE, 60);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "Поражение ☹", Color.RED, 60);
    }

    private void restart() {
        isGameStopped = false;
        score = 0;
        setScore(0);
        countClosedTiles = SIDE * SIDE;
        countMinesOnField = 0;
        createGame();
    }

    private void openTile(int x, int y) {
        GameObject gameObject = gameField[y][x];

        if (isGameStopped || gameObject.isOpen || gameObject.isFlag) {
            return;
        }

        if (!gameObject.isMine) {
            setCellColor(x, y, Color.GREEN);
            gameObject.isOpen = true;
            score += 5;
            setScore(score);

            countClosedTiles--;
            if (countClosedTiles == countMinesOnField) {
                win();
            }

            if (gameObject.countMineNeighbors == 0) {
                setCellValue(x, y, "");
                ArrayList<GameObject> neighbors = getNeighbors(gameObject);
                for (GameObject neighbor : neighbors) {
                    if (!neighbor.isOpen) {
                        openTile(neighbor.x, neighbor.y);
                    }
                }
            }
            else
            {
                setCellNumber(x, y, gameObject.countMineNeighbors);
            }
        }
        else
        {
            xE = x;
            yE = y;
            setCellValueEx(x, y, Color.RED, MINE, Color.BLACK, 70);
            stepE = 0;
            setTurnTimer(50);
        }
    }

    private int xE;
    private int yE;
    private int stepE;


    @Override
    public void onTurn(int step) {
        stepE++;
        if (stepE % 2 == 0) {
            setCellValueEx(xE, yE, Color.RED, MINE, Color.BLACK, 70);
        }
        else{
            setCellValueEx(xE, yE, Color.YELLOW, MINE, Color.BLACK, 70);
        }
        if (stepE > 9) {
            gameOver();
            stopTurnTimer();
        }
    }

    private void markTile(int x, int y) {
        GameObject gameObject = gameField[y][x];

        if ((countFlags == 0 && !gameObject.isFlag) || (gameObject.isOpen) || (isGameStopped)) {
            return;
        }

        if (gameObject.isFlag) {
            gameObject.isFlag = false;
            countFlags++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.ORANGE);
        }
        else
        {
            gameObject.isFlag = true;
            countFlags--;
            setCellValue(x, y, FLAG);
            setCellColor(x, y, Color.YELLOW);
        }
    }

    private ArrayList<GameObject> getNeighbors(GameObject gameObject) {
        ArrayList<GameObject> result = new ArrayList<>();

        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }

                result.add(gameField[y][x]);
            }
        }

        return result;
    }

    private void countMineNeighbors() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                GameObject gameObject = gameField[y][x];

                if (!gameObject.isMine) {
                    ArrayList<GameObject> neighbors = getNeighbors(gameObject);
                    int countMineNeighbors = 0;

                    for (GameObject neighbor : neighbors) {
                        if (neighbor.isMine) {
                            countMineNeighbors++;
                        }
                    }

                    gameObject.countMineNeighbors = countMineNeighbors;
                }
            }
        }
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        if (isGameStopped) {
            return;
        }

        markTile(x, y);
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
            return;
        }

        openTile(x, y);
    }
}