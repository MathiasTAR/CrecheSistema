package creche.model;

import java.time.LocalDate;

public class Crianca extends Pessoa {

    private Long idCrianca;
    private String foto;
    private Sexo sexo;
    private CorRaca corRaca;
    private boolean possuiIrmaoCreche;
    private boolean possuiIrmaoGemeo;
    private String numeroSus; // cartsus
    private String unidadeSaude;
    private String municipioNascimento;
    private String cartorioRegistro;
    private String certidaoNascimentoNum;
    private LocalDate dataEmissaoCertidao;
    private String orgEmissorCertidao;
    private boolean restricaoAlimentar;
    private String descricaoRestricoesAlimentares;
    private boolean alergia;
    private String problemaSaude;
    private MobilidadeReduzida mobilidadeReduzida;
    private boolean defMulti;
    private boolean educEspecial;
    private boolean responsavelBeneficiarioAuxilioGov;
    private int idResponsavel;
    private int idTipoAuxilio;
    private int idClassificacaoEspecial;
    private boolean statusClassificacaoEspecial;

    // ===== Getters e Setters =====

    public Long getIdCrianca() { return idCrianca; }
    public void setIdCrianca(Long idCrianca) { this.idCrianca = idCrianca; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    public CorRaca getCorRaca() { return corRaca; }
    public void setCorRaca(CorRaca corRaca) { this.corRaca = corRaca; }

    public boolean isPossuiIrmaoCreche() { return possuiIrmaoCreche; }
    public void setPossuiIrmaoCreche(boolean possuiIrmaoCreche) { this.possuiIrmaoCreche = possuiIrmaoCreche; }

    public boolean isPossuiIrmaoGemeo() { return possuiIrmaoGemeo; }
    public void setPossuiIrmaoGemeo(boolean possuiIrmaoGemeo) { this.possuiIrmaoGemeo = possuiIrmaoGemeo; }

    public String getNumeroSus() { return numeroSus; }
    public void setNumeroSus(String numeroSus) { this.numeroSus = numeroSus; }

    public String getUnidadeSaude() { return unidadeSaude; }
    public void setUnidadeSaude(String unidadeSaude) { this.unidadeSaude = unidadeSaude; }

    public String getMunicipioNascimento() { return municipioNascimento; }
    public void setMunicipioNascimento(String municipioNascimento) { this.municipioNascimento = municipioNascimento; }

    public String getCartorioRegistro() { return cartorioRegistro; }
    public void setCartorioRegistro(String cartorioRegistro) { this.cartorioRegistro = cartorioRegistro; }

    public String getCertidaoNascimentoNum() { return certidaoNascimentoNum; }
    public void setCertidaoNascimentoNum(String certidaoNascimentoNum) { this.certidaoNascimentoNum = certidaoNascimentoNum; }

    public LocalDate getDataEmissaoCertidao() { return dataEmissaoCertidao; }
    public void setDataEmissaoCertidao(LocalDate dataEmissaoCertidao) { this.dataEmissaoCertidao = dataEmissaoCertidao; }

    public String getOrgEmissorCertidao() { return orgEmissorCertidao; }
    public void setOrgEmissorCertidao(String orgEmissorCertidao) { this.orgEmissorCertidao = orgEmissorCertidao; }

    public boolean isRestricaoAlimentar() { return restricaoAlimentar; }
    public void setRestricaoAlimentar(boolean restricaoAlimentar) { this.restricaoAlimentar = restricaoAlimentar; }

    public String getDescricaoRestricoesAlimentares() { return descricaoRestricoesAlimentares; }
    public void setDescricaoRestricoesAlimentares(String descricaoRestricoesAlimentares) { this.descricaoRestricoesAlimentares = descricaoRestricoesAlimentares; }

    public boolean isAlergia() { return alergia; }
    public void setAlergia(boolean alergia) { this.alergia = alergia; }

    public String getProblemaSaude() { return problemaSaude; }
    public void setProblemaSaude(String problemaSaude) { this.problemaSaude = problemaSaude; }

    public MobilidadeReduzida getMobilidadeReduzida() { return mobilidadeReduzida; }
    public void setMobilidadeReduzida(MobilidadeReduzida mobilidadeReduzida) { this.mobilidadeReduzida = mobilidadeReduzida; }

    public boolean isDefMulti() { return defMulti; }
    public void setDefMulti(boolean defMulti) { this.defMulti = defMulti; }

    public boolean isEducEspecial() { return educEspecial; }
    public void setEducEspecial(boolean educEspecial) { this.educEspecial = educEspecial; }

    public boolean isResponsavelBeneficiarioAuxilioGov() { return responsavelBeneficiarioAuxilioGov; }
    public void setResponsavelBeneficiarioAuxilioGov(boolean responsavelBeneficiarioAuxilioGov) { this.responsavelBeneficiarioAuxilioGov = responsavelBeneficiarioAuxilioGov; }

    public int getIdResponsavel() { return idResponsavel; }
    public void setIdResponsavel(int idResponsavel) { this.idResponsavel = idResponsavel; }

    public int getIdTipoAuxilio() { return idTipoAuxilio; }
    public void setIdTipoAuxilio(int idTipoAuxilio) { this.idTipoAuxilio = idTipoAuxilio; }

    public int getIdClassificacaoEspecial() { return idClassificacaoEspecial; }
    public void setIdClassificacaoEspecial(int idClassificacaoEspecial) { this.idClassificacaoEspecial = idClassificacaoEspecial; }

    public boolean isStatusClassificacaoEspecial() { return statusClassificacaoEspecial; }
    public void setStatusClassificacaoEspecial(boolean statusClassificacaoEspecial) { this.statusClassificacaoEspecial = statusClassificacaoEspecial; }
}
