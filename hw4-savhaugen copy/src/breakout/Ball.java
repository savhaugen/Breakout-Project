package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;

import java.awt.Color;


public class Ball {
    
    private double radius;
    private double centerX;
    private double centerY;

    private Ellipse newBall;

    private double maxX;
    private double maxY;
    private double dx;
    private double dy;
    private int count=0;

    private GraphicsText losingMessage;

    private GraphicsText winningMessage;

    private int brickCounter;

    private static final Color BALL_COLOR = new Color(0, 0, 0);


    public Ball(double radius, double centerX, double  centerY, double maxX, double maxY, int numBricks){
        this.radius = radius;
        this.centerX= centerX;
        this.centerY=centerX;
        this.maxX=maxX;
        this.maxY=maxY;

        newBall= new Ellipse(centerX, centerY, 2*radius, 2*radius);
        newBall.setFillColor(BALL_COLOR);
        newBall.setFilled(true);

        losingMessage = new GraphicsText();
        losingMessage.setFont(FontStyle.BOLD, 50);
        losingMessage.setText("You lose!");
        losingMessage.setPosition(centerX-125, centerY-150);

        winningMessage= new GraphicsText();

        winningMessage = new GraphicsText();
        winningMessage.setFont(FontStyle.BOLD, 50);
        winningMessage.setText("You win!");
        winningMessage.setPosition(centerX-125, centerY-150);

        brickCounter= numBricks;
    
        dx = -8;
        dy = -8;

    }

    /**
     * Sets the ball's new x and y coordinates to include the velocity
     *   */
    public void updatePosition() {

        double newX = dx  + newBall.getX();
        double newY = dy + newBall.getY();
        
        newBall.setPosition(newX, newY);
        
    }

    /**
     * If the ball goes outside the top, left or right bounds it will 
     * switch direction so it looks like it's bouncing off the wall.
     */
    public boolean wallCollision(){
        if (newBall.getX()<= 0|| newBall.getX() +(2*radius)>=maxX){
            dx= dx *-1;
            return true;
        }
        if (newBall.getY()<= 0){
            dy= dy*-1;
            return true;
        }
        return false;
    }

    /**
     *This tells us when the ball is out of bounds of the bottom of the screen
     */
    public boolean ballOutOfBounds(){
        if(newBall.getY() < maxY){
            return false;
        }
        return true;
    }

   /**
    * This sets the ball back to the center of the screen when 
    it goes out of bounds of the bottom.
    It also keeps track of how many times the ball has gone out of bounds(lives) 
    and displays a message telling the player they have lost when all three 
    lives have been used.
    * @param x
    * @param y
    * @param canvas
    */
    public void resetPosition(double x, double y, CanvasWindow canvas){
       
        if (ballOutOfBounds()){
            dx = -8;  
            dy = -8; 
            newBall.setPosition(x, y);
            canvas.draw();
            count= count +1;
            canvas.pause(3000);
            if(count>=3 && bricksStillExist()){
                canvas.removeAll();
                canvas.add(losingMessage);
            }
        }
    }

    /**
     * This program handles the ball when it intersects a brick. It tests whether the
     * object is a brick, and if it is, it removes it from the canvas. 
     * It also switches the direction of the ball so it looks like it bounced
     * off the brick.
     * @param canvas
     */
    public void brickCollision(CanvasWindow canvas){
        if (canvas.getElementAt(newBall.getX(), newBall.getY()-1) != null && !(canvas.getElementAt(newBall.getX(), newBall.getY()-1) instanceof Paddle)){
            dy= dy *-1;
            if((canvas.getElementAt(newBall.getX(), newBall.getY()-1) instanceof Brick)){
                canvas.remove(canvas.getElementAt(newBall.getX(), newBall.getY()-1));
                brickCounter= brickCounter-1;
            }  
        }
        if (canvas.getElementAt(newBall.getX() +(2*radius) +1, newBall.getY()+radius) != null && !(canvas.getElementAt(newBall.getX() +(2*radius) +1, newBall.getY()+radius)instanceof Paddle)){
            dx= dx *-1;
            if ((canvas.getElementAt(newBall.getX() +(2*radius) +1, newBall.getY()+radius)) instanceof Brick){
                canvas.remove(canvas.getElementAt(newBall.getX() +(2*radius) +1, newBall.getY()+radius));
                brickCounter= brickCounter-1;
            }
        }
        if (canvas.getElementAt(newBall.getX() +radius, newBall.getY()+(2*radius)+1) != null && !(canvas.getElementAt(newBall.getX() +radius, newBall.getY()+(2*radius)+1) instanceof Paddle)){
            dy= dy *-1;
            if(canvas.getElementAt(newBall.getX() +radius, newBall.getY()+(2*radius)+1) instanceof Brick){
                canvas.remove(canvas.getElementAt(newBall.getX() +radius, newBall.getY()+(2*radius)+1));
                brickCounter= brickCounter-1;
            }    
        }
        if (canvas.getElementAt(newBall.getX() -1, newBall.getY()+radius) != null && !(canvas.getElementAt(newBall.getX() -1, newBall.getY()+radius) instanceof Paddle)){
            dy= dy *-1;
            if (canvas.getElementAt(newBall.getX() -1, newBall.getY()+radius) instanceof Brick){
                canvas.remove(canvas.getElementAt(newBall.getX() -1, newBall.getY()+radius));
                brickCounter= brickCounter-1;
            }   
        }
        
    }

   
/**adds ball to canvas */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(newBall);
    }

    public Ellipse getShape(){
        return newBall;
    }

    public boolean bricksStillExist(){
        if (brickCounter<0){
            return false;
        }
        return true;
    }

   
    /**
     * This tests to see if any bricks still exist on the canvas.
     * If not, it will clear the canvas and display a message telling the player
     * they have won.
     * @param canvas
     */
    public void youWin(CanvasWindow canvas){
        if (!bricksStillExist()){
            canvas.removeAll();
            canvas.add(winningMessage);
            canvas.pause(3000);
        }
    }

}
