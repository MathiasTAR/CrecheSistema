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

        while (true) {
            System.out.println("\n=== SISTEMA DE CRECHE ===");
            System.out.println("1 - Buscar Crianças");
            System.out.println("2 - Cadastrar Criança");
            System.out.println("3 - Cadastrar Matrícula");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> buscarCrianças(sc, criancaDAO);
                case 2 -> cadastrarCriança(sc, criancaDAO);
                case 3 -> cadastrarMatricula(sc, matriculaDAO);
                case 0 -> {
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void buscarCrianças(Scanner sc, CriancaDAO dao) {
        System.out.println("\n=== BUSCAR CRIANÇAS ===");

        System.out.print("ID (deixe em branco para não filtrar): ");
        String idStr = sc.nextLine();
        Long id = idStr.isEmpty() ? null : Long.parseLong(idStr);

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        if (nome.isEmpty()) nome = null;

        System.out.print("Data Nascimento (yyyy-MM-dd ou vazio): ");
        String dataStr = sc.nextLine();
        LocalDate data = dataStr.isEmpty() ? null : LocalDate.parse(dataStr);

        System.out.print("Sexo (MASCULINO/FEMININO/OUTRO ou vazio): ");
        String s = sc.nextLine();
        Sexo sexo = null;
        if (!s.isEmpty()) {
            try { sexo = Sexo.valueOf(s.toUpperCase()); } catch (Exception ignored) {}
        }

        System.out.print("Cor/Raça (BRANCA/PRETA/PARDA/AMARELA/INDIGENA ou vazio): ");
        String cor = sc.nextLine();
        CorRaca corRaca = null;
        if (!cor.isEmpty()) {
            try { corRaca = CorRaca.valueOf(cor.toUpperCase()); } catch (Exception ignored) {}
        }

        System.out.print("Possui irmão na creche? (S/N ou vazio): ");
        String irmaoStr = sc.nextLine();
        Boolean irmaoCreche = null;
        if (irmaoStr.equalsIgnoreCase("S")) irmaoCreche = true;
        else if (irmaoStr.equalsIgnoreCase("N")) irmaoCreche = false;

        System.out.print("Possui irmão gêmeo? (S/N ou vazio): ");
        String gemeoStr = sc.nextLine();
        Boolean irmaoGemeo = null;
        if (gemeoStr.equalsIgnoreCase("S")) irmaoGemeo = true;
        else if (gemeoStr.equalsIgnoreCase("N")) irmaoGemeo = false;

        List<Crianca> lista = dao.buscarPorFiltros(
                id, nome, data, sexo, corRaca, irmaoCreche, irmaoGemeo,
                null, null, null, null, null, null
        );

        if (lista.isEmpty()) {
            System.out.println("Nenhuma criança encontrada.");
        } else {
            System.out.println("\n=== RESULTADO ===");
            for (Crianca c : lista) {
                System.out.println("ID: " + c.getIdCrianca() + " | Nome: " + c.getNome() +
                        " | Nasc.: " + c.getDataNascimento() +
                        " | Sexo: " + c.getSexo() +
                        " | Cor/Raça: " + c.getCorRaca() +
                        " | Irmão na creche: " + c.isPossuiIrmaoCreche() +
                        " | Irmão gêmeo: " + c.isPossuiIrmaoGemeo() +
                        " | SUS: " + c.getNumeroSus());
            }
        }
    }

    private static void cadastrarCriança(Scanner sc, CriancaDAO dao) {
        System.out.println("\n=== CADASTRO DE CRIANÇA ===");
        Crianca c = new Crianca();

        System.out.print("Nome: ");
        c.setNome(sc.nextLine());

        System.out.print("Data Nascimento (yyyy-MM-dd): ");
        c.setDataNascimento(LocalDate.parse(sc.nextLine()));

        System.out.print("Sexo (MASCULINO/FEMININO/OUTRO): ");
        c.setSexo(Sexo.valueOf(sc.nextLine().toUpperCase()));

        System.out.print("Cor/Raça (BRANCA/PRETA/PARDA/AMARELA/INDIGENA ou vazio): ");
        String cor = sc.nextLine();
        if (!cor.isEmpty()) c.setCorRaca(CorRaca.valueOf(cor.toUpperCase()));

        System.out.print("Possui irmão na creche? (S/N): ");
        c.setPossuiIrmaoCreche(sc.nextLine().equalsIgnoreCase("S"));

        System.out.print("Possui irmão gêmeo? (S/N): ");
        c.setPossuiIrmaoGemeo(sc.nextLine().equalsIgnoreCase("S"));

        System.out.print("Número SUS: ");
        c.setNumeroSus(sc.nextLine());

        System.out.print("Unidade de Saúde: ");
        c.setUnidadeSaude(sc.nextLine());

        System.out.print("Município Nascimento: ");
        c.setMunicipioNascimento(sc.nextLine());

        System.out.print("Cartório Registro: ");
        c.setCartorioRegistro(sc.nextLine());

        System.out.print("Certidão Nascimento Nº: ");
        c.setCertidaoNascimentoNum(sc.nextLine());

        // Você pode adicionar os demais campos conforme desejar

        Long id = dao.cadastrarCrianca(c);
        System.out.println("Criança cadastrada com ID: " + id);
    }

    private static void cadastrarMatricula(Scanner sc, MatriculaDAO dao) {
        System.out.println("\n=== CADASTRO DE MATRÍCULA ===");
        MatriculaEfetivada m = new MatriculaEfetivada();

        System.out.print("ID Criança: ");
        m.setIdCrianca(sc.nextLong());

        System.out.print("ID Pré-Matrícula: ");
        m.setIdPreMatricula(sc.nextLong());

        System.out.print("Data Matrícula (yyyy-MM-dd): ");
        m.setDataMatricula(LocalDate.parse(sc.next()));

        System.out.print("Série: ");
        m.setSerie(sc.next());

        System.out.print("Ano Letivo: ");
        m.setAnoLetivo(sc.nextInt());

        System.out.print("Orientação Recebida? (S/N): ");
        m.setOrientRecebida(sc.next().equalsIgnoreCase("S"));

        m.setSituacao(SituacaoMatricula.ATIVA);

        Long idMatricula = dao.cadastrarMatricula(m);
        System.out.println("Matrícula cadastrada com ID: " + idMatricula);
    }
}
