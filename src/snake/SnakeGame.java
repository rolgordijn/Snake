package snake;


public class SnakeGame {
	

public enum Directions
{
Up, right, down, left;
}
	
	private Directions dir;
	private int size;
	private boolean[][] pivot;
	private char[][] display;

	public SnakeGame(int size, Directions dir){
	this.dir=dir;
	this.size = size;
    pivot = new boolean[size][size];
    display = new char[size][size];
}
	
	public void init(){
		
	}
	
	public void print(){
	
	}







}
