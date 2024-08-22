package dao;

import jakarta.persistence.EntityManager;
import modelo.Aluno;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private EntityManager entityManager;

    public AlunoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Aluno aluno){
        this.entityManager.persist(aluno);
    }

    public void excluir(Aluno aluno){
        this.entityManager.remove(aluno);
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

    public List<Aluno> listarAprovados(){
        List<Aluno> alunos = new ArrayList<>();
        String jpql = "SELECT a FROM Aluno a";
        alunos = this.entityManager.createQuery(jpql, Aluno.class).getResultList();
        alunos = alunos.stream().filter(aluno -> aluno.calculaMedia() >= 6).toList();
        return alunos;
    }

}
