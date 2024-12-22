package prosjekt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;



public class Apple implements SnakeInterface {
    private int x;
    private int y;
    private List<XYvalues> possibleXYvalues = new ArrayList<XYvalues>();

    public Apple(SnakeBody snakebody){
        Random rand = new Random();
        addpossibleXYvalues();

        Iterator<XYvalues> iterator = possibleXYvalues.iterator();
        while (iterator.hasNext()) {
            XYvalues xy2 = iterator.next();
            for(XYvalues xy : snakebody.getSnakeBody()){
                if(xy.getXvalue() == xy2.getXvalue() && xy.getYvalue() == xy2.getYvalue()){
                    iterator.remove();

                }
            }
            
        }
        if (possibleXYvalues.size() == 0) {
            ;
        }
        else{
            int randomIndex = rand.nextInt(possibleXYvalues.size());
            this.x = possibleXYvalues.get(randomIndex).getXvalue();
            this.y = possibleXYvalues.get(randomIndex).getYvalue();
        }

    }
    public Apple(int x, int y){
        if (x > 9 || y > 9 || x < 0 || y < 0){
            throw new IllegalArgumentException("Ugyldig verdi for x eller y");
        }
        this.x = x;
        this.y = y;
    }

    public int getXvalue(){
        return this.x;
    }

    public int getYvalue(){
        return this.y;
    }

    public List<XYvalues> getPossibleXYvalues(){
        return possibleXYvalues;
    }

    public void generateNewApple(){
        Random rand = new Random();
        this.x = rand.nextInt(9);
        this.y = rand.nextInt(9);
    }

    public void addpossibleXYvalues(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                possibleXYvalues.add(new XYvalues(i,j));
            }
        }
    }

    public static void main(String[] args) {
        SnakeBody snake = new SnakeBody();
        Apple apple = new Apple(snake);
        System.out.println(apple.getXvalue());
        System.out.println(apple.getYvalue());
        
    }
}
