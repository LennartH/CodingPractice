package gameoflife.javafrontend.impl.component;

import java.awt.Component;

import gameoflife.javafrontend.ProvidesComponent;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class IntegerTextField implements ProvidesComponent {
    
    private final JTextField textField;

    public IntegerTextField(int value, int columns) {
        textField = new JTextField(new IntegerDocument(), String.valueOf(value), columns);
    }

    public int getValue() {
        return Integer.parseInt(textField.getText());
    }

    @Override
    public Component getComponent() {
        return textField;
    }
    
    private class IntegerDocument extends PlainDocument {
        private static final long serialVersionUID = 2613991738327661701L;
        
        public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
            try {
                Integer.parseInt(s);
            } catch (Exception ex) {
                return;
            }
            super.insertString(offset, s, attributeSet);
        }

    }

}
