package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "⚫";

    private final List<GameObject> snakeParts = new ArrayList<>();
    boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {
        Color color = isAlive ? Color.BLACK : Color.RED;

        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject part = snakeParts.get(i);
            String sign = (i != 0) ? BODY_SIGN : HEAD_SIGN;
            game.setCellValueEx(part.x, part.y, Color.NONE, sign, color, 75);
        }
    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (newHead.x >= SnakeGame.WIDTH
                || newHead.x < 0
                || newHead.y >= SnakeGame.HEIGHT
                || newHead.y < 0
                || checkCollision(newHead)) {
            isAlive = false;
            return;
        }

        snakeParts.add(0, newHead);

        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else {
            removeTail();
        }
    }

    public GameObject createNewHead() {
        GameObject oldHead = snakeParts.get(0);
        if (direction == Direction.LEFT) {
            return new GameObject(oldHead.x - 1, oldHead.y);
        } else if (direction == Direction.RIGHT) {
            return new GameObject(oldHead.x + 1, oldHead.y);
        } else if (direction == Direction.UP) {
            return new GameObject(oldHead.x, oldHead.y - 1);
        } else {
            return new GameObject(oldHead.x, oldHead.y + 1);
        }
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public void setDirection(Direction direction) {
        if (isOppositeDirection(direction) || isSameDirection(direction)) {
            return;
        }
        this.direction = direction;
    }

    private boolean isOppositeDirection(Direction direction) {
        return (this.direction == Direction.LEFT && direction == Direction.RIGHT) ||
                (this.direction == Direction.RIGHT && direction == Direction.LEFT) ||
                (this.direction == Direction.UP && direction == Direction.DOWN) ||
                (this.direction == Direction.DOWN && direction == Direction.UP);
    }

    private boolean isSameDirection(Direction direction) {
        GameObject head = snakeParts.get(0);
        GameObject neck = snakeParts.get(1);

        return (direction == Direction.LEFT && head.y == neck.y) ||
                (direction == Direction.RIGHT && head.y == neck.y) ||
                (direction == Direction.UP && head.x == neck.x) ||
                (direction == Direction.DOWN && head.x == neck.x);
    }

    public boolean checkCollision(GameObject head) {
        for (GameObject sp : snakeParts) {
            if (sp.x == head.x && sp.y == head.y)
                return true;
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }
}
