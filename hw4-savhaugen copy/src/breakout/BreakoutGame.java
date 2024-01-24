package breakout;

import java.util.Random;

import edu.macalester.graphics.CanvasWindow;

/**
 * The game of Breakout.
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 900;
    private static final int CANVAS_HEIGHT = 700;
    
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 20;

    private static final int BALL_RADIUS = 7;

    private CanvasWindow breakoutCanvas;
 
    private Ball ball;
    private Paddle paddle;
    private BrickManager brickManager;

    private double centerX;
    private double centerY;
    private double angle;
    private double maxX;
    private double maxY;
    private double initialSpeed;


    public static void main(String[] args){
        BreakoutGame game =new BreakoutGame();
        game.resetGame();
    }



    public BreakoutGame() {
        centerX= 0;
        centerY=0;

        breakoutCanvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        brickManager= new BrickManager(breakoutCanvas);
        ball= new Ball(BALL_RADIUS, breakoutCanvas.getWidth()/2-15, breakoutCanvas.getHeight()-200, CANVAS_WIDTH, CANVAS_HEIGHT, 53);
        paddle = new Paddle(breakoutCanvas.getWidth()/2-50, breakoutCanvas.getHeight()-100, PADDLE_WIDTH, PADDLE_HEIGHT);

    }



    /**
     * This adds all the elements of our game to the canvas
     * Then it runs an animation of the ball moving and bouncing around the screen.
     * It calls the methods will display the winning and losing messages, if needed.
     * It also animates the paddle to move along the x axis with the position of the mouse.
     * 
     */
    public void resetGame() {
     
        ball.addToCanvas(breakoutCanvas);
        paddle.addToCanvas(breakoutCanvas);
        brickManager.generateBrickWall(53);
        breakoutCanvas.draw();
        breakoutCanvas.pause(3000);
        
        breakoutCanvas.animate(()-> {
            ball.updatePosition();
            ball.wallCollision();
            ball.brickCollision(breakoutCanvas);
            ball.resetPosition(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, breakoutCanvas);
            ball.youWin(breakoutCanvas); 
        });

        breakoutCanvas.onMouseMove(event -> {
            paddle.movePaddle(event.getPosition().getX());
        });
    }

    /**
     * Calls resetGame to initialize the game and make it start.
     */
    public void run() {    
        resetGame(); 
    }

    /**
     * This sets the x coordinate of the paddle to match the x coordinate of 
     * the mouse's current position.
     * @param paddle
     * @param canvas
     */
    public void move(Paddle paddle, CanvasWindow canvas){
        canvas.onDrag(event -> {
            paddle.setPosition(event.getPosition().getX(), (breakoutCanvas.getHeight()-100));
        });
      
    }   
}

