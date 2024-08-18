package dao;

import jakarta.persistence.EntityManager;
import modelo.Aluno;

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
    public List<Aluno> listarAprovados(){
        List<Aluno> alunos = new ArrayList<>();
        String jpql = "SELECT a FROM Aluno a";
        alunos = this.entityManager.createQuery(jpql, Aluno.class).getResultList();
        alunos = alunos.stream().filter(aluno -> aluno.calculaMedia() >= 6).toList();
        return alunos;
    }

}
