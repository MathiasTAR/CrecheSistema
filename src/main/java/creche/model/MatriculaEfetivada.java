package creche.model;

import java.time.LocalDate;

public class MatriculaEfetivada {
    private Long id;
    private Long idCrianca;
    private Long idPreMatricula;
    private LocalDate dataMatricula;
    private String serie;
    private int anoLetivo;
    private boolean orientRecebida;
    private LocalDate dataDesligamento;
    private SituacaoMatricula situacao;

    // === Getters e Setters ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdCrianca() { return idCrianca; }
    public void setIdCrianca(Long idCrianca) { this.idCrianca = idCrianca; }

    public Long getIdPreMatricula() { return idPreMatricula; }
    public void setIdPreMatricula(Long idPreMatricula) { this.idPreMatricula = idPreMatricula; }

    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public int getAnoLetivo() { return anoLetivo; }
    public void setAnoLetivo(int anoLetivo) { this.anoLetivo = anoLetivo; }

    public boolean isOrientRecebida() { return orientRecebida; }
    public void setOrientRecebida(boolean orientRecebida) { this.orientRecebida = orientRecebida; }

    public LocalDate getDataDesligamento() { return dataDesligamento; }
    public void setDataDesligamento(LocalDate dataDesligamento) { this.dataDesligamento = dataDesligamento; }

    public SituacaoMatricula getSituacao() { return situacao; }
    public void setSituacao(SituacaoMatricula situacao) { this.situacao = situacao; }
}
