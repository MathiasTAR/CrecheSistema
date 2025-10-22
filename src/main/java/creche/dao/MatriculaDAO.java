package creche.dao;

import creche.model.MatriculaEfetivada;
import creche.model.SituacaoMatricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO extends GenericDAO {

    public Long cadastrarMatricula(MatriculaEfetivada m) {
        String sql = "INSERT INTO matricula (" +
                "id_crianca, id_prematricula, data_matricula, serie, ano_letivo, orient_recebida, situacao_mat" +
                ") VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, m.getIdCrianca());
            stmt.setLong(2, m.getIdPreMatricula());
            stmt.setDate(3, java.sql.Date.valueOf(m.getDataMatricula()));
            stmt.setString(4, m.getSerie());
            stmt.setInt(5, m.getAnoLetivo());
            stmt.setBoolean(6, m.isOrientRecebida());
            stmt.setString(7, m.getSituacao().name());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar matrícula: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Busca dinâmica de matrículas com filtros opcionais
    public List<MatriculaEfetivada> buscarPorFiltros(
            Long idMatricula, Long idCrianca, Long idPreMatricula,
            LocalDate dataMatricula, String serie, Integer anoLetivo,
            Boolean orientRecebida, LocalDate dataDesligamento,
            SituacaoMatricula situacao
    ) {
        List<MatriculaEfetivada> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM matricula WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (idMatricula != null) {
            sql.append(" AND id_matricula = ?");
            parametros.add(idMatricula);
        }
        if (idCrianca != null) {
            sql.append(" AND id_crianca = ?");
            parametros.add(idCrianca);
        }
        if (idPreMatricula != null) {
            sql.append(" AND id_pre_matricula = ?");
            parametros.add(idPreMatricula);
        }
        if (dataMatricula != null) {
            sql.append(" AND data_matricula = ?");
            parametros.add(java.sql.Date.valueOf(dataMatricula));
        }
        if (serie != null && !serie.isEmpty()) {
            sql.append(" AND serie = ?");
            parametros.add(serie);
        }
        if (anoLetivo != null) {
            sql.append(" AND ano_letivo = ?");
            parametros.add(anoLetivo);
        }
        if (orientRecebida != null) {
            sql.append(" AND orient_recebida = ?");
            parametros.add(orientRecebida);
        }
        if (dataDesligamento != null) {
            sql.append(" AND data_desligamento = ?");
            parametros.add(java.sql.Date.valueOf(dataDesligamento));
        }
        if (situacao != null) {
            sql.append(" AND situacao_mat = ?");
            parametros.add(situacao.name());
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MatriculaEfetivada m = mapResultSetParaMatricula(rs);
                    lista.add(m);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar matrículas com filtros: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    private MatriculaEfetivada mapResultSetParaMatricula(ResultSet rs) throws SQLException {
        MatriculaEfetivada m = new MatriculaEfetivada();
        m.setId(rs.getLong("id_matricula"));
        m.setIdCrianca(rs.getLong("id_crianca"));
        m.setIdPreMatricula(rs.getLong("id_pre_matricula"));
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
