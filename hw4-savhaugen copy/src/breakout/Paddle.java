package breakout;



import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

import edu.macalester.graphics.Rectangle;

import java.awt.Color;

public class Paddle extends Rectangle{
    

    private double width;
    private double height;
    private double centerX;
    private double centerY;
    private Rectangle newPaddle;

    public static final Color PADDLE_COLOR = new Color(0, 0, 0);

    public Paddle(double centerX, double centerY, double width, double height){
        super(centerX, centerY, width, height);
        this.width=width;
        this.height=height;
        this.centerX=centerX;
        this.centerY=centerY;
        newPaddle= new Rectangle(centerX, centerY, width, height);

        newPaddle.setFillColor(Color.BLACK);
        newPaddle.setFilled(true);

    }


    /**
     * Adds paddle to canvas.
     * @param canvas
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(newPaddle);
    }

 
    /**
     * Sets paddle's x coordinate equal to a new x coordiante
     * that it takes in as a parameter. 
     * @param x
     */
    public void movePaddle(double x){
        centerX=x;
        newPaddle.setCenter(centerX, centerY);
   }





   

}
