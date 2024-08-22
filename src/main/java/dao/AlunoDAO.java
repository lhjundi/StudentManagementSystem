package dao;

import jakarta.persistence.EntityManager;
import modelo.Aluno;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final EntityManager entityManager;

    public AlunoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Aluno aluno){
        this.entityManager.persist(aluno);
    }

    public void excluir(String nome){
        Aluno aluno;
        aluno = buscarAlunoPeloNome(nome);
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(aluno);
        this.entityManager.getTransaction().commit();
    }

    public void alterarAluno(Aluno aluno, Aluno alunoEditado){
        aluno.setNome(alunoEditado.getNome());
        aluno.setRa(alunoEditado.getRa());
        aluno.setEmail(alunoEditado.getEmail());
        aluno.setNota1(alunoEditado.getNota1());
        aluno.setNota2(alunoEditado.getNota2());
        aluno.setNota3(alunoEditado.getNota3());
        entityManager.merge(aluno);
    }

    public Aluno buscarAlunoPeloNome(String nome){
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :nome";
        return entityManager.createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }


    public void listarTodos(){
        System.out.println("Exibindo todos os alunos:");
        List<Aluno> alunos;
        String jpql = "SELECT a FROM Aluno a";
        alunos = this.entityManager.createQuery(jpql, Aluno.class).getResultList();

        for (Aluno aluno : alunos) {
            double media = aluno.calculaMedia();
            String situacao = aluno.defineStatus();

            System.out.println("-----------------------------");
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Email: " + aluno.getEmail());
            System.out.println("RA: " + aluno.getRa());
            System.out.println("Notas: " + aluno.getNota1() + " - " + aluno.getNota2() + " - " + aluno.getNota3());
            System.out.println("Média: " + media);
            System.out.println("Situação: " + situacao);
            System.out.println("-----------------------------");
        }
    }
    }

