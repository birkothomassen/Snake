package prosjekt;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class snakeController{
    private SnakeBody snake;
    private Apple apple;
    private boolean gameover = false;
    private boolean hasSnakeMoved = false;
    private BestScoreFile bestscorefile = new BestScoreFile();

    @FXML
    private Timeline klokke;

    @FXML
    private GridPane snakegrid;

    @FXML
    private AnchorPane background;

    @FXML
    private Pane scne;

    @FXML
    private Button start;

    @FXML
    private Button restart;

    @FXML
    private TextArea scoreBoard;

    @FXML
    private TextArea bestScore;

    @FXML
    private Label gameoverlabel;

    @FXML
    private Label newrecordlabel;

    @FXML
    private Label winlabel;

    @FXML
    private Label birkssnake;




    public void startsnake(){
        restart.setVisible(false);

        snake = new SnakeBody();
        apple = new Apple(snake);

        drawsnake(snake);
        drawApple(apple);

        start.setVisible(false);

        this.klokke = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> {
            if(!gameover){

                if (snake.hasEatenApple(apple)) {
                    // Legg til en ny del på slangen når den spiser et eple
                    snake.move(apple); //denne setningen gjør at slangen vokser dobbelt og når fjernet vokser den ikke i det hele tatt
                    System.out.println("Eple spist");
                
                    // Oppdater eplets posisjon og tegn det på nytt
                    apple = new Apple(snake);
                    drawApple(apple);
                }
                else{
                    snake.move(apple);
                    snake.getSnakeBody().remove(0);
                    System.out.println("Eple ikke spist");
                }
                hasSnakeMoved = true;
    
            // Tegn slangen på nytt
            snakegrid.getChildren().removeIf(node -> node instanceof Rectangle);
            drawsnake(snake);
            }
            //Må også sjekke om slangen kolliderer med seg selv
            if(snake.checkWallCollision()||snake.checkCollision()){
                gameover = true;
                gameOverLabel();
                newRecordLabel();
                klokke.stop();
            //Dersom man har fylt hele brettet
            if(win()){
                winlabel.setText("You Win!");
                winlabel.setVisible(true);
                klokke.stop();
            }
            if(gameover){
                restart.setVisible(true);
            }
            }
            
        
        //Oppdater score
        updateScore();
        bestScore();
        //Sjekke om bestscore skal oppdateres
        updateBestScore(snake);
        

        }));
        updateBestScore(snake);
        klokke.setCycleCount(Timeline.INDEFINITE); // Gjenta tidslinjen uendelig
        klokke.play();
        }
    
    public void restartsnake(){
        gameover = false;
        klokke.stop();
        snakegrid.getChildren().removeIf(node -> node instanceof Rectangle);
        gameoverlabel.setVisible(false);
        newrecordlabel.setVisible(false);
        startsnake();
        System.out.println("Restart");
    }
    
    

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (hasSnakeMoved) {
            KeyCode code = event.getCode();
            if (code == KeyCode.W) {
            snake.setDirection("UP");
         } else if (code == KeyCode.S) {
            snake.setDirection("DOWN");
        } else if (code == KeyCode.A) {
            snake.setDirection("LEFT");
        } else if (code == KeyCode.D) {
            snake.setDirection("RIGHT");
        }
            
        }
        
        hasSnakeMoved = false;
    }

    @FXML
    public void updateScore(){
        scoreBoard.setText("Score: "+snake.getScore());
    }

    @FXML
    public void bestScore(){
        bestScore.setText("Best Score: "+ bestscorefile.readBestScore());
    }

    @FXML
    public void gameOverLabel(){
        if(gameover){
            gameoverlabel.setText("Game Over");
            gameoverlabel.setVisible(true);
        System.out.println("Game Over");
        }
    }

    @FXML
    public void newRecordLabel(){
        if(gameover){
            if(snake.getScore() > bestscorefile.readBestScore()){
                newrecordlabel.setText("But, New Record!");
                newrecordlabel.setVisible(true);
                System.out.println("But, New Record!");
        }
    }
    }

    //Filhåndtering
    public void updateBestScore(SnakeBody snake){
        if (gameover) {
            if(snake.getScore() > bestscorefile.readBestScore()){
                bestscorefile.writeBestScore(snake);
            }  
        }
    }


    //Tegn slangen
    public void drawsnake(SnakeBody snake){
        for(XYvalues xyvalue : snake.getSnakeBody()){
            Rectangle snakepart = new Rectangle(40,40);
            snakepart.setFill(javafx.scene.paint.Color.GREEN);
            if(xyvalue.getXvalue()>=0 && xyvalue.getYvalue()>=0){
            snakegrid.add(snakepart, xyvalue.getXvalue(), xyvalue.getYvalue());
            }
            }
    }

    //Metode for forksjellig farge på hode og kropp
    public void drawsnake2(SnakeBody snake){
        Random rand = new Random();
        Rectangle snakeHead = new Rectangle(40,40);
        snakeHead.setFill(javafx.scene.paint.Color.GREEN);
        XYvalues snakeheadxy = snake.getSnakeBody().get(snake.getSnakeBody().size()-1);
        if(snakeheadxy.getXvalue()>=0 && snakeheadxy.getYvalue()>=0 && snakeheadxy.getXvalue()<=9 && snakeheadxy.getYvalue()<=9){
            snakegrid.add(snakeHead, snakeheadxy.getXvalue(), snakeheadxy.getYvalue());
        }

        for(int i = 0 ; i < snake.getSnakeBody().size()-1; i++){
            Rectangle snakepart = new Rectangle(40,40);
            Color randcolor = Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
            Color greencolor = Color.rgb(0,255,0);
            snakepart.setFill(randcolor);
            snakegrid.add(snakepart, snake.getSnakeBody().get(i).getXvalue(), snake.getSnakeBody().get(i).getYvalue());

        }
    }

    //Tegn eplet
    public void drawApple(Apple apple){
        snakegrid.getChildren().removeIf(node -> node instanceof Circle);
        Circle apple1 = new Circle(20);
        apple1.setFill(javafx.scene.paint.Color.RED);
        snakegrid.add(apple1, apple.getXvalue(), apple.getYvalue());
    }

    public boolean win(){
        if(snake.getSnakeBody().size() == 100){
            return true;
        }
        else{
            return false;

        }
    }

    public static void main(String[] args) {
        
    }
}
