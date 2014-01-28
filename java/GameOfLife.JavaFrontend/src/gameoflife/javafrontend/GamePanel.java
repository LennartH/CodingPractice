package gameoflife.javafrontend;

import java.awt.event.ActionListener;

public interface GamePanel extends ProvidesComponent {

    public void addActionListenerToNewGameControl(ActionListener listener);

}
