package prosjekt;

import java.util.ArrayList;
import java.util.List;

public class SnakeBody {
    private List<XYvalues> snakebody = new ArrayList<XYvalues>();
    private String direction;
    private int score = 0;
    private int bestScore = 0;

    public SnakeBody(){
        snakebody.add(new XYvalues(0,0));
        snakebody.add(new XYvalues(1,0));
        this.direction = "RIGHT";
    }

    //Metode for å bevege snakebody
    public void move(Apple apple){
        XYvalues lastElement = snakebody.get(snakebody.size()-1);
        int lastX = lastElement.getXvalue();
        int lastY = lastElement.getYvalue();

        switch (getDirection()) {
            case "RIGHT":
                snakebody.add(new XYvalues(lastX+1, lastY));
                break;
            case "LEFT":
                snakebody.add(new XYvalues(lastX-1, lastY));
                break;
            case "UP":
                snakebody.add(new XYvalues(lastX, lastY-1));
                break;
            case "DOWN":
                snakebody.add(new XYvalues(lastX, lastY+1));
                break;
            default:
                throw new IllegalArgumentException("Ugyldig retning");
        }
    }

    //Metode for å få retning
    public String getDirection(){
        return direction;
    }


    //Metode for å sjekke kollisjon
    public boolean checkCollision(){
        XYvalues lastElement = snakebody.get(snakebody.size()-1);
        int lastX = lastElement.getXvalue();
        int lastY = lastElement.getYvalue();

        for(int i = snakebody.size()-2; i>=0 ; i--){
            XYvalues currentElement = snakebody.get(i);
            if((lastX == currentElement.getXvalue()) && (lastY == currentElement.getYvalue())){
                System.out.println("kolliderte med seg selv");
                return true;
            }
        }
        return false;
    }
    //Sjekke kolisjon med vegg
    public boolean checkWallCollision(){
        XYvalues lastElement = snakebody.get(snakebody.size()-1);
        int lastX = lastElement.getXvalue();
        int lastY = lastElement.getYvalue();

        if(lastX < 0 || lastX > 9 || lastY < 0 || lastY > 9){
            System.out.println("kolliderte med veggen");
            return true;
        }
        return false;
    }

    //Metode for å sette retning
    public void setDirection(String newdirection){
        switch (newdirection) {
            case "RIGHT":
                if(!direction.equals("LEFT")){
                    direction = newdirection;
                }
                break;
            case "LEFT":
                if(!direction.equals("RIGHT")){
                    direction = newdirection;
                }
                break;
            case "UP":
                if(!direction.equals("DOWN")){
                    direction = newdirection;
                }
                break;
            case "DOWN":    
                if(!direction.equals("UP")){
                    direction = newdirection;
                }
                break;
            default:
                throw new IllegalArgumentException("Ugyldig retning");
        }
    }

    //Metode for å sjekke om slangehodet har spist et eple
    public boolean hasEatenApple(Apple apple){
        XYvalues lastElement = snakebody.get(snakebody.size()-1);
        int lastX = lastElement.getXvalue();
        int lastY = lastElement.getYvalue();

        if(lastX == apple.getXvalue() && lastY== apple.getYvalue()){
            score++;
            return true;
        }
        else{
            return false;
        }
    }

    //Metode for å returnere snakebody
    public List<XYvalues> getSnakeBody(){
        return snakebody;
    }

    public void setScore(int score){
        this.score = score;
    }

    //Metode for å returnere score
    public int getScore(){
        return score;
    }

    //Metode for å returnere bestScore
    public int getBestScore(int score){
        if(score>bestScore){
            bestScore = score;
        }
        return bestScore;
    }



    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i = 1; i<= snakebody.size();i++){
            result.append("Del "+i+": ("+snakebody.get(snakebody.size()-i).getXvalue()+","+snakebody.get(snakebody.size()-i).getYvalue()+")"+("\n"));
        }
        
        return result.toString();
    }

public static void main(String[] args) {
    SnakeBody snake = new SnakeBody();
    Apple apple = new Apple(1,0);
    
    System.out.println(snake);
    snake.move(apple);
    System.out.println(snake);
    System.out.println(snake.getSnakeBody().get(snake.getSnakeBody().size()-1).getXvalue());

    

}
}
