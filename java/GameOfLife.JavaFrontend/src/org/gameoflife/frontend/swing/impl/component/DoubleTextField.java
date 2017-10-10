package org.gameoflife.frontend.swing.impl.component;

import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.gameoflife.frontend.swing.impl.AbstractProvidesComponent;

public class DoubleTextField extends AbstractProvidesComponent {
    
    private final JTextField textField;

    public DoubleTextField(double initialValue, int columns) {
        textField = new JTextField(columns);
        textField.setDocument(new PositiveDoubleDocument());
        textField.setText(String.valueOf(initialValue));
    }

    public double getValue() {
        return Double.parseDouble(textField.getText());
    }

    @Override
    public Component getComponent() {
        return textField;
    }
    
    private class PositiveDoubleDocument extends PlainDocument {
        private static final long serialVersionUID = 2613991738327661701L;
        
        public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
            try {
                String value = textField.getText() + s;
                Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return;
            }
            super.insertString(offset, s, attributeSet);
        }

    }

}
