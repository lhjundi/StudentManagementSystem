package main;

import dao.AlunoDAO;
import dao.DaoUtil;
import jakarta.persistence.EntityManager;
import modelo.Aluno;
import util.JPAUtil;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static final AlunoDAO alunoDAO = DaoUtil.getAlunoDAO();
    private static final EntityManager entityManager = JPAUtil.getEntityManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("------ MENU ------");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Excluir Aluno");
            System.out.println("3. Alterar Aluno");
            System.out.println("4. Buscar aluno pelo nome");
            System.out.println("5. Listar alunos (com status de aprovação)");
            System.out.println("6. Fim do programa");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consome a nova linha após a entrada do número

            switch (option) {
                case 1:
                    System.out.println("Cadastrar Aluno");
                    Aluno novoAluno = criarAluno(scanner);
                    alunoDAO.cadastrar(novoAluno);
                    break;
                case 2:
                    System.out.println("Excluir Aluno");
                    System.out.print("Digite o RA do aluno a ser excluído: ");
                    String raExcluir = scanner.nextLine();
                    alunoDAO.excluir(raExcluir);
                    break;
                case 3:
                    System.out.println("Alterar Aluno");
                    Aluno alunoAlterado = criarAluno(scanner);
                    alunoDAO.alterar(alunoAlterado);
                    break;
                case 4:
                    System.out.println("Buscar aluno pelo nome");
                    System.out.print("Digite o nome do aluno: ");
                    String nomeBuscar = scanner.nextLine();
                    alunoDAO.buscarPorNome(nomeBuscar);
                    break;
                case 5:
                    System.out.println("Listar alunos (com status de aprovação)");
                    alunoDAO.listarAlunos();
                    break;
                case 6:
                    System.out.println("Fim do programa.");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
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

        scanner.nextLine();  // Consome a nova linha

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setRa(ra);
        aluno.setEmail(email);
        aluno.setNota1(nota1);
        aluno.setNota2(nota2);
        aluno.setNota3(nota3);

        return aluno;
    }
}
