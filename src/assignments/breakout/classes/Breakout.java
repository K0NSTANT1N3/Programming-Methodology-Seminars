package assignments.breakout.classes;/*
 * File: Breakout.java
 * -------------------
 * Name: Konstantine Endeladze
 * Section Leader:
 *
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import assignments.breakout.classes.game_entities.Button;
import assignments.breakout.classes.game_entities.*;
import assignments.breakout.variables.Var;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Breakout extends GraphicsProgram {
    private final Object lock = new Object();
    private final Object ballCreationLock = new Object();
    /**
     * initializing variables
     */
    private RandomGenerator rgen;
    private AtomicInteger lifeLeft;
    private int bricksBreaked;
    private int rounds;
    private GLabel life;
    private boolean gameStarted;
    private volatile boolean gameRunning;
    private volatile boolean newBallInitiated;
    private AtomicInteger ballCount;
    /**
     * initializing objects
     */
    private Paddle paddle1;
    private Lobby lobby;
    private List<Thread> threads;

    /* constructs essention detailes before game starts */
    @Override
    public void init() {
        setSize(Var.WIDTH, Var.HEIGHT);
        lobby = Lobby.getInstance();
        rgen = RandomGenerator.getInstance();
        variableReset();
        threads = new ArrayList<>();
        ballCount = new AtomicInteger(0);
        lifeLeft = new AtomicInteger(Var.NTURNS);

        addMouseListeners();
    }

    /**
     * Runs the Breakout program.
     */
    @Override
    public void run() {
        pause(50);

        lobbyConstr();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (gameStarted) {
            double x = e.getX();
            double width1 = paddle1.getWidth();
            if (x >= width1 / 2 && x <= Var.WIDTH - width1 / 2) {
                paddle1.setLocation(x - width1 / 2, paddle1.getY());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameStarted) return;

        GObject obj = getElementAt(e.getX(), e.getY());
        if (obj instanceof Button btn) {// click play
            if (btn == lobby.getPlay()) {
                setAndPlay();
            } else if (btn == lobby.getCredits()) {// click credits
                removeAll();
                creditsPage();
            } else if (btn == lobby.getBack()) {//click back button
                removeAll();
                lobbyConstr();
            }
        }
    }


    private void ballMovement(Ball ball) {

        ball.setVx(rgen.nextDouble(1.0, 3.0));
        if (rgen.nextBoolean(0.5)) ball.setVx(-1 * ball.getVx());
        ball.setVy(6);

        while (gameRunning) {
            ball.move(ball.getVx(), ball.getVy());
            pause(ball.getDelay());

            handleCollision(ball);

            synchronized (this) {
                if (bricksBreaked >= Var.BRICKS_TO_BREAK) {
                    roundFinished();
                    return;
                }
            }
        }
    }

    /**
     * handles colisions of a ball with other objects
     */

    private void handleCollision(Ball ball) {
        if (ball.getY() > Var.HEIGHT) {// when coming down
            missedBall(ball);
        } else if (ball.getY() < 0) {// when touching top border
            ball.setVy(ball.getVy() < 0 ? -ball.getVy() : ball.getVy());
        } else {
            HashMap<Double, Double> points;

            for (int i = 1; i <= 4; i++) {
                if (i == 1) {//collision with top side of ball
                    points = ball.topPoints();
                } else if (i == 2) {//collision with left side of ball
                    points = ball.leftPoints();

                    if (ball.getX() <= 0) ball.setVx(ball.getVx() <= 0 ? -ball.getVx() : ball.getVx());
                } else if (i == 3) {//collision with bottom side of ball
                    points = ball.bottomPoints();
                } else {//collision with right side of ball
                    points = ball.rightPoints();

                    if (ball.getX() + 2 * ball.getRadius() > Var.WIDTH)
                        ball.setVx(ball.getVx() >= 0 ? -ball.getVx() : ball.getVx());
                }

                for (Map.Entry<Double, Double> entry : points.entrySet()) {
                    double x = entry.getKey();
                    double y = entry.getValue();

                    GObject object = getElementAt(x, y);

                    if (object != null && !(object instanceof Ball) && !(object instanceof GImage) && !(object instanceof GLabel)) {
                        if (i == 1) {//collision with top side of balls
                            ball.setVy(ball.getVy() <= 0 ? -ball.getVy() : ball.getVy());
                        } else if (i == 2) {//collision with left side of ball
                            ball.setVx(ball.getVx() <= 0 ? -ball.getVx() : ball.getVx());
                        } else if (i == 3) {//collision with bottom side of ball
                            ball.setVy(ball.getVy() >= 0 ? -ball.getVy() : ball.getVy());
                        } else if (i == 4) {//collision with right side of ball
                            ball.setVx(ball.getVx() >= 0 ? -ball.getVx() : ball.getVx());
                        }
                        objectCollisionHandler(object, ball);
                    }
                }
            }
        }

    }

    /**
     * action methods
     */
    private void setAndPlay() {
        // Create a CountDownLatch with a count of 1
        CountDownLatch latch = new CountDownLatch(1);

        // Start imgThread
        Thread imgThread = new Thread(() -> {
            gameInit();

            try {
                Thread.sleep(700); // Adjust the sleep time based on your needs
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            // Signal that imgThread has completed its job
            latch.countDown();
        });
        imgThread.start();

        try {
            // Wait for imgThread to complete before starting gamePlay
            latch.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt(); // Preserve interrupted status
        }
        newBallThread();
    }

    /**
     * what to happen on object after colliding with ball
     */
    private void objectCollisionHandler(Object obj, Ball ball) {
        if (obj instanceof Brick brick) {// ball touched brick
            brickCollision(brick, ball);
        } else if (obj instanceof Paddle paddle) {// ball touched paddle
            paddleCollision(paddle, ball);
        } else if (obj instanceof SuperBrick brick) {// ball touched superBrick
            if (!brick.getFalling()) {
                superBrickCollision(brick, ball);
            }
        }
    }

    private void superBrickCollision(SuperBrick brick, Ball ball) {
        brick.setFalling(true);
        // Start a new thread for moving the super brick
        Thread superBrickThread = new Thread(() -> {
            // Move the super brick downwards
            while (brick.getY() < Var.HEIGHT) {
                brick.move(0, 1);
                pause(brick.getDelay());

                superBrickOnPaddle(brick, ball);
            }

            newBallInitiated = false;
            Thread.currentThread().interrupt();
        });

        // Start the thread
        superBrickThread.start();
    }

    private void superBrickOnPaddle(SuperBrick brick, Ball ball) {
        GObject obj = getElementAt(brick.getX() - brick.getWidth() / 2, brick.getY() + brick.getHeight() / 2);
        if (obj instanceof Paddle) {
            Paddle paddle = (Paddle) obj;
            int superPower = brick.getSuperPower();
            remove(brick);

            switch (superPower) {
                case 1 -> paddle.setSize(Var.PADDLE_WIDTH * 2, Var.PADDLE_HEIGHT); //paddle increased in width
                case 2 -> paddle.setSize(Var.PADDLE_WIDTH / 2, Var.PADDLE_HEIGHT); //paddle decreased in width
                case 3 -> {//ball increased in size
                    if (ball != null) {
                        ball.setRadius(Var.BALL_RADIUS * 3);
                        ball.setSize(ball.getRadius() * 2, ball.getRadius() * 2);
                    }
                }
                case 4 -> {//ball increased in size
                    if (ball != null) {
                        ball.setRadius(Var.BALL_RADIUS / 3);
                        ball.setSize(ball.getRadius() * 2, ball.getRadius() * 2);
                    }
                }
                case 5 -> {
                    synchronized (ballCreationLock) {
                        if (!newBallInitiated) {
                            newBallThread();
                            newBallInitiated = true;
                        }
                    }
                }
                case 6 -> {//speedup
                    if (ball != null) ball.setDelay(ball.getDelay() - 2);
                }
                case 7 -> {//slowdown
                    if (ball != null) ball.setDelay(ball.getDelay() + 2);
                }
            }
        }
    }

    private void brickCollision(Brick brick, Ball ball) {
        ball.setColor(brick.getColor());
        remove(brick);
        bricksBreaked++;
    }

    /* precise reflection from paddle */
    private void paddleCollision(Paddle paddle, Ball ball) {
        double ballMid = ball.getX() + ball.getWidth() / 2;
        /* if ball is touching edge of paddle */
        if (ballMid < paddle.leftX()) {//on left side
            ball.setVx(-9);
        } else if (ballMid > paddle.rightX()) {//on right side
            ball.setVx(9);
        } else if (ballMid < paddle.midX()) { //first half of paddle
            double touchedFrom = ballMid - paddle.leftX();
            int reflectQuality = 8 - (int) Math.ceil((8 * touchedFrom) / paddle.getWidth());
            ball.setVx(-reflectQuality);
        } else { //second half of paddle
            double touchedFrom = paddle.rightX() - ballMid;
            int reflectQuality = 8 - (int) Math.ceil((8 * touchedFrom) / paddle.getWidth());
            ball.setVx(reflectQuality);
        }
    }

    /**
     * constructing methods
     */
    /* builds bricks */
    private void brickConstr() {
        for (int i = 0; i < Var.NBRICK_ROWS; i++) {
            for (int j = 0; j < Var.NBRICKS_PER_ROW; j++) {
                double x = Var.BRICK_SEP / 2.0 + j * (Var.BRICK_WIDTH + Var.BRICK_SEP);
                double y = Var.BRICK_Y_OFFSET + i * 1.0 * (Var.BRICK_HEIGHT + Var.BRICK_SEP);
                Brick brick = new Brick(x, y, Var.BRICK_WIDTH, Var.BRICK_HEIGHT);
                brick.setFilled(true);

                switch (i) {
                    case 0, 1 -> brick.setColor(Color.RED);
                    case 2, 3 -> brick.setColor(Color.ORANGE);
                    case 4, 5 -> brick.setColor(Color.YELLOW);
                    case 6, 7 -> brick.setColor(Color.GREEN);
                    case 8, 9 -> brick.setColor(Color.CYAN);
                }

                add(brick);
            }
        }
    }

    private void superBrickConstr() {
        for (int i = 0; i < Var.SUPER_BRICK_NUM; i++) {
            SuperBrick brick = SuperBrick.randBrick();
            add(brick);
        }
    }

    /* adds paddle on specific location with its own dimentions */
    private Paddle paddleConstr(double x, double y, double width, double height) {
        Paddle paddle = new Paddle(x, y, width, height);
        paddle.setFilled(true);
        paddle.setColor(Color.BLACK);
        add(paddle);
        return paddle;
    }

    /* adds ball on specific location with its own radius */
    private Ball ballConstr(double x, double y, double radius) {
        Ball ball = new Ball(x, y, 2 * radius, 2 * radius, 27);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        add(ball);
        return ball;
    }

    /* calculates which image to set as background based on numbers*/
    private void setBackground(int number) {
        if (number < 1 || number > 8) return;
        String imagePath = "breakout/assets/" + number + ".png";
        GImage image = lobby.addImage(imagePath);
        add(image);
    }

    private void newBallThread() {
        Ball newBall = ballConstr(Var.WIDTH / 2.0 - Var.BALL_RADIUS, Var.HEIGHT / 2.0 - Var.BALL_RADIUS, Var.BALL_RADIUS);

        // Increment the ball count
        ballCount.incrementAndGet();

        // Start a new thread for moving the new ball
        Thread newBallThread = new Thread(() -> {
            while (gameRunning) {
                ballMovement(newBall);

                if (bricksBreaked >= Var.BRICKS_TO_BREAK || newBall.getY() > Var.HEIGHT) {
                    break;
                }
            }

            // Remove the thread from the list when it's done
            Thread.currentThread().interrupt();
            threads.remove(Thread.currentThread());
        });

        // Start the thread
        threads.add(newBallThread);
        newBallThread.start();
    }

    /**
     * lobby constructor
     */
    private void lobbyConstr() {
        setBackground(1);
        lobbyBtnConstr();
    }

    private void lobbyBtnConstr() {
        lobby.buttonConstr();
        add(lobby.getCredits());
        add(lobby.getPlay());
    }

    private void gameInit() {
        removeAll();
        paddle1 = paddleConstr((Var.WIDTH - Var.PADDLE_WIDTH) / 2.0, Var.HEIGHT - (Var.PADDLE_Y_OFFSET + Var.PADDLE_HEIGHT), Var.PADDLE_WIDTH, Var.PADDLE_HEIGHT);
        Ball ball = ballConstr(Var.WIDTH / 2.0 - Var.BALL_RADIUS, Var.HEIGHT / 2.0 - Var.BALL_RADIUS, Var.BALL_RADIUS);
        bricksBreaked = 0;
        rounds++;
        gameStarted = true;
        gameRunning = true;

        // Set background first
        setBackground(rounds + 4);

        // Add elements after setting the background
        life = new GLabel("You Have " + lifeLeft + " Life");
        life.setColor(Color.RED);
        add(life, Var.WIDTH - 100, Var.BRICK_Y_OFFSET / 2.0);

        // Add paddle bricks and ball
        superBrickConstr();
        brickConstr();
        add(paddle1);
        add(ball);
    }


    private void variableReset() {
        gameStarted = false;
        gameRunning = false;
        newBallInitiated = false;
        rounds = 0;
        lifeLeft = new AtomicInteger(Var.NTURNS);
    }

    private void creditsPage() {
        setBackground(2);
        lobby.buttonConstr();
        add(lobby.getBack());
    }

    /**
     * after failing ball
     */
    private void missedBall(Ball ball) {
        if (!gameRunning) return;

        synchronized (lock) {
            remove(ball);
            Thread currentThread = Thread.currentThread();
            currentThread.interrupt();
            threads.remove(currentThread);

            // Decrement the ball count
            ballCount.decrementAndGet();//update the ballCount

            if (threads.size() <= 0) {
                lifeLeft.decrementAndGet();
                remove(life);
                life = new GLabel("You Have " + lifeLeft.get() + " Life");
                life.setColor(Color.RED);
                add(life, Var.WIDTH - 100, Var.BRICK_Y_OFFSET / 2.0);

                if (lifeLeft.get() >= 1) {
                    ballCount.incrementAndGet();
                    pause(50);
                    newBallThread();
                } else {
                    gameRunning = false;
                    displayGameOver();
                }
            }
        }
    }

    /* when player finally loses the game */
    private void displayGameOver() {
        for (Thread thread : threads) {
            thread.interrupt();
        }

        SwingUtilities.invokeLater(() -> {
            removeAll();

            variableReset();
            setBackground(3);
            GLabel gameOverLabel = new GLabel("დაიბრიდე ;(");
            double x = (gameOverLabel.getWidth()) / 2;
            double y = Var.HEIGHT / 2.0;
            gameOverLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
            gameOverLabel.setColor(Color.RED);
            add(gameOverLabel, x, y);
            lobbyBtnConstr();
        });
    }

    /**
     * after breaking every brick
     */
    private void roundFinished() {
        for (Thread thread : threads) thread.interrupt();

        synchronized (this) {
            Thread currentThread = Thread.currentThread();
            currentThread.interrupt();
            threads.remove(currentThread);

            try {
                Thread.sleep(600); // Adjust the sleep time based on your needs
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            if (rounds > 3) {// game finished
                displayGameWon();
            } else {//next round
                setAndPlay();
            }
        }
    }

    private void displayGameWon() {
        SwingUtilities.invokeLater(() -> {
            removeAll();

            variableReset();
            setBackground(4);
            GLabel gameWonLabel = new GLabel("შუა ღამეა და ეს assignments.breakout ეჩალიჩება ... მოიგე ძმა წადი დაიძინე");
            double x = 5;
            double y = Var.HEIGHT / 2.0;
            gameWonLabel.setColor(Color.RED);
            add(gameWonLabel, x, y);
            lobbyBtnConstr();
        });
    }
}