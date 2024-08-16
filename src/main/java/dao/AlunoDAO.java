package dao;

import jakarta.persistence.EntityManager;
import modelo.Aluno;

public class AlunoDAO {
    private EntityManager entityManager;

    public AlunoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Aluno aluno){
        this.entityManager.persist(aluno);
    }

}
