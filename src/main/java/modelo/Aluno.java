package modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;
    private String      nome;
    private String      ra;

    private String      email;

    private BigDecimal  nota1;
    private BigDecimal  nota2;
    private BigDecimal  nota3;
    public Aluno() {
    }

    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public double calculaMedia(){
        ArrayList<BigDecimal> notas = new ArrayList<>();
        notas.add(getNota1());
        notas.add(getNota2());
        notas.add(getNota3());
        return notas.stream()
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0);
    }

    public String defineStatus(){
        double media = calculaMedia();
        if ( media < 4 ) return "Reprovado";
        if ( media < 6 ) return "Recuperação";
        return "Aprovado";
    }

    @Override
    public String toString() {
        return "Dados do aluno: \n" +
                "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "RA: " + ra + "\n" +
                "Notas: " + nota1 + " - " + nota2 + " - " + nota3  + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }
}
