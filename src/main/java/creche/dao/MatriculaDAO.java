package creche.dao;

import creche.model.MatriculaEfetivada;
import creche.model.SituacaoMatricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MatriculaDAO extends GenericDAO {

    public MatriculaEfetivada buscarPorId(Long id) {
        String sql = "SELECT * FROM matricula WHERE id_matricula = ?";
        MatriculaEfetivada m = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    m = mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }

    public MatriculaEfetivada buscarPorIdCrianca(Long idCrianca) {
        String sql = "SELECT * FROM matricula WHERE id_crianca = ? AND situacao_mat = 'ATIVA'";
        MatriculaEfetivada m = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idCrianca);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    m = mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }

    // Método auxiliar para mapear ResultSet
    private MatriculaEfetivada mapResultSet(ResultSet rs) throws SQLException {
        MatriculaEfetivada m = new MatriculaEfetivada();

        m.setId(rs.getLong("id_matricula"));
        m.setIdCrianca(rs.getLong("id_crianca"));
        m.setIdPreMatricula(rs.getLong("id_prematricula"));

        // Datas
        if (rs.getDate("data_matricula") != null) {
            m.setDataMatricula(rs.getDate("data_matricula").toLocalDate());
        }

        // Enum
        String situacaoStr = rs.getString("situacao_mat");
        if (situacaoStr != null) {
            m.setSituacao(SituacaoMatricula.valueOf(situacaoStr));
        }

        // Campos adicionais
        m.setSerie(rs.getString("serie")); // se você quiser criar um enum para série, pode substituir
        m.setAnoLetivo(rs.getInt("ano_letivo"));
        m.setOrientRecebida(rs.getBoolean("orient_recebida"));

        if (rs.getDate("data_desligamento") != null) {
            m.setDataDesligamento(rs.getDate("data_desligamento").toLocalDate());
        }

        return m;
    }
}
