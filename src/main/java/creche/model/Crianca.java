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
    private LocalDate dataEmissao;
    private String orgaoEmissor;
    private boolean restricaoAlimentar;
    private String restricoesAlimentares;
    private int idAlergia;
    private String problemaSaude;
    private MobilidadeReduzida mobilidadeReduzida;
    private boolean defMulti;
    private boolean educEspecial;
    private boolean responsavelBeneficiarioAuxilioGov;
    private String tipoAuxilio;
    private String numeroNIS;
    private String certidao;
    private String orgEmissor;

    // Getters e Setters
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

    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }

    public String getOrgaoEmissor() { return orgaoEmissor; }
    public void setOrgaoEmissor(String orgaoEmissor) { this.orgaoEmissor = orgaoEmissor; }

    public boolean isRestricaoAlimentar() { return restricaoAlimentar; }
    public void setRestricaoAlimentar(boolean restricaoAlimentar) { this.restricaoAlimentar = restricaoAlimentar; }

    public String getRestricoesAlimentares() { return restricoesAlimentares; }
    public void setRestricoesAlimentares(String restricoesAlimentares) { this.restricoesAlimentares = restricoesAlimentares; }

    public int getIdAlergia() { return idAlergia; }
    public void setIdAlergia(int idAlergia) { this.idAlergia = idAlergia; }

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

    public String getTipoAuxilio() { return tipoAuxilio; }
    public void setTipoAuxilio(String tipoAuxilio) { this.tipoAuxilio = tipoAuxilio; }

    public String getNumeroNIS() { return numeroNIS; }
    public void setNumeroNIS(String numeroNIS) { this.numeroNIS = numeroNIS; }

    public String getCertidao() { return certidao; }
    public void setCertidao(String certidao) { this.certidao = certidao; }

    public String getOrgEmissor() { return orgEmissor; }
    public void setOrgEmissor(String orgEmissor) { this.orgEmissor = orgEmissor; }
}
