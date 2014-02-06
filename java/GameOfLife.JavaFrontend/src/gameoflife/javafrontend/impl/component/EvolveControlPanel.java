package gameoflife.javafrontend.impl.component;

import gameoflife.javafrontend.impl.AbstractProvidesComponent;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.gameoflife.controller.GameController;

public class EvolveControlPanel extends AbstractProvidesComponent {

    private final GameController gameController;
    private Timer evolutionTimer;
    
    private final JPanel evolveControlPanel;
    private final JButton evolveButton;
    private final DoubleTextField evolutionIntervalField;

    private final String evolveButtonText;
    private boolean evolving = false;
    
    public EvolveControlPanel(String evolveButtonText, GameController gameController) {
        this.gameController = gameController;
        this.evolveButtonText = evolveButtonText;
        
        evolveControlPanel = new JPanel();
        BoxLayout evolveControlPanelLayout = new BoxLayout(evolveControlPanel, BoxLayout.Y_AXIS);
        evolveControlPanel.setLayout(evolveControlPanelLayout);
        
        evolveButton = createEvolveButton(evolveButtonText);
        evolveControlPanel.add(evolveButton);
        
        evolutionIntervalField = new DoubleTextField(0.5, 4);
        evolveControlPanel.add(createEvolveIntervalPanel(evolutionIntervalField));
    }

    private JButton createEvolveButton(String evolveButtonText) {
        JButton evolveButton = new JButton(evolveButtonText);
        evolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (evolving) {
                    stopEvolving();
                } else {
                    startEvolving();
                }
            }
        });
        return evolveButton;
    }

    public void startEvolving() {
        evolutionTimer = new Timer();
        evolutionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameController.calculateNextGeneration();
            }
        }, 0, getEvolveIntervalAsMillis());
        evolving = true;
        evolveButton.setText("Stop");
    }

    private long getEvolveIntervalAsMillis() {
        double intervalInSeconds = evolutionIntervalField.getValue();
        return (long) (intervalInSeconds * 1000);
    }

    public void stopEvolving() {
        evolutionTimer.cancel();
        evolving = false;
        evolveButton.setText(evolveButtonText);
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
