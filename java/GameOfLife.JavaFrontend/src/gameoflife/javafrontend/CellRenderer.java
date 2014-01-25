package gameoflife.javafrontend;

import org.gameoflife.backend.shared.CellState;

public interface CellRenderer extends ProvidesComponent {

	public void setState(CellState state);
	public CellState getState();

}
