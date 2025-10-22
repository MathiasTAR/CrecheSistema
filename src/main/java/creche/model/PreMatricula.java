package creche.model;

import java.time.LocalDate;

public class PreMatricula {
    private Long id;
    private Long idCrianca;
    private LocalDate dataPreMatricula;
    private String status;
    private String numeroProtocolo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdCrianca() { return idCrianca; }
    public void setIdCrianca(Long idCrianca) { this.idCrianca = idCrianca; }
    public LocalDate getDataPreMatricula() { return dataPreMatricula; }
    public void setDataPreMatricula(LocalDate dataPreMatricula) { this.dataPreMatricula = dataPreMatricula; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNumeroProtocolo() { return numeroProtocolo; }
    public void setNumeroProtocolo(String numeroProtocolo) { this.numeroProtocolo = numeroProtocolo; }
}
