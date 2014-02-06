package gameoflife.javafrontend.impl.component;

import gameoflife.javafrontend.impl.AbstractProvidesComponent;

import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class PositiveIntegerWithMaximumTextField extends AbstractProvidesComponent {
    
    private final JTextField textField;

    public PositiveIntegerWithMaximumTextField(int value, int columns, int maximum) {
        textField = new JTextField(new PositiveIntegerWithMaximumDocument(maximum), String.valueOf(value), columns);
    }

    public int getValue() {
        return Integer.parseInt(textField.getText());
    }

    @Override
    public Component getComponent() {
        return textField;
    }
    
    private class PositiveIntegerWithMaximumDocument extends PlainDocument {
        private static final long serialVersionUID = 2613991738327661701L;
        
        private int maximum;
        
        public PositiveIntegerWithMaximumDocument(int maximum) {
            this.maximum = maximum;
        }
        
        public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
            try {
                Integer.parseInt(s);
                
                int value = textField != null ? Integer.parseInt(textField.getText() + s) : 0;
                if (valueIsOutOfBounds(value)) {
                    return;
                }
            } catch (NumberFormatException e) {
                return;
            }
            super.insertString(offset, s, attributeSet);
        }

        private boolean valueIsOutOfBounds(int value) {
            return value > maximum;
        }

    }

}
