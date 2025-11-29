package dao;

import model.UserModel;
import java.sql.*;
import java.util.*;
import jdbc.ConnectionFactory;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    // INSERT
    public void insert(UserModel u) {
        String sql = "INSERT INTO usuario (userid, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, u.getUserid().toString()); // UUID -> String
            stmt.setString(2, u.getUsername());
            stmt.setString(3, u.getPassword());
            stmt.executeUpdate();
            System.out.println("✅ Usuário inserido!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }


    // SELECT
    public List<UserModel> findAll() {
        List<UserModel> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserModel u = new UserModel(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getLong("roleId")
                );
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return list;
    }

    // UPDATE
    public void update(UserModel u) {
        String sql = "UPDATE users SET username=?, password=? WHERE userid=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getUserid().toString()); // UUID -> String
            stmt.executeUpdate();
            System.out.println("✅ Usuário atualizado!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    // DELETE
    public void delete(UUID id) {
        String sql = "DELETE FROM users WHERE userid=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
            System.out.println("✅ Usuário excluído!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }
    
    // LOGIN / AUTENTICAÇÃO
public UserModel login(String username, String password) {
    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Monta um usuário completo para guardar na sessão
            UserModel u = new UserModel(
                rs.getString("username"),
                rs.getString("password"),
                rs.getLong("roleId")
            );
            u.setUserid(rs.getString("userid"));
            return u;
        }
    } catch (SQLException e) {
        System.out.println("Erro no login: " + e.getMessage());
    }

    return null; // se não encontrou
}

public boolean autenticar(String username, String password) {
    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        return rs.next(); // achou = autenticou
    } catch (SQLException e) {
        System.out.println("Erro ao autenticar: " + e.getMessage());
        return false;
    }
}

}
