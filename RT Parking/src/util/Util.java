/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author ronan
 */
public class Util {

    private static final DecimalFormat dFormat = new DecimalFormat("##,###,###.00");
    private static final NumberFormatter formatter = new NumberFormatter(dFormat);

    public static DefaultFormatterFactory getMoney() {

        formatter.setFormat(dFormat);
        formatter.setAllowsInvalid(false);
        formatter.setValueClass(Double.class);
        DefaultFormatterFactory formatterFactory = new DefaultFormatterFactory(formatter);
        return formatterFactory;
    }

}
