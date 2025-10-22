package creche.app;

import creche.dao.CriancaDAO;
import creche.dao.MatriculaDAO;
import creche.model.Crianca;
import creche.model.MatriculaEfetivada;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CriancaDAO criancaDAO = new CriancaDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();

        System.out.println("=== CONSULTA DE DADOS ===");
        System.out.print("Digite o ID da criança: ");
        Long id = sc.nextLong();

        // Buscar a criança
        Crianca c = criancaDAO.buscarPorId(id);
        if (c == null) {
            System.out.println("Nenhuma criança encontrada com esse ID.");
            return;
        }

        // Exibir dados da criança
        System.out.println("\n=== Dados da Criança ===");
        System.out.println("ID: " + c.getIdCrianca());
        System.out.println("Nome: " + c.getNome());
        System.out.println("Data de Nascimento: " + c.getDataNascimento());
        System.out.println("Sexo: " + c.getSexo());
        System.out.println("Cor/Raça: " + c.getCorRaca());
        System.out.println("Foto: " + c.getFoto());
        System.out.println("Possui irmão na creche: " + c.isPossuiIrmaoCreche());
        System.out.println("Possui irmão gêmeo: " + c.isPossuiIrmaoGemeo());
        System.out.println("Número SUS: " + c.getNumeroSus());
        System.out.println("Unidade de Saúde: " + c.getUnidadeSaude());
        System.out.println("Município de Nascimento: " + c.getMunicipioNascimento());
        System.out.println("Cartório de Registro: " + c.getCartorioRegistro());
        System.out.println("Certidão de Nascimento nº: " + c.getCertidaoNascimentoNum());
        System.out.println("Data de Emissão da Certidão: " + c.getDataEmissaoCertidao());
        System.out.println("Órgão Emissor da Certidão: " + c.getOrgEmissorCertidao());
        System.out.println("Restrição Alimentar: " + c.isRestricaoAlimentar());
        System.out.println("Descrição das Restrições Alimentares: " + c.getDescricaoRestricoesAlimentares());
        System.out.println("Alergia: " + c.isAlergia());
        System.out.println("Problemas de Saúde: " + c.getProblemaSaude());
        System.out.println("Mobilidade Reduzida: " + c.getMobilidadeReduzida());
        System.out.println("Deficiência Múltipla: " + c.isDefMulti());
        System.out.println("Educação Especial: " + c.isEducEspecial());
        System.out.println("Beneficiário Auxílio Governo: " + c.isResponsavelBeneficiarioAuxilioGov());
        System.out.println("ID Responsável: " + c.getIdResponsavel());
        System.out.println("ID Tipo Auxílio: " + c.getIdTipoAuxilio());
        System.out.println("ID Classificação Especial: " + c.getIdClassificacaoEspecial());
        System.out.println("Status Classificação Especial: " + c.isStatusClassificacaoEspecial());

        // Buscar matrícula
        MatriculaEfetivada m = matriculaDAO.buscarPorIdCrianca(c.getIdCrianca());
        if (m != null) {
            System.out.println("\n=== Dados da Matrícula ===");
            System.out.println("ID Matrícula: " + m.getId());
            System.out.println("ID Pré-Matrícula: " + m.getIdPreMatricula());
            System.out.println("Data Matrícula: " + m.getDataMatricula());
            System.out.println("Série: " + m.getSerie());
            System.out.println("Ano Letivo: " + m.getAnoLetivo());
            System.out.println("Orientação Recebida: " + m.isOrientRecebida());
            System.out.println("Data Desligamento: " + m.getDataDesligamento());
            System.out.println("Situação: " + m.getSituacao());
        } else {
            System.out.println("\nEssa criança ainda não tem matrícula ativa.");
        }

        sc.close();
    }
}
