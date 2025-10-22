package creche.dao;

import creche.model.Crianca;
import creche.model.CorRaca;
import creche.model.Sexo;
import creche.model.MobilidadeReduzida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CriancaDAO extends GenericDAO {

    public Long cadastrarCrianca(Crianca c) {
        String sql = "INSERT INTO crianca (" +
                "nome, foto, data_nascimento, sexo, cor_raca, possui_irmao_creche, possui_irmao_gemeo, " +
                "cartsus, unidade_saude, municipio_nascimento, cartorio_registro, certidao_nascimento_num, " +
                "data_emissao_certidao, org_emissor_certidao, restricao_alimentar, descricao_restricoes_alimentares, " +
                "alergia, problema_saude, mob_red, def_multi, educ_especial, responsavel_beneficiario_auxilio_gov, " +
                "id_responsavel, id_tipo_auxilio, id_classificacao_especial, status_classificacao_especial" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getFoto());
            stmt.setDate(3, java.sql.Date.valueOf(c.getDataNascimento()));
            stmt.setString(4, c.getSexo().name());
            stmt.setString(5, c.getCorRaca() != null ? c.getCorRaca().name() : null);
            stmt.setBoolean(6, c.isPossuiIrmaoCreche());
            stmt.setBoolean(7, c.isPossuiIrmaoGemeo());
            stmt.setString(8, c.getNumeroSus());
            stmt.setString(9, c.getUnidadeSaude());
            stmt.setString(10, c.getMunicipioNascimento());
            stmt.setString(11, c.getCartorioRegistro());
            stmt.setString(12, c.getCertidaoNascimentoNum());
            stmt.setDate(13, c.getDataEmissaoCertidao() != null ? java.sql.Date.valueOf(c.getDataEmissaoCertidao()) : null);
            stmt.setString(14, c.getOrgEmissorCertidao());
            stmt.setBoolean(15, c.isRestricaoAlimentar());
            stmt.setString(16, c.getDescricaoRestricoesAlimentares());
            stmt.setBoolean(17, c.isAlergia());
            stmt.setString(18, c.getProblemaSaude());
            stmt.setString(19, c.getMobilidadeReduzida() != null ? c.getMobilidadeReduzida().name() : null);
            stmt.setBoolean(20, c.isDefMulti());
            stmt.setBoolean(21, c.isEducEspecial());
            stmt.setBoolean(22, c.isResponsavelBeneficiarioAuxilioGov());
            stmt.setInt(23, c.getIdResponsavel());
            stmt.setInt(24, c.getIdTipoAuxilio());
            stmt.setInt(25, c.getIdClassificacaoEspecial());
            stmt.setBoolean(26, c.isStatusClassificacaoEspecial());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1); // Retorna ID gerado
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar criança: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Busca dinâmica com todos os filtros possíveis
    public List<Crianca> buscarPorFiltros(
            Long id, String nome, LocalDate dataNascimento, Sexo sexo, CorRaca corRaca,
            Boolean possuiIrmaoCreche, Boolean possuiIrmaoGemeo,
            String numeroSus, String unidadeSaude, String municipioNascimento,
            String cartorioRegistro, String certidaoNum, MobilidadeReduzida mobilidade
    ) {
        List<Crianca> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM crianca WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (id != null) {
            sql.append(" AND id_crianca = ?");
            parametros.add(id);
        }
        if (nome != null && !nome.isEmpty()) {
            sql.append(" AND nome LIKE ?");
            parametros.add("%" + nome + "%");
        }
        if (dataNascimento != null) {
            sql.append(" AND data_nascimento = ?");
            parametros.add(java.sql.Date.valueOf(dataNascimento));
        }
        if (sexo != null) {
            sql.append(" AND sexo = ?");
            parametros.add(sexo.name());
        }
        if (corRaca != null) {
            sql.append(" AND cor_raca = ?");
            parametros.add(corRaca.name());
        }
        if (possuiIrmaoCreche != null) {
            sql.append(" AND possui_irmao_creche = ?");
            parametros.add(possuiIrmaoCreche);
        }
        if (possuiIrmaoGemeo != null) {
            sql.append(" AND possui_irmao_gemeo = ?");
            parametros.add(possuiIrmaoGemeo);
        }
        if (numeroSus != null && !numeroSus.isEmpty()) {
            sql.append(" AND cartsus = ?");
            parametros.add(numeroSus);
        }
        if (unidadeSaude != null && !unidadeSaude.isEmpty()) {
            sql.append(" AND unidade_saude = ?");
            parametros.add(unidadeSaude);
        }
        if (municipioNascimento != null && !municipioNascimento.isEmpty()) {
            sql.append(" AND municipio_nascimento = ?");
            parametros.add(municipioNascimento);
        }
        if (cartorioRegistro != null && !cartorioRegistro.isEmpty()) {
            sql.append(" AND cartorio_registro = ?");
            parametros.add(cartorioRegistro);
        }
        if (certidaoNum != null && !certidaoNum.isEmpty()) {
            sql.append(" AND certidao_nascimento_num = ?");
            parametros.add(certidaoNum);
        }
        if (mobilidade != null) {
            sql.append(" AND mob_red = ?");
            parametros.add(mobilidade.name());
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Crianca c = mapResultSetParaCrianca(rs);
                    lista.add(c);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar crianças com filtros: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    private Crianca mapResultSetParaCrianca(ResultSet rs) throws SQLException {
        Crianca c = new Crianca();
        c.setIdCrianca(rs.getLong("id_crianca"));
        c.setNome(rs.getString("nome"));
        c.setFoto(rs.getString("foto"));
        c.setDataNascimento(rs.getDate("data_nascimento") != null ? rs.getDate("data_nascimento").toLocalDate() : null);
        c.setDataEmissaoCertidao(rs.getDate("data_emissao_certidao") != null ? rs.getDate("data_emissao_certidao").toLocalDate() : null);
        c.setSexo(Sexo.valueOf(rs.getString("sexo")));
        String cor = rs.getString("cor_raca");
        if (cor != null) c.setCorRaca(CorRaca.valueOf(cor));
        String mob = rs.getString("mob_red");
        if (mob != null) c.setMobilidadeReduzida(MobilidadeReduzida.valueOf(mob));

        c.setPossuiIrmaoCreche(rs.getBoolean("possui_irmao_creche"));
        c.setPossuiIrmaoGemeo(rs.getBoolean("possui_irmao_gemeo"));
        c.setRestricaoAlimentar(rs.getBoolean("restricao_alimentar"));
        c.setDefMulti(rs.getBoolean("def_multi"));
        c.setEducEspecial(rs.getBoolean("educ_especial"));
        c.setResponsavelBeneficiarioAuxilioGov(rs.getBoolean("responsavel_beneficiario_auxilio_gov"));

        c.setNumeroSus(rs.getString("cartsus"));
        c.setUnidadeSaude(rs.getString("unidade_saude"));
        c.setMunicipioNascimento(rs.getString("municipio_nascimento"));
        c.setCartorioRegistro(rs.getString("cartorio_registro"));
        c.setCertidaoNascimentoNum(rs.getString("certidao_nascimento_num"));
        c.setOrgEmissorCertidao(rs.getString("org_emissor_certidao"));
        c.setDescricaoRestricoesAlimentares(rs.getString("descricao_restricoes_alimentares"));
        c.setProblemaSaude(rs.getString("problema_saude"));

        c.setIdTipoAuxilio(rs.getInt("id_tipo_auxilio"));
        c.setIdResponsavel(rs.getInt("id_responsavel"));
        c.setIdClassificacaoEspecial(rs.getInt("id_classificacao_especial"));
        c.setStatusClassificacaoEspecial(rs.getBoolean("status_classificacao_especial"));

        return c;
    }
}
