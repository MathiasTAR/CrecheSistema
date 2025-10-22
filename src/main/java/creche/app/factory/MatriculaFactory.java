package creche.app.factory;

import creche.model.PreMatricula;
import creche.model.MatriculaEfetivada;
import creche.model.HistoricoMatricula;
import creche.model.SituacaoMatricula;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatriculaFactory {

    public static MatriculaEfetivada efetivarMatricula(PreMatricula preMatricula) {
        MatriculaEfetivada matricula = new MatriculaEfetivada();
        matricula.setIdCrianca(preMatricula.getIdCrianca());
        matricula.setIdPreMatricula(preMatricula.getId());
        matricula.setDataMatricula(LocalDate.now());
        matricula.setSituacao(SituacaoMatricula.ATIVA);


        return matricula;
    }

    public static HistoricoMatricula gerarHistorico(MatriculaEfetivada matricula, String evento, String obs) {
        HistoricoMatricula h = new HistoricoMatricula();
        h.setIdMatricula(matricula.getId());
        h.setDataEvento(LocalDateTime.now());
        h.setTipoEvento(evento);
        h.setObservacao(obs);
        return h;
    }
}
