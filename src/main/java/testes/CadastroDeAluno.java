package testes;

import dao.AlunoDAO;
import jakarta.persistence.EntityManager;
import modelo.Aluno;
import util.JPAUtil;

import java.math.BigDecimal;

public class CadastroDeAluno {
    public static void main(String[] args) {

        Aluno aluno1 = new Aluno(
                "Lucas Jundi","SC1000","lhjundi@outlook.com",
                BigDecimal.valueOf(8.0), BigDecimal.valueOf(9.0), BigDecimal.valueOf(10.0) );

        Aluno aluno2 = new Aluno(
                "Vitor Calicchio","SC1001","vitor@gmail.com",
                BigDecimal.valueOf(9.0), BigDecimal.valueOf(9.5), BigDecimal.valueOf(10.0) );

        Aluno aluno3 = new Aluno(
                "Joaquim Silva","SC1002","joaquim@live.com",
                BigDecimal.valueOf(4.0), BigDecimal.valueOf(4.5), BigDecimal.valueOf(3.5) );

        Aluno aluno4 = new Aluno(
                "Beatriz Souza","SC1003","beatriz@yahoo.com",
                BigDecimal.valueOf(3.0), BigDecimal.valueOf(6.5), BigDecimal.valueOf(2.0) );

        Aluno aluno5 = new Aluno(
                "Alex Bezerra","SC1004","alex@hotmail.com",
                BigDecimal.valueOf(6.0), BigDecimal.valueOf(5.5), BigDecimal.valueOf(6.5) );

        EntityManager entityManager = JPAUtil.getEntityManager();

        AlunoDAO dao = new AlunoDAO(entityManager);

        entityManager.getTransaction().begin();

        dao.cadastrar(aluno1);
        dao.cadastrar(aluno2);
        dao.cadastrar(aluno3);
        dao.cadastrar(aluno4);
        dao.cadastrar(aluno5);

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("Media do aluno 1");
        System.out.println(aluno1.calculaMedia());
        System.out.println(aluno1.defineStatus());

        System.out.println("Media do aluno 2");
        System.out.println(aluno2.calculaMedia());
        System.out.println(aluno2.defineStatus());

        System.out.println("Media do aluno 3");
        System.out.println(aluno3.calculaMedia());
        System.out.println(aluno3.defineStatus());

        System.out.println("Media do aluno 4");
        System.out.println(aluno4.calculaMedia());
        System.out.println(aluno4.defineStatus());

        System.out.println("Media do aluno 5");
        System.out.println(aluno5.calculaMedia());
        System.out.println(aluno5.defineStatus());
    }
}
