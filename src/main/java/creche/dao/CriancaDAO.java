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
                    LocalDate dataNasc = rs.getDate("data_nascimento") != null ? rs.getDate("data_nascimento").toLocalDate() : null;
                    c.setDataNascimento(dataNasc);

                    LocalDate dataEmissao = rs.getDate("data_emissao") != null ? rs.getDate("data_emissao").toLocalDate() : null;
                    c.setDataEmissao(dataEmissao);

                    // Enums
                    c.setSexo(Sexo.valueOf(rs.getString("sexo")));
                    String cor = rs.getString("cor_raca");
                    if (cor != null) c.setCorRaca(CorRaca.valueOf(cor));

                    String mob = rs.getString("mob_red");
                    if (mob != null) {c.setMobilidadeReduzida(MobilidadeReduzida.valueOf(mob));}

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
                    c.setOrgaoEmissor(rs.getString("orgao_emissor"));
                    c.setRestricoesAlimentares(rs.getString("restricoes_alimentares"));
                    c.setProblemaSaude(rs.getString("problema_saude"));
                    c.setTipoAuxilio(rs.getString("tipo_auxilio"));
                    c.setNumeroNIS(rs.getString("numero_nis"));
                    c.setCertidao(rs.getString("certidao"));
                    c.setOrgEmissor(rs.getString("org_emissor"));

                    // Inteiros
                    c.setIdAlergia(rs.getInt("id_alergia"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
}
