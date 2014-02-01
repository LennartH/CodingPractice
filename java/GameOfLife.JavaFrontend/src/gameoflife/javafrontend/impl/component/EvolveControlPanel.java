package gameoflife.javafrontend.impl.component;

import gameoflife.javafrontend.impl.AbstractProvidesComponent;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EvolveControlPanel extends AbstractProvidesComponent {
    
    private final JPanel evolveControlPanel;
    private final JButton evolveButton;
    private final DoubleTextField evolveIntervalField;
    
    public EvolveControlPanel(String evolveButtonText) {
        evolveControlPanel = new JPanel();
        BoxLayout evolveControlPanelLayout = new BoxLayout(evolveControlPanel, BoxLayout.Y_AXIS);
        evolveControlPanel.setLayout(evolveControlPanelLayout);
        
        evolveButton = new JButton(evolveButtonText);
        evolveControlPanel.add(evolveButton);
        
        evolveIntervalField = new DoubleTextField(0.5, 4);
        evolveControlPanel.add(createEvolveIntervalPanel(evolveIntervalField));
    }

    private JPanel createEvolveIntervalPanel(DoubleTextField evolveIntervalField) {
        JPanel evolveIntervalPanel = new JPanel();
        evolveIntervalPanel.add(new JLabel("every"));
        evolveIntervalPanel.add(evolveIntervalField.getComponent());
        evolveIntervalPanel.add(new JLabel("s"));
        return evolveIntervalPanel;
    }

    @Override
    public Component getComponent() {
        return evolveControlPanel;
    }

}
