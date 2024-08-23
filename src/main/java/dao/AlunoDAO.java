package dao;

import exception.AlunoNaoEncontradoException;
import jakarta.persistence.EntityManager;
import modelo.Aluno;

import java.util.List;

public class AlunoDAO {
    private final EntityManager entityManager;

    public AlunoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Aluno aluno) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(aluno);
        this.entityManager.getTransaction().commit();
    }

    public void excluir(String nome) {
        Aluno aluno;
        aluno = buscarAlunoPeloNome(nome);
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(aluno);
        this.entityManager.getTransaction().commit();
    }

    public void alterarAluno(Aluno aluno, Aluno alunoEditado) {
        aluno.setNome(alunoEditado.getNome());
        aluno.setRa(alunoEditado.getRa());
        aluno.setEmail(alunoEditado.getEmail());
        aluno.setNota1(alunoEditado.getNota1());
        aluno.setNota2(alunoEditado.getNota2());
        aluno.setNota3(alunoEditado.getNota3());
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(aluno);
        this.entityManager.getTransaction().commit();
    }

    public Aluno buscarAlunoPeloNome(String nome) {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :nome";
        return entityManager.createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getResultList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado!"));
    }


    public void listarTodos() {
        System.out.println("Exibindo todos os alunos:");
        List<Aluno> alunos;
        String jpql = "SELECT a FROM Aluno a";
        alunos = this.entityManager.createQuery(jpql, Aluno.class).getResultList();

        for (Aluno aluno : alunos) {
            System.out.println("-----------------------------");
            System.out.println(aluno);
            System.out.println("Média: " + aluno.calculaMedia());
            System.out.println("Situação: " + aluno.defineStatus());
            System.out.println("-----------------------------");
        }
    }
}
