package prosjekt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BestScoreFile {

    File bestScoreFile = new File("bestscore.txt");

    public void writeBestScore(SnakeBody snake){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(bestScoreFile));
            writer.write(String.valueOf(snake.getScore()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readBestScore(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(bestScoreFile));
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static void main(String[] args) {
        BestScoreFile bestScore = new BestScoreFile();
        SnakeBody snake = new SnakeBody();
        SnakeBody snake2 = new SnakeBody();
        snake2.setScore(10);
        snake.setScore(5);
        bestScore.writeBestScore(snake);
        bestScore.writeBestScore(snake2);
        System.out.println(bestScore.readBestScore());
    }

}
