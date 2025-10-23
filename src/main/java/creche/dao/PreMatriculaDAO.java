package creche.dao;

import creche.model.PreMatricula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PreMatriculaDAO extends GenericDAO {

    // Inserir nova pré-matrícula
    public void inserir(PreMatricula pm) {
        String sql = "INSERT INTO pre_matricula (id_crianca, data_pre_matricula, status, numero_protocolo) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, pm.getIdCrianca());

            if (pm.getDataPreMatricula() != null) {
                stmt.setDate(2, Date.valueOf(pm.getDataPreMatricula()));
            } else {
                stmt.setNull(2, Types.DATE);
            }

            stmt.setString(3, pm.getStatus());
            stmt.setString(4, pm.getNumeroProtocolo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pm.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir pré-matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Buscar pré-matrícula por ID
    public PreMatricula buscarPorId(Long id) {
        String sql = "SELECT * FROM pre_matricula WHERE id = ?";
        PreMatricula pm = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pm = new PreMatricula();
                    pm.setId(rs.getLong("id"));
                    pm.setIdCrianca(rs.getLong("id_crianca"));
                    pm.setStatus(rs.getString("status"));
                    pm.setNumeroProtocolo(rs.getString("numero_protocolo"));

                    Date data = rs.getDate("data_pre_matricula");
                    if (data != null) {
                        pm.setDataPreMatricula(data.toLocalDate());
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar pré-matrícula por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return pm;
    }

    // Buscar todas as pré-matrículas
    public List<PreMatricula> buscarTodos() {
        List<PreMatricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM pre_matricula";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PreMatricula pm = new PreMatricula();
                pm.setId(rs.getLong("id"));
                pm.setIdCrianca(rs.getLong("id_crianca"));
                pm.setStatus(rs.getString("status"));
                pm.setNumeroProtocolo(rs.getString("numero_protocolo"));

                Date data = rs.getDate("data_pre_matricula");
                if (data != null) {
                    pm.setDataPreMatricula(data.toLocalDate());
                }

                lista.add(pm);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todas as pré-matrículas: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // Atualizar pré-matrícula
    public void atualizar(PreMatricula pm) {
        String sql = "UPDATE pre_matricula SET id_crianca = ?, data_pre_matricula = ?, status = ?, numero_protocolo = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, pm.getIdCrianca());

            if (pm.getDataPreMatricula() != null) {
                stmt.setDate(2, Date.valueOf(pm.getDataPreMatricula()));
            } else {
                stmt.setNull(2, Types.DATE);
            }

            stmt.setString(3, pm.getStatus());
            stmt.setString(4, pm.getNumeroProtocolo());
            stmt.setLong(5, pm.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pré-matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Deletar pré-matrícula por ID
    public void deletar(Long id) {
        String sql = "DELETE FROM pre_matricula WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar pré-matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
