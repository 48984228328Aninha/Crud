/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exceptions;

import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;

/**
 *
 * @author humbe
 */
public class TestConnection {

   
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com o banco");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco" + e.getMessage());
        }
    }
    
}
