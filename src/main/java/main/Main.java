package main;

import dao.AlunoDAO;
import jakarta.persistence.EntityManager;
import modelo.Aluno;
import util.JPAUtil;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Level;


public class Main {

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        EntityManager entityManager = JPAUtil.getEntityManager();
        AlunoDAO alunoDAO = new AlunoDAO(entityManager);

        int opcao;
        do {
            main.menu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> main.cadastrar(scanner, alunoDAO);
                case 2 -> main.excluir(scanner, alunoDAO);
                case 3 -> main.editar(scanner, alunoDAO);
                case 4 -> main.consultar(scanner, alunoDAO);
                case 5 -> main.exibir(alunoDAO);
                case 6 -> System.out.println("Fim do programa.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
        scanner.close();
    }

    private void menu() {
        System.out.println("------ MENU ------");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Excluir Aluno");
        System.out.println("3. Alterar Aluno");
        System.out.println("4. Buscar aluno pelo nome");
        System.out.println("5. Listar alunos (com status de aprovação)");
        System.out.println("6. Fim do programa");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrar(Scanner scanner, AlunoDAO alunoDAO) {
        System.out.println("\nCadastrar Aluno\n");
        Aluno novoAluno = criarAluno(scanner);
        System.out.println(novoAluno);
        alunoDAO.cadastrar(novoAluno);
        System.out.println("\nAluno cadastrado com sucesso!\n");
    }

    private Aluno criarAluno(Scanner scanner) {
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

    private void excluir(Scanner scanner, AlunoDAO alunoDAO) {
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

    private void editar(Scanner scanner, AlunoDAO alunoDAO) {
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

    private Aluno alterarAluno(Scanner scanner, Aluno aluno) {
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

    private void consultar(Scanner scanner, AlunoDAO alunoDAO) {
        System.out.println("\nBuscar aluno pelo nome\n");
        System.out.print("Digite o nome do aluno: ");
        try {
            String nomeBuscar = scanner.nextLine();
            System.out.println(alunoDAO.buscarAlunoPeloNome(nomeBuscar));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void exibir(AlunoDAO alunoDAO) {
        System.out.println("\nExibindo todos os alunos:\n");
        alunoDAO.listarTodos();
    }
}
