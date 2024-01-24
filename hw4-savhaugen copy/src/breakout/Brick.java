package breakout;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;

public class Brick extends Rectangle{
    
    private double width;
    private double height;
    private double centerX;
    private double centerY;
    private Rectangle newBrick;

    public Color BRICK_COLOR = new Color(50, 50, 50);
    

    public Brick(double centerX, double centerY, double width, double height){
        super(centerX, centerY, width, height);
        this.width=width;
        this.height=height;
        this.centerX=centerX;
        this.centerY=centerY;

        newBrick= new Rectangle(centerX, centerY, width, height);
        newBrick.setFillColor(BRICK_COLOR);
        
    }
 
}
