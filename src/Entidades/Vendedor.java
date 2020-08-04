package Entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable{
    
    private Integer id;
    private String nome;
    private String email;
    private Date aniversário;
    private Double salárioBase;
    
    private Departamento departamento;
        
    public Vendedor(){
    }

    public Vendedor(Integer id, String nome, String email, Date aniversário, Double salárioBase, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.aniversário = aniversário;
        this.salárioBase = salárioBase;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAniversário() {
        return aniversário;
    }

    public void setAniversário(Date aniversário) {
        this.aniversário = aniversário;
    }

    public Double getSalárioBase() {
        return salárioBase;
    }

    public void setSalárioBase(Double salárioBase) {
        this.salárioBase = salárioBase;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vendedor other = (Vendedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", anivers\u00e1rio=" + aniversário + ", sal\u00e1rioBase=" + salárioBase + ", departamento=" + departamento + '}';
    }

   
    
    
    
    
}
