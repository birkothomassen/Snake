package prosjekt;

import java.util.Random;
import javafx.scene.shape.Rectangle;

public class XYvalues extends Rectangle implements SnakeInterface{
    private int xValue;
    private int yValue;
    //private Rectangle rectangle;

    //Konstruktør1 - for random XYverdi
    public XYvalues(){
        Random rand = new Random();
        xValue = rand.nextInt(9);
        yValue = rand.nextInt(9);
        //this.rectangle = new Rectangle(40,40,Color.GREEN);
    }
    //Konstruktør2 - for spesifikk XYverdi
    public XYvalues(int xValue, int yValue){
        if(xValue < -1 || xValue > 10 || yValue < -1 || yValue > 10){
            throw new IllegalArgumentException("Ugyldig verdi for x eller y");
        }
        this.xValue = xValue;
        this.yValue = yValue;
        //this.rectangle = new Rectangle(40,40,Color.GREEN);
    }


    public int getXvalue(){
        return this.xValue;
    }
    public int getYvalue(){
        return this.yValue;
    }

    public static void main(String[] args) {
        XYvalues xy = new XYvalues();
        System.out.println(xy.getXvalue());
        System.out.println(xy.getYvalue());
    }

}
