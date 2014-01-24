package gameoflife;


public interface GameBoard {
    
    public void evolve();

    Cell[][] getCells();

	public int getHeight();
	public int getWidth();

}
