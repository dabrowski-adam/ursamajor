package com.pbl.ursa.technology;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    TechnologyGameScreen gameScreen;
    Rectangle bounds;
    Cell[][] cells;

    boolean isSimulating;
    float elapsedTime;
    float stepTime;
    List<Trash> cargo;

    public Board(float x, float y, final TechnologyGameScreen gameScreen) {
        this.gameScreen = gameScreen;
        InputHandler input = gameScreen.input;

        bounds = new Rectangle(x, y, 250, 250);
        isSimulating = false;
        elapsedTime = 0;
        stepTime = 1;
        cargo = new ArrayList<Trash>();

        cells = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final Cell cell = new Cell(
                        bounds.x + i * 50,
                        bounds.y + j * 50,
                        50,
                        50);

                input.bindDown(cell.bounds, new Callable() {
                    @Override
                    public void call(float x, float y) {
                        if (isSimulating) { return; }

                        Tool tool = cell.remove();
                        if (tool != null) {
                            gameScreen.draggedItem = tool;
                            gameScreen.itemOrigin = cell;
                        }
                    }
                });
                input.bindUp(cell.bounds, new Callable() {
                    @Override
                    public void call(float x, float y) {
                        if (cell.insert(gameScreen.draggedItem)) {
                            gameScreen.draggedItem = null;
                            if (cell == gameScreen.itemOrigin) {
                                cell.rotate();
                            }
                        } else {
                            gameScreen.resetDragged();
                        }
                    }
                });

                cells[i][j] = cell;
            }
        }
    }

    public void render(float delta, SpriteBatch spriteBatch, Map<Tool, Texture> resources) {
        if (spriteBatch == null || !spriteBatch.isDrawing()) { return; }

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                cells[y][x].render(spriteBatch, resources);
            }
        }

        if (isSimulating) {
            simulate(delta);

            float progress = elapsedTime / stepTime;
            for (Trash trash : cargo) {
                Cell cur = cells[trash.posX][trash.posY];
                Cell next = cells[trash.nextPosX][trash.nextPosY];

                float difX = next.bounds.x - cur.bounds.x;
                float difY = next.bounds.y - cur.bounds.y;

                spriteBatch.draw(resources.get(trash.type),
                        5 + cur.bounds.x + difX * progress,
                        5 + cur.bounds.y + difY * progress,
                        40,
                        40);
            }
        }
    }

    public void clear() {
        stopSimulation();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j].clear();
            }
        }
    }

    public void stopSimulation() {
        isSimulating = false;
    }

    public void startSimulation() {
        if (isSimulating) { return; }

        isSimulating = true;
        elapsedTime = 0;
        cargo.clear();
    }

    private void simulate(float delta) {
        // Spawn trash
        if (elapsedTime == 0) {
            cargo.add(new Trash(Tool.Bottle, 0, 4));
        }

        // End simulation step
        if (elapsedTime >= stepTime) {
            elapsedTime = 0;
        }


        // Start simulation step
        if (elapsedTime == 0) {
            boolean allRecycled = true;

            for (Trash trash : cargo) {
                if (trash.isRecycled) { continue; }
                else { allRecycled = false; }

                if (trash.isMoved) {
                    trash.posX = trash.nextPosX;
                    trash.posY = trash.nextPosY;
                    trash.isMoved = false;
                }

                Cell cell = cells[trash.posX][trash.posY];
                switch (cell.content) {
                    case Empty:
                        gameScreen.failure_sfx.play();
                        stopSimulation();
                        break;
                    case Bin:
                        trash.isRecycled = true;
                        break;
                    case BeltRightLocked:
                        if (trash.posX + 1 < 5) {
                            trash.nextPosX = trash.posX + 1;
                            trash.nextPosY = trash.posY;
                            trash.isMoved = true;
                            break;
                        }
                    case BeltRight:
                        if (trash.posX + 1 < 5) {
                            trash.nextPosX = trash.posX + 1;
                            trash.nextPosY = trash.posY;
                            trash.isMoved = true;
                            break;
                        }
                    case BeltLeft:
                        if (trash.posX - 1 >= 0) {
                            trash.nextPosX = trash.posX - 1;
                            trash.nextPosY = trash.posY;
                            trash.isMoved = true;
                            break;
                        }
                    case BeltUp:
                        if (trash.posY + 1 < 5) {
                            trash.nextPosX = trash.posX;
                            trash.nextPosY = trash.posY + 1;
                            trash.isMoved = true;
                            break;
                        }
                    case BeltDown:
                        if (trash.posY - 1 >= 0) {
                            trash.nextPosX = trash.posX;
                            trash.nextPosY = trash.posY - 1;
                            trash.isMoved = true;
                            break;
                        }
                    default:
                        stopSimulation();
                        break;
                }
            }

            // Level finished
            if (allRecycled) {
                stopSimulation();
                gameScreen.success();
            }
        }

        elapsedTime += delta;
    }
}
