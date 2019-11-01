/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiago.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tiago.model.Fornecedor;

/**
 *
 * @author Tiago
 */
public class FornecedorDAO {

    private final ConexaoJDBC conexao;

    public FornecedorDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoPostJDBC();
    }

    public Long inserir(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
        Long id_fornecedor = null;
        String sqlQuery = "INSERT INTO fornecedor (nome, email, cnpj, comentario) VALUES (?, ?, ?, ?) RETURNING id_fornecedor";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEmail());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setString(4, fornecedor.getComentario());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id_fornecedor = rs.getLong("id_fornecedor");
            }

            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return id_fornecedor;
    }

    public int alterar(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE fornecedor SET nome = ?, email = ?, cnpj = ?, comentario = ? WHERE id_fornecedor = ?";
        int linhasAfetadas = 0;

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEmail());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setString(4, fornecedor.getComentario());
            stmt.setLong(5, fornecedor.getId_fornecedor());

            linhasAfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return linhasAfetadas;
    }

    public int excluir(long id_fornecedor) throws SQLException, ClassNotFoundException {
        int linhasAlfetadas = 0;
        String sqlQuery = "DELETE FROM fornecedor WHERE id_fornecedor = ?";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setLong(1, id_fornecedor);
            linhasAlfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return linhasAlfetadas;
    }

    public Fornecedor selecionar(long id_fornecedor) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setLong(1, id_fornecedor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return parser(rs);
            }
        } catch (SQLException e) {
            throw e;
        }

        return null;
    }

    public List<Fornecedor> listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM fornecedor ORDER BY id_fornecedor";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();

            List<Fornecedor> fornecedor = new ArrayList<>();

            while (rs.next()) {
                fornecedor.add(parser(rs));
            }

            return fornecedor;
        } catch (SQLException e) {
            throw e;
        }
    }

    private Fornecedor parser(ResultSet resultSet) throws SQLException {
        Fornecedor f = new Fornecedor();

        f.setId_fornecedor(resultSet.getLong("id_fornecedor"));
        f.setNome(resultSet.getString("nome"));
        f.setEmail(resultSet.getString("email"));
        f.setCnpj(resultSet.getString("cnpj"));
        f.setComentario(resultSet.getString("comentario"));

        return f;
    }
}
