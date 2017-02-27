package snake;

import snake.SnakeGame.Directions;

public class Game {
	
	
	public static void main(String[] args) {
		SnakeGame game = new SnakeGame(80,40,Directions.left);
		for(int i=0;i<10;i++){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.newSnakePosition();
		if(i==5)game.setDirection(Directions.up);
		}
		
	
	}


}
