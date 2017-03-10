package snake;

import snake.SnakeGame.Directions;

public class Game{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		 snakegame = new SnakeGame(140,50,Directions.right);
		for(;;){
			try {
				Thread.sleep(400);
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		 	snakegame.newSnakePosition();
		  snakegame.food();
		}
	}
	private static SnakeGame snakegame;

}
