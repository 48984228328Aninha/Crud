package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    
    private final String url = "jdbc:mysql://localhost:3306/produto?useSSL=false&serverTimezone=UTC";
    private final String usuario = "root";
    private final String senha = "admin"; 

    public Connection getConnection() {
        try {
            // Registra explicitamente o driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("✅ Conexão estabelecida com sucesso!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao se conectar ao banco de dados: " + e.getMessage());
        }
        return null;
    }

    // Teste rápido
    public static void main(String[] args) {
        Connection c = new ConnectionFactory().getConnection();
        if (c != null) System.out.println("Conexão OK");
        else System.out.println("Conexão NULA");
    }
}
