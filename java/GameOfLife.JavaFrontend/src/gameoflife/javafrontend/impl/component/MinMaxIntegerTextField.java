package gameoflife.javafrontend.impl.component;

import java.awt.Component;

import gameoflife.javafrontend.ProvidesComponent;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MinMaxIntegerTextField implements ProvidesComponent {
    
    private final JTextField textField;

    public MinMaxIntegerTextField(int value, int columns, int minimum, int maximum) {
        textField = new JTextField(new MinMaxIntegerDocument(minimum, maximum), String.valueOf(value), columns);
    }

    public int getValue() {
        return Integer.parseInt(textField.getText());
    }

    @Override
    public Component getComponent() {
        return textField;
    }
    
    private class MinMaxIntegerDocument extends PlainDocument {
        private static final long serialVersionUID = 2613991738327661701L;
        
        private int minimum;
        private int maximum;
        
        public MinMaxIntegerDocument(int minimum, int maximum) {
            this.minimum = minimum;
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
            return value < minimum || value > maximum;
        }

    }

}
