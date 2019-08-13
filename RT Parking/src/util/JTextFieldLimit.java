package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {

    private final int iMaxLength;

    public JTextFieldLimit(final int maxlen) {
        super();
        this.iMaxLength = maxlen;
    }

    @Override
    public void insertString(final int offset, final String str, final AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (this.iMaxLength <= 0) // aceitara qualquer no. de caracteres  
        {
            super.insertString(offset, str, attr);
            return;
        }
        final int ilen = this.getLength() + str.length();
        if (ilen <= this.iMaxLength) {
            super.insertString(offset, str, attr); // ...aceita str  
        } else {
            if (this.getLength() == this.iMaxLength) {
                return; // nada a fazer
            }
            final String newStr = str.substring(0, this.iMaxLength - this.getLength());
            super.insertString(offset, newStr, attr);
        }
    }
}
