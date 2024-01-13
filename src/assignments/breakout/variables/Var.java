package assignments.breakout.variables;

/**
 * this is utility class. its function is to save variables that entire program needs to know
 */
public final class Var {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    public static final int WIDTH = APPLICATION_WIDTH + 2;
    public static final int HEIGHT = APPLICATION_HEIGHT + 52;

    /**
     * Dimensions of the paddle
     */
    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    public static final int PADDLE_Y_OFFSET = 80;

    /**
     * Number of bricks per row
     */
    public static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    public static final int NBRICK_ROWS = 10;

    /**
     * number of super bricks
     */
    public static final int SUPER_BRICK_NUM = 40;
    /**
     * Separation between bricks
     */
    public static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    public static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    public static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    public static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    public static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    public static final int NTURNS = 3;

    /* set this number to 10 in order to see other levels easily */
    public static final int BRICKS_TO_BREAK = 100;

}
