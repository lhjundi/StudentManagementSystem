package dao;

import jakarta.persistence.EntityManager;
import util.JPAUtil;


public class DaoUtil {

    private static final EntityManager entityManager = JPAUtil.getEntityManager();
    private static final AlunoDAO alunoDAO = new AlunoDAO(entityManager);

    public static AlunoDAO getAlunoDAO() {
        return alunoDAO;
    }

}
