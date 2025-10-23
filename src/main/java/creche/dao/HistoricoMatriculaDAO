package creche.dao;

import creche.model.HistoricoMatricula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoMatriculaDAO {

    private Connection connection;

    public HistoricoMatriculaDAO(Connection connection) {
        this.connection = connection;
    }

    // Inserir um novo histórico
    public void inserir(HistoricoMatricula historico) throws SQLException {
        String sql = "INSERT INTO HISTORICO_MATRICULA "
                + "(ID_MATRICULA, DATA_EVENTO, TIPO_EVENTO, DESCRICAO, USUARIO_REGISTRO, OBSERVACAO) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, historico.getIdMatricula());
            stmt.setTimestamp(2, Timestamp.valueOf(historico.getDataEvento()));
            stmt.setString(3, historico.getTipoEvento());
            stmt.setString(4, historico.getDescricao());
            stmt.setString(5, historico.getUsuarioRegistro()); // aqui corrigir se quiser: getUsuarioRegistro
            stmt.setString(6, historico.getObservacao());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    historico.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    // Atualizar histórico existente
    public void atualizar(HistoricoMatricula historico) throws SQLException {
        String sql = "UPDATE HISTORICO_MATRICULA SET "
                + "ID_MATRICULA = ?, DATA_EVENTO = ?, TIPO_EVENTO = ?, DESCRICAO = ?, "
                + "USUARIO_REGISTRO = ?, OBSERVACAO = ? "
                + "WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, historico.getIdMatricula());
            stmt.setTimestamp(2, Timestamp.valueOf(historico.getDataEvento()));
            stmt.setString(3, historico.getTipoEvento());
            stmt.setString(4, historico.getDescricao());
            stmt.setString(5, historico.getUsuarioRegistro());
            stmt.setString(6, historico.getObservacao());
            stmt.setLong(7, historico.getId());

            stmt.executeUpdate();
        }
    }

    // Deletar histórico por ID
    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM HISTORICO_MATRICULA WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    // Buscar histórico por ID
    public HistoricoMatricula buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM HISTORICO_MATRICULA WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearHistorico(rs);
                }
            }
        }
        return null;
    }

    // Listar todos os históricos
    public List<HistoricoMatricula> listarTodos() throws SQLException {
        List<HistoricoMatricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM HISTORICO_MATRICULA";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearHistorico(rs));
            }
        }
        return lista;
    }

    // Método auxiliar para mapear ResultSet para objeto
    private HistoricoMatricula mapearHistorico(ResultSet rs) throws SQLException {
        HistoricoMatricula historico = new HistoricoMatricula();
        historico.setId(rs.getLong("ID"));
        historico.setIdMatricula(rs.getLong("ID_MATRICULA"));
        historico.setDataEvento(rs.getTimestamp("DATA_EVENTO").toLocalDateTime());
        historico.setTipoEvento(rs.getString("TIPO_EVENTO"));
        historico.setDescricao(rs.getString("DESCRICAO"));
        historico.setUsuarioRegistro(rs.getString("USUARIO_REGISTRO"));
        historico.setObservacao(rs.getString("OBSERVACAO"));
        return historico;
    }
}
