package main.java.com.iniflex.model;

import java.time.LocalDate;

public class Pessoa {
    private String Nome;
    private LocalDate DataNascimento;

    public Pessoa (String nome, LocalDate datanascimento) {
        this.Nome = nome;
        this.DataNascimento = datanascimento;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public LocalDate getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDate datanascimento) {
        this.DataNascimento = datanascimento;
    }
  }
