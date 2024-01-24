package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;

import java.awt.Color;
import java.util.Random;

import java.util.ArrayList;


public class BrickManager {


    private Random random;
    private CanvasWindow canvas;
   
    private double centerX;
    private double centerY;

    int red;
    int blue;
    int green;

    private double brickWidth;
    private double brickHeight;

    private GraphicsText winningMessage;

    public BrickManager(CanvasWindow canvas){
        this.canvas=canvas;
        random = new Random();
        centerX=0;
        centerY=0;

        brickWidth=90;
        brickHeight=20;

        red=100;
        green=10;
        blue=200;

        winningMessage= new GraphicsText();

        winningMessage = new GraphicsText();
        winningMessage.setFont(FontStyle.BOLD, 50);
        winningMessage.setText("You lose!");
        winningMessage.setPosition(centerX, centerY);

    }

    /**
     * This creates bricks and adds them next to each other, wrapping when 
     * it gets to the end of the canvas so it looks like a continuous wall.
     * It also sets a random color for each new row by setting random values for 
     * red, green, and blue.
     * @param numBricks
     */
    public void generateBrickWall(int numBricks){
        
        Color BRICK_COLOR = new Color(randomInt(255), randomInt(255), randomInt(255));

        for (int i = 0; i<=numBricks; i++){
        
            Brick newBrick = new Brick(centerX, centerY, brickWidth, brickHeight);
            newBrick.setFillColor(BRICK_COLOR);
            newBrick.setFilled(true);
            canvas.add(newBrick);
            centerX=centerX + brickWidth;
        
           if (centerX > canvas.getWidth()){
                BRICK_COLOR = new Color(randomInt(255), randomInt(255), randomInt(255));
                newBrick.setFillColor(BRICK_COLOR);
                
                centerY= centerY + brickHeight;
                centerX=0;

                red=randomInt(255);
                green= randomInt(255);
                blue = randomInt(255);

                newBrick.setFillColor(BRICK_COLOR);
           }
        }
    }

     private int randomInt(int max){
            return random.nextInt(max);
        }


    
    
    







}