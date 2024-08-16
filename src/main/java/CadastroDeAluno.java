import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class CadastroDeAluno {
    public static void main(String[] args) {

        Aluno aluno1 = new Aluno();
        aluno1.setNome("Lucas Jundi");
        aluno1.setEmail("lhjundi@outlook.com");
        aluno1.setNota1(new BigDecimal("8.00"));
        aluno1.setNota2(new BigDecimal("9.00"));
        aluno1.setNota3(new BigDecimal("10"));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("alunos");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(aluno1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
