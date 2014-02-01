package gameoflife.javafrontend.impl.component;

import gameoflife.javafrontend.impl.AbstractProvidesComponent;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenerationCountLabel extends AbstractProvidesComponent {
    
    private final JPanel labelsPanel;
    private final JLabel countLabel;
    
    public GenerationCountLabel(String text) {
        labelsPanel = new JPanel();
        
        labelsPanel.add(new JLabel(text));
        countLabel = new JLabel();
        labelsPanel.add(countLabel);
        
        setGenerationCount(0);
    }
    
    private int getGenerationCount() {
        return Integer.parseInt(countLabel.getText());
    }
    
    public void setGenerationCount(int count) {
        countLabel.setText(String.valueOf(Math.max(count, 0)));
    }

    public void incrementGenerationCount() {
        setGenerationCount(getGenerationCount() + 1);
    }

    @Override
    public Component getComponent() {
        return labelsPanel;
    }

}
