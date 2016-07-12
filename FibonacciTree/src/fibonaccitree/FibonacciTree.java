package fibonaccitree;

import static fibonaccitree.FibonacciTree.sleep;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.lang.Thread;

public class FibonacciTree extends JFrame {
    
    double maxLength = 100;
    double lengthDecayFactor = 0.80;
    double deltaTheta = Math.PI/4;
    
    int maximumLevel = 20;
    boolean animateAllLevelsUpToMaximum = true;
    int waitTimeBetweenFrames = 800;
    int currLevel = 1;
    
    Color purple = new Color(200,80,250);
    Color green = new Color(100,250,130);
    Color blue = new Color(60,220,250);
     
    Color[] branchColours = { purple, blue, green };
    
    
    public void drawFibTree( int n, double x1, double y1, double angle, double r, int age, Graphics g  ) {
        
       double x2 = x1 + r * Math.cos(angle);
       double y2 = y1 - r * Math.sin(angle);
       
       drawOneBranch( x1, y1, x2, y2, age, g ); //draw one branch from (x1,y1), no matter what n is.
       
       double r2 = r * lengthDecayFactor;
       
       if ( n > 1) { // if we're not in the base case
            n--;
           if ( age == 0 ) { //if a baby has just been drawn
                drawFibTree(n,x2,y2,angle,r2,1,g);
           }
           
           else { //if a kid or adult has just been drawn
                //sprout an adult from (x2, y2) at a new angle
                //sprout a baby from (x2, y2) at a new angle
                drawFibTree(n,x2,y2,angle - deltaTheta,r2,2,g);
                drawFibTree(n,x2,y2,angle + deltaTheta,r2,0,g);
           }            
        }
    }
    
    
    public void drawOneBranch( double x1, double y1, double x2, double y2, int age, Graphics g) {
        g.setColor( branchColours[ age ] );
        g.drawLine( (int)x1, (int)y1, (int)x2, (int)y2 );
    }
    
    
    public void paint( Graphics g ) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1000, 700);
        drawFibTree( currLevel, 500, 700, Math.PI/2, 140, 0, g );     
   }
    
    
    public static void main(String[] args) {
        
        FibonacciTree ft = new FibonacciTree();
        
        ft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ft.setSize( 1000, 700 );
        ft.setVisible(true);
        
        if ( ft.animateAllLevelsUpToMaximum == true ) {
            
            for ( int i=1; i < ft.maximumLevel; i++ ) {
                
                sleep( ft.waitTimeBetweenFrames );
       
                ft.repaint();
                ft.currLevel++;
            }  
        }
        
        else{
            ft.currLevel = ft.maximumLevel;
        }
   }

    
    public static void sleep( int duration ) {
        try {
              Thread.sleep( duration );
            }
        catch (Exception e) {
            }
    }
    

}