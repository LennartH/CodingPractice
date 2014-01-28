package gameoflife.javafrontend;

import java.awt.event.ActionListener;

public interface GameControlsPanel extends ProvidesComponent {

    public void addActionListenerToNewGameControl(ActionListener listener);

}
