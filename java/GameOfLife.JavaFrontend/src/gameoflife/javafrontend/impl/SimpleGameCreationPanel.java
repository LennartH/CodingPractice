package gameoflife.javafrontend.impl;

import gameoflife.javafrontend.GameCreationPanel;
import gameoflife.javafrontend.impl.component.IntegerTextField;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.gameoflife.controller.GameController;

public class SimpleGameCreationPanel implements GameCreationPanel {

    private static final int STANDARD_WIDTH = 25;
    private static final int STANDARD_HEIGHT = 25;
    
    private final JPanel controlsPanel;
    private final IntegerTextField widthField;
    private final IntegerTextField heightField;

    public SimpleGameCreationPanel(final GameController gameController) {
        controlsPanel = new JPanel(new FlowLayout());
        
        controlsPanel.add(createLabel("Width: "));
        widthField = new IntegerTextField(STANDARD_WIDTH, 3);
        controlsPanel.add(widthField.getComponent());
        
        controlsPanel.add(createLabel("Height: "));
        heightField = new IntegerTextField(STANDARD_HEIGHT, 3);
        controlsPanel.add(heightField.getComponent());
        
        JButton createNewGameButton = new JButton("Create");
        createNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.createNewGame(getWidth(), getHeight());
            }
        });
        controlsPanel.add(createNewGameButton);
    }

    private JLabel createLabel(String text) {
        return new JLabel(text);
    }

    private int getHeight() {
        return heightField.getValue();
    }

    private int getWidth() {
        return widthField.getValue();
    }

    @Override
    public Component getComponent() {
        return controlsPanel;
    }

}