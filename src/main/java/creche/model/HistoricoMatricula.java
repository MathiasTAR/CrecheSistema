package creche.model;

import java.time.LocalDateTime;

public class HistoricoMatricula {
    private Long id;
    private Long idMatricula;
    private LocalDateTime dataEvento;
    private String tipoEvento;
    private String descricao;
    private String usuarioRegistro;
    private String observacao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdMatricula() { return idMatricula; }
    public void setIdMatricula(Long idMatricula) { this.idMatricula = idMatricula; }

    public LocalDateTime getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDateTime dataEvento) { this.dataEvento = dataEvento; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public  String getDescricao() {return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getUsuarioRegistro() {return usuarioRegistro; }
    public void setUsuarioRegistro(String usuarioRegistro) {this.usuarioRegistro = usuarioRegistro; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
