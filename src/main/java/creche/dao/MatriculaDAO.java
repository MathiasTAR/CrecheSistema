package creche.dao;

import creche.model.MatriculaEfetivada;
import creche.model.SituacaoMatricula;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO extends GenericDAO {

    public Long cadastrarMatricula(MatriculaEfetivada m) {
        String sql = "INSERT INTO matricula (id_crianca, id_prematricula, data_matricula, serie, ano_letivo, orient_recebida, situacao_mat) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, m.getIdCrianca());
            stmt.setLong(2, m.getIdPreMatricula());
            stmt.setDate(3, Date.valueOf(m.getDataMatricula()));
            stmt.setString(4, m.getSerie());
            stmt.setInt(5, m.getAnoLetivo());
            stmt.setBoolean(6, m.isOrientRecebida());
            stmt.setString(7, m.getSituacao().name());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar matrícula: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public MatriculaEfetivada buscarPorIdCrianca(Long idCrianca) {
        String sql = "SELECT * FROM matricula WHERE id_crianca = ? AND situacao_mat = 'ATIVA'";
        MatriculaEfetivada m = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idCrianca);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    m = mapResultSetParaMatricula(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar matrícula por ID da criança: " + e.getMessage());
            e.printStackTrace();
        }

        return m;
    }

    private MatriculaEfetivada mapResultSetParaMatricula(ResultSet rs) throws SQLException {
        MatriculaEfetivada m = new MatriculaEfetivada();
        m.setId(rs.getLong("id_matricula"));
        m.setIdCrianca(rs.getLong("id_crianca"));
        m.setIdPreMatricula(rs.getLong("id_prematricula"));
        m.setDataMatricula(rs.getDate("data_matricula") != null ? rs.getDate("data_matricula").toLocalDate() : null);
        m.setSerie(rs.getString("serie"));
        m.setAnoLetivo(rs.getInt("ano_letivo"));
        m.setOrientRecebida(rs.getBoolean("orient_recebida"));
        m.setDataDesligamento(rs.getDate("data_desligamento") != null ? rs.getDate("data_desligamento").toLocalDate() : null);
        String situ = rs.getString("situacao_mat");
        if (situ != null) m.setSituacao(SituacaoMatricula.valueOf(situ));
        return m;
    }
}
