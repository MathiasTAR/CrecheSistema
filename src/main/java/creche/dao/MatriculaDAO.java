package creche.dao;

import creche.model.MatriculaEfetivada;
import creche.model.SituacaoMatricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MatriculaDAO extends GenericDAO {

    public MatriculaEfetivada buscarPorIdCrianca(Long idCrianca) {
        String sql = "SELECT * FROM matricula WHERE id_crianca = ? AND situacao_mat = 'ATIVA'";
        MatriculaEfetivada m = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idCrianca);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    m = new MatriculaEfetivada();
                    m.setId(rs.getLong("id_matricula"));
                    m.setIdCrianca(rs.getLong("id_crianca"));
                    m.setIdPreMatricula(rs.getLong("id_prematricula"));

                    if (rs.getDate("data_matricula") != null)
                        m.setDataMatricula(rs.getDate("data_matricula").toLocalDate());

                    m.setSerie(rs.getString("serie"));
                    m.setAnoLetivo(rs.getInt("ano_letivo"));
                    m.setOrientRecebida(rs.getBoolean("orient_recebida"));

                    if (rs.getDate("data_desligamento") != null)
                        m.setDataDesligamento(rs.getDate("data_desligamento").toLocalDate());

                    String situacao = rs.getString("situacao_mat");
                    if (situacao != null)
                        m.setSituacao(SituacaoMatricula.valueOf(situacao));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar matrícula por ID da criança: " + e.getMessage());
            e.printStackTrace();
        }

        return m;
    }
}
