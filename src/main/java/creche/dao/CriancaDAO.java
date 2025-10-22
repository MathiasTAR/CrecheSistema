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

public class CriancaDAO extends GenericDAO {

    public Crianca buscarPorId(Long id) {
        String sql = "SELECT * FROM crianca WHERE id_crianca = ?";
        Crianca c = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Crianca();
                    c.setIdCrianca(rs.getLong("id_crianca"));
                    c.setNome(rs.getString("nome"));
                    c.setFoto(rs.getString("foto"));

                    // Datas
                    if (rs.getDate("data_nascimento") != null)
                        c.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());

                    if (rs.getDate("data_emissao_certidao") != null)
                        c.setDataEmissaoCertidao(rs.getDate("data_emissao_certidao").toLocalDate());

                    // Enums
                    c.setSexo(Sexo.valueOf(rs.getString("sexo")));
                    String cor = rs.getString("cor_raca");
                    if (cor != null) c.setCorRaca(CorRaca.valueOf(cor));

                    String mob = rs.getString("mob_red");
                    if (mob != null) c.setMobilidadeReduzida(MobilidadeReduzida.valueOf(mob));

                    // Booleanos
                    c.setPossuiIrmaoCreche(rs.getBoolean("possui_irmao_creche"));
                    c.setPossuiIrmaoGemeo(rs.getBoolean("possui_irmao_gemeo"));
                    c.setRestricaoAlimentar(rs.getBoolean("restricao_alimentar"));
                    c.setDefMulti(rs.getBoolean("def_multi"));
                    c.setEducEspecial(rs.getBoolean("educ_especial"));
                    c.setResponsavelBeneficiarioAuxilioGov(rs.getBoolean("responsavel_beneficiario_auxilio_gov"));

                    // Strings
                    c.setNumeroSus(rs.getString("cartsus"));
                    c.setUnidadeSaude(rs.getString("unidade_saude"));
                    c.setMunicipioNascimento(rs.getString("municipio_nascimento"));
                    c.setCartorioRegistro(rs.getString("cartorio_registro"));
                    c.setCertidaoNascimentoNum(rs.getString("certidao_nascimento_num"));
                    c.setOrgEmissorCertidao(rs.getString("org_emissor_certidao"));
                    c.setDescricaoRestricoesAlimentares(rs.getString("descricao_restricoes_alimentares"));
                    c.setProblemaSaude(rs.getString("problema_saude"));

                    // Inteiros / FKs
                    c.setIdTipoAuxilio(rs.getInt("id_tipo_auxilio"));
                    c.setIdResponsavel(rs.getInt("id_responsavel"));
                    c.setIdClassificacaoEspecial(rs.getInt("id_classificacao_especial"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar criança por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return c;
    }
}
