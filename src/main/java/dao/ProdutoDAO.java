package dao;

import java.sql.*;
import java.util.*;
import jdbc.ConnectionFactory;
import model.ProdutoModel;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public List<ProdutoModel> findAll() {
        List<ProdutoModel> list = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutoModel p = new ProdutoModel();

                p.setIdProduto(rs.getLong("idProduto"));
                p.setNome_produto(rs.getString("nome_produto"));
                p.setDescricao_produto(rs.getString("descricao_produto"));
                p.setQuantidade_estoque(rs.getInt("quantidade_estoque"));
                p.setPreco(rs.getDouble("preco"));
                p.setData_cadastro(rs.getDate("data_cadastro"));

                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return list;
    }

    public void insert(ProdutoModel p) {

        String sql = "INSERT INTO produto (nome_produto, descricao_produto, quantidade_estoque, preco, data_cadastro) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNome_produto());
            stmt.setString(2, p.getDescricao_produto());
            stmt.setInt(3, p.getQuantidade_estoque());
            stmt.setDouble(4, p.getPreco());
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis())); // agora gera data automática

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                p.setIdProduto(rs.getLong(1));
            }

            System.out.println("✅ Produto inserido!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public void update(ProdutoModel p) {

        String sql = "UPDATE produto SET nome_produto=?, descricao_produto=?, quantidade_estoque=?, preco=?, data_cadastro=? WHERE idProduto=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, p.getNome_produto());
            stmt.setString(2, p.getDescricao_produto());
            stmt.setInt(3, p.getQuantidade_estoque());
            stmt.setDouble(4, p.getPreco());
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            stmt.setLong(6, p.getIdProduto());

            stmt.executeUpdate();

            System.out.println("✅ Produto atualizado!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void delete(Long id) {

        String sql = "DELETE FROM produto WHERE idProduto=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

            System.out.println("✅ Produto excluído!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }
}
