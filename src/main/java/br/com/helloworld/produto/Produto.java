/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.helloworld.produto;
import dao.UsuarioDAO;
import java.sql.Connection;
import jdbc.ConnectionFactory;
import model.UserModel;
import service.ProdutoService;

/**
 *
 * @author humbe
 */
public class Produto {

    public static void main(String[] args) {
    //Connection c = new ConnectionFactory().getConnection();
    //if (c != null) System.out.println("Conexão OK");
    //else System.out.println("Conexão NULA");
    
    UsuarioDAO dao = new UsuarioDAO();
        UserModel u = new UserModel("amanda_rodrigues.de.siqueira@hotmail.com", "12345", 1);
        dao.insert(u);
        System.out.println("Usuário criado!");
}
    }


    

