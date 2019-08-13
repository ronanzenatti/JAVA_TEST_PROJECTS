
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ronan
 */
public class LimiteDigitos extends PlainDocument{
    private int qtdeMax;
    
    public LimiteDigitos(int maxLen){
        super();
        if(maxLen <= 0)
            throw new IllegalArgumentException("Especifique a quantidade !");
        qtdeMax = maxLen;
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr){
//        throws BadLo
    }
}
