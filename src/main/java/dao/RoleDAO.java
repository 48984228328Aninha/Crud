package dao;


import model.RoleModel;
import java.sql.*;
import java.util.*;
import jdbc.ConnectionFactory;

public class RoleDAO {
    private Connection connection;

    public RoleDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void insert(RoleModel r) {
        String sql = "INSERT INTO roles (roleId, rolename) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, r.getRoleId());
            stmt.setString(2, r.getRolename());
            stmt.executeUpdate();
            System.out.println("✅ Role inserida!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir role: " + e.getMessage());
        }
    }

    public List<RoleModel> findAll() {
        List<RoleModel> list = new ArrayList<>();
        String sql = "SELECT * FROM roles";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new RoleModel(
                    rs.getString("rolename"),
                    rs.getLong("roleId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar roles: " + e.getMessage());
        }
        return list;
    }

    public void update(RoleModel r) {
        String sql = "UPDATE roles SET rolename=? WHERE roleId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getRolename());
            stmt.setLong(2, r.getRoleId());
            stmt.executeUpdate();
            System.out.println("✅ Role atualizada!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar role: " + e.getMessage());
        }
    }

    public void delete(Long roleId) {
        String sql = "DELETE FROM roles WHERE roleId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roleId);
            stmt.executeUpdate();
            System.out.println("✅ Role deletada!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar role: " + e.getMessage());
        }
    }
}
