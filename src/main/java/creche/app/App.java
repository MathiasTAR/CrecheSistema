package creche.app;

import creche.dao.CriancaDAO;
import creche.dao.MatriculaDAO;
import creche.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CriancaDAO criancaDAO = new CriancaDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();

        System.out.println("=== SISTEMA DE CRECHE ===");

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n1 - Cadastrar Criança");
            System.out.println("2 - Buscar Criança");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");
            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarCriança(sc, criancaDAO, matriculaDAO);
                    break;
                case 2:
                    buscarCriança(sc, criancaDAO);
                    break;
                case 3:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }

    private static void cadastrarCriança(Scanner sc, CriancaDAO criancaDAO, MatriculaDAO matriculaDAO) {
        try {
            Crianca c = new Crianca();
            System.out.println("\n=== CADASTRO DE CRIANÇA ===");

            System.out.print("Nome: "); c.setNome(sc.nextLine());
            System.out.print("Foto URL: "); c.setFoto(sc.nextLine());
            System.out.print("Data Nascimento (yyyy-MM-dd): "); c.setDataNascimento(LocalDate.parse(sc.nextLine()));

            System.out.print("Sexo (MASCULINO/FEMININO/OUTRO): ");
            c.setSexo(Sexo.valueOf(sc.nextLine().toUpperCase()));

            System.out.print("Cor/Raça (BRANCA/PRETA/PARDA/AMARELA/INDIGENA): ");
            c.setCorRaca(CorRaca.valueOf(sc.nextLine().toUpperCase()));

            System.out.print("Possui irmão na creche (true/false): "); c.setPossuiIrmaoCreche(Boolean.parseBoolean(sc.nextLine()));
            System.out.print("Possui irmão gêmeo (true/false): "); c.setPossuiIrmaoGemeo(Boolean.parseBoolean(sc.nextLine()));

            System.out.print("Número SUS: "); c.setNumeroSus(sc.nextLine());
            System.out.print("Unidade de Saúde: "); c.setUnidadeSaude(sc.nextLine());
            System.out.print("Município Nascimento: "); c.setMunicipioNascimento(sc.nextLine());
            System.out.print("Cartório Registro: "); c.setCartorioRegistro(sc.nextLine());
            System.out.print("Certidão Nascimento Número: "); c.setCertidaoNascimentoNum(sc.nextLine());
            System.out.print("Data Emissão Certidão (yyyy-MM-dd): "); c.setDataEmissaoCertidao(LocalDate.parse(sc.nextLine()));
            System.out.print("Orgão Emissor Certidão: "); c.setOrgEmissorCertidao(sc.nextLine());
            System.out.print("Restrição Alimentar (true/false): "); c.setRestricaoAlimentar(Boolean.parseBoolean(sc.nextLine()));
            System.out.print("Descrição Restrições Alimentares: "); c.setDescricaoRestricoesAlimentares(sc.nextLine());
            System.out.print("Alergia (true/false): "); c.setAlergia(Boolean.parseBoolean(sc.nextLine()));
            System.out.print("Problema de Saúde: "); c.setProblemaSaude(sc.nextLine());

            System.out.print("Mobilidade Reduzida (TEMPORARIA/PERMANENTE/NENHUMA): ");
            c.setMobilidadeReduzida(MobilidadeReduzida.valueOf(sc.nextLine().toUpperCase()));

            System.out.print("Deficiência Múltipla (true/false): "); c.setDefMulti(Boolean.parseBoolean(sc.nextLine()));
            System.out.print("Educação Especial (true/false): "); c.setEducEspecial(Boolean.parseBoolean(sc.nextLine()));
            System.out.print("Responsável Beneficiário (true/false): "); c.setResponsavelBeneficiarioAuxilioGov(Boolean.parseBoolean(sc.nextLine()));

            System.out.print("ID Responsável: "); c.setIdResponsavel(Integer.parseInt(sc.nextLine()));
            System.out.print("ID Tipo Auxílio: "); c.setIdTipoAuxilio(Integer.parseInt(sc.nextLine()));
            System.out.print("ID Classificação Especial: "); c.setIdClassificacaoEspecial(Integer.parseInt(sc.nextLine()));
            System.out.print("Status Classificação Especial (true/false): "); c.setStatusClassificacaoEspecial(Boolean.parseBoolean(sc.nextLine()));

            Long idCrianca = criancaDAO.cadastrarCrianca(c);
            if (idCrianca != null) {
                System.out.println("Criança cadastrada com sucesso! ID: " + idCrianca);

                // Cadastro automático de matrícula
                System.out.println("\n=== CADASTRO DE MATRÍCULA ===");
                MatriculaEfetivada m = new MatriculaEfetivada();
                m.setIdCrianca(idCrianca);
                m.setIdPreMatricula(0L); // preencher conforme necessário
                m.setDataMatricula(LocalDate.now());
                m.setSerie("INFANTIL");
                m.setAnoLetivo(LocalDate.now().getYear());
                m.setOrientRecebida(true);
                m.setSituacao(SituacaoMatricula.ATIVA);

                Long idMatricula = matriculaDAO.cadastrarMatricula(m);
                System.out.println("Matrícula cadastrada com sucesso! ID: " + idMatricula);

            } else {
                System.out.println("Erro ao cadastrar criança.");
            }
        } catch (Exception e) {
            System.err.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private static void buscarCriança(Scanner sc, CriancaDAO criancaDAO) {
        System.out.println("\n=== BUSCA DE CRIANÇAS ===");
        try {
            System.out.print("ID (vazio para ignorar): ");
            String idStr = sc.nextLine();
            Long id = idStr.isEmpty() ? null : Long.parseLong(idStr);

            System.out.print("Nome: "); String nome = sc.nextLine();
            if (nome.isEmpty()) nome = null;

            System.out.print("Data Nascimento (yyyy-MM-dd ou vazio): ");
            String dataStr = sc.nextLine();
            LocalDate dataNascimento = dataStr.isEmpty() ? null : LocalDate.parse(dataStr);

            System.out.print("Sexo (MASCULINO/FEMININO/OUTRO ou vazio): ");
            String sexoStr = sc.nextLine();
            Sexo sexo = sexoStr.isEmpty() ? null : Sexo.valueOf(sexoStr.toUpperCase());

            System.out.print("Cor/Raça (BRANCA/PRETA/PARDA/AMARELA/INDIGENA ou vazio): ");
            String corStr = sc.nextLine();
            CorRaca cor = corStr.isEmpty() ? null : CorRaca.valueOf(corStr.toUpperCase());

            System.out.print("Possui irmão na creche (true/false ou vazio): ");
            String irmaoStr = sc.nextLine();
            Boolean possuiIrmaoCreche = irmaoStr.isEmpty() ? null : Boolean.parseBoolean(irmaoStr);

            System.out.print("Possui irmão gêmeo (true/false ou vazio): ");
            String gemeoStr = sc.nextLine();
            Boolean possuiIrmaoGemeo = gemeoStr.isEmpty() ? null : Boolean.parseBoolean(gemeoStr);

            System.out.print("Número SUS: "); String numeroSus = sc.nextLine();
            if (numeroSus.isEmpty()) numeroSus = null;

            System.out.print("Unidade Saúde: "); String unidadeSaude = sc.nextLine();
            if (unidadeSaude.isEmpty()) unidadeSaude = null;

            System.out.print("Município Nascimento: "); String municipio = sc.nextLine();
            if (municipio.isEmpty()) municipio = null;

            System.out.print("Cartório Registro: "); String cartorio = sc.nextLine();
            if (cartorio.isEmpty()) cartorio = null;

            System.out.print("Certidão Número: "); String certidao = sc.nextLine();
            if (certidao.isEmpty()) certidao = null;

            System.out.print("Mobilidade Reduzida (TEMPORARIA/PERMANENTE/NENHUMA ou vazio): ");
            String mobStr = sc.nextLine();
            MobilidadeReduzida mobilidade = mobStr.isEmpty() ? null : MobilidadeReduzida.valueOf(mobStr.toUpperCase());

            List<Crianca> resultados = criancaDAO.buscarPorFiltros(id, nome, dataNascimento, sexo, cor,
                    possuiIrmaoCreche, possuiIrmaoGemeo, numeroSus, unidadeSaude, municipio, cartorio, certidao, mobilidade);

            if (resultados.isEmpty()) {
                System.out.println("Nenhuma criança encontrada.");
            } else {
                System.out.println("\n=== RESULTADO DA BUSCA ===");
                for (Crianca c : resultados) {
                    System.out.println("ID: " + c.getIdCrianca() +
                            " | Nome: " + c.getNome() +
                            " | Sexo: " + c.getSexo() +
                            " | Cor/Raça: " + c.getCorRaca() +
                            " | Possui Irmão Creche: " + c.isPossuiIrmaoCreche() +
                            " | Possui Irmão Gêmeo: " + c.isPossuiIrmaoGemeo() +
                            " | Número SUS: " + c.getNumeroSus() +
                            " | Unidade Saúde: " + c.getUnidadeSaude() +
                            " | Município Nascimento: " + c.getMunicipioNascimento() +
                            " | Cartório Registro: " + c.getCartorioRegistro() +
                            " | Certidão Número: " + c.getCertidaoNascimentoNum() +
                            " | Data Emissão: " + c.getDataEmissaoCertidao() +
                            " | Orgão Emissor: " + c.getOrgEmissorCertidao() +
                            " | Restrição Alimentar: " + c.isRestricaoAlimentar() +
                            " | Descrição Restrições: " + c.getDescricaoRestricoesAlimentares() +
                            " | Alergia: " + c.isAlergia() +
                            " | Problema Saúde: " + c.getProblemaSaude() +
                            " | Mobilidade: " + c.getMobilidadeReduzida() +
                            " | Def Multi: " + c.isDefMulti() +
                            " | Educação Especial: " + c.isEducEspecial() +
                            " | Responsável Beneficiário: " + c.isResponsavelBeneficiarioAuxilioGov() +
                            " | ID Responsável: " + c.getIdResponsavel() +
                            " | ID Tipo Auxílio: " + c.getIdTipoAuxilio() +
                            " | ID Classificação Especial: " + c.getIdClassificacaoEspecial() +
                            " | Status Classificação: " + c.isStatusClassificacaoEspecial()
                    );
                }
            }

        } catch (Exception e) {
            System.err.println("Erro na busca: " + e.getMessage());
        }
    }
}
