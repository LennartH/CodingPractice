package gameoflife.javafrontend.impl.component;

import gameoflife.javafrontend.impl.AbstractProvidesComponent;

import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

public class DoubleTextField extends AbstractProvidesComponent {
    
    private final JFormattedTextField textField;

    public DoubleTextField(double initialValue, int columns) {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        textField = new JFormattedTextField(format);
        textField.setColumns(columns);
        textField.setValue(initialValue);
    }

    @Override
    public Component getComponent() {
        return textField;
    }

}
