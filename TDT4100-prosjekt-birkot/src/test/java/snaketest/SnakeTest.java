package snaketest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import prosjekt.Apple;
import prosjekt.BestScoreFile;
import prosjekt.SnakeBody;
import prosjekt.XYvalues;

public class SnakeTest {
    //Test for XYvalues klassen
    @Test 
    @DisplayName("Test constructorXY")
    void testConstructorXY(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            XYvalues xy = new XYvalues(5, 15);});

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            XYvalues xy = new XYvalues(-2, 8);});
    }

    @Test
    @DisplayName("Test getXvalue")
    void testGetXvalue(){
        XYvalues xy = new XYvalues(5, 8);
        Assertions.assertEquals(5, xy.getXvalue());
    }

    @Test
    @DisplayName("Test getYvalue")
    void testGetYvalue(){
        XYvalues xy = new XYvalues(5, 8);
        Assertions.assertEquals(8, xy.getYvalue());
    }


    //Test for Apple klassen
    @Test
    @DisplayName("Test constructorapple")
    void testconstructerapple(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Apple eple = new Apple(11, 1);});
        
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Apple eple = new Apple(4, 13);});
        
    }

    @Test
    @DisplayName("Test constructorapple2")
    void testconstructerapple2(){
        SnakeBody snake = new SnakeBody();
        Apple eple = new Apple(snake);

        Assertions.assertTrue(eple.getXvalue() >= 0 && eple.getXvalue() <= 9);
        Assertions.assertTrue(eple.getYvalue() >= 0 && eple.getYvalue() <= 9);
        Assertions.assertEquals(98,eple.getPossibleXYvalues().size());
    }

    @Test
    @DisplayName("Test getXvalueapple")
    void testgetXvalueapple(){
        Apple eple = new Apple(5,8);
        Assertions.assertEquals(5, eple.getXvalue());
    }

    @Test
    @DisplayName("Test getYvalueapple")
    void testgetYvalueapple(){
        Apple eple = new Apple(5,8);
        Assertions.assertEquals(8, eple.getYvalue());
    }


    //Test for SnakeBody klassen
    @Test
    @DisplayName("Test constructorSnake")
    void testconstructorSnake(){
        SnakeBody snake = new SnakeBody();
        Assertions.assertEquals(2, snake.getSnakeBody().size());
        Assertions.assertEquals("RIGHT",snake.getDirection());
        Assertions.assertEquals(0, snake.getScore());
    }

    @Test
    @DisplayName("Test setDirection")
    void testsetDirection(){
        SnakeBody snake = new SnakeBody();
        snake.setDirection("UP");
        Assertions.assertEquals("UP", snake.getDirection());
    }

    @Test
    @DisplayName("Test filhåndtering")
    void testfilhåndtering(){
        SnakeBody snake = new SnakeBody();
        BestScoreFile bestScore = new BestScoreFile();
        snake.setScore(5);
        bestScore.writeBestScore(snake);
        Assertions.assertEquals(5, bestScore.readBestScore());

    }


}
