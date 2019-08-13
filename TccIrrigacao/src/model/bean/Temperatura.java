/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;


/**
 *
 * @author Aluno
 */
public class Temperatura {
    private int id_temp;
    private double valor_temp;
    private Date data_hora;

    public int getId_temp() {
        return id_temp;
    }

    public void setId_temp(int id_temp) {
        this.id_temp = id_temp;
    }

    public double getValor_temp() {
        return valor_temp;
    }

    public void setValor_temp(double valor_temp) {
        this.valor_temp = valor_temp;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }
    
    
    
    
    
}
