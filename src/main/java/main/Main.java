package main;

import dao.AlunoDAO;
import dao.DaoUtil;
import modelo.Aluno;

import java.math.BigDecimal;
import java.util.Scanner;


public class Main {

    private static final AlunoDAO alunoDAO = DaoUtil.getAlunoDAO();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            menu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> cadastrar(scanner);
                case 2 -> excluir(scanner);
                case 3 -> editar(scanner);
                case 4 -> consultar(scanner);
                case 5 -> exibir();
                case 6 -> System.out.println("Fim do programa.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
        scanner.close();
    }

    private static void menu() {
        System.out.println("------ MENU ------");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Excluir Aluno");
        System.out.println("3. Alterar Aluno");
        System.out.println("4. Buscar aluno pelo nome");
        System.out.println("5. Listar alunos (com status de aprovação)");
        System.out.println("6. Fim do programa");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrar(Scanner scanner) {
        System.out.println("\nCadastrar Aluno\n");
        Aluno novoAluno = criarAluno(scanner);
        System.out.println(novoAluno);
        alunoDAO.cadastrar(novoAluno);
        System.out.println("\nAluno cadastrado com sucesso!\n");
    }

    private static Aluno criarAluno(Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o RA do aluno: ");
        String ra = scanner.nextLine();

        System.out.print("Digite o email do aluno: ");
        String email = scanner.nextLine();

        System.out.print("Digite a nota 1 do aluno: ");
        BigDecimal nota1 = scanner.nextBigDecimal();

        System.out.print("Digite a nota 2 do aluno: ");
        BigDecimal nota2 = scanner.nextBigDecimal();

        System.out.print("Digite a nota 3 do aluno: ");
        BigDecimal nota3 = scanner.nextBigDecimal();

        scanner.nextLine();

        return new Aluno(nome, ra, email, nota1, nota2, nota3);
    }

    private static void excluir(Scanner scanner) {
        System.out.println("\nExcluir Aluno\n");
        System.out.print("Digite o nome do aluno a ser excluído: ");
        String nomeCadastro = scanner.nextLine();
        try {
            alunoDAO.excluir(nomeCadastro);
            System.out.println("\nAluno removido com sucesso!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void editar(Scanner scanner) {
        Aluno alunoAntigo;
        Aluno alunoNovo;
        System.out.println("\nAlterar Aluno\n");
        System.out.print("Digite o nome do aluno a ser alterado: ");
        String nomeAlterar = scanner.nextLine();
        try {
            alunoAntigo = alunoDAO.buscarAlunoPeloNome(nomeAlterar);
            alunoNovo = alterarAluno(scanner, alunoAntigo);
            alunoDAO.alterarAluno(alunoAntigo, alunoNovo);
            System.out.println("\nAluno alterado com sucesso!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Aluno alterarAluno(Scanner scanner, Aluno aluno) {
        System.out.print("Digite o novo nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o novo RA do aluno: ");
        String ra = scanner.nextLine();

        System.out.print("Digite o novo email do aluno: ");
        String email = scanner.nextLine();

        System.out.print("Digite a nova nota 1 do aluno: ");
        BigDecimal nota1 = scanner.nextBigDecimal();

        System.out.print("Digite a nova nota 2 do aluno: ");
        BigDecimal nota2 = scanner.nextBigDecimal();

        System.out.print("Digite a nova nota 3 do aluno: ");
        BigDecimal nota3 = scanner.nextBigDecimal();

        scanner.nextLine();

        aluno.setNome(nome);
        aluno.setRa(ra);
        aluno.setEmail(email);
        aluno.setNota1(nota1);
        aluno.setNota2(nota2);
        aluno.setNota3(nota3);

        return aluno;
    }

    private static void consultar(Scanner scanner) {
        System.out.println("\nBuscar aluno pelo nome\n");
        System.out.print("Digite o nome do aluno: ");
        try {
            String nomeBuscar = scanner.nextLine();
            System.out.println(alunoDAO.buscarAlunoPeloNome(nomeBuscar));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exibir() {
        System.out.println("\nExibindo todos os alunos:\n");
        alunoDAO.listarTodos();
    }
}
