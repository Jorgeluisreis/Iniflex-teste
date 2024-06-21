package main.java.com.iniflex.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa{
    private BigDecimal Salario;
    private String Funcao;

    public Funcionario(String Nome, LocalDate DataNascimento, BigDecimal salario, String funcao) {
        super(Nome, DataNascimento);
        this.Salario = salario;
        this.Funcao = funcao;
    }

    public BigDecimal getSalario() {
        return Salario;
    }

    public void setSalario(BigDecimal salario) {
        this.Salario = salario;
    }

    public String getFuncao() {
        return Funcao;
    }

    public void setFuncao(String funcao) {
        this.Funcao = funcao;
    }

    public String getInfo() {
        String dataFormato = getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyy"));
        @SuppressWarnings("deprecation")
        Locale brasil = new Locale("pt", "BR");
        NumberFormat format = NumberFormat.getCurrencyInstance(brasil);
        String salarioFormatado = format.format(getSalario());

        StringBuilder info = new StringBuilder();
        info.append("Nome: ").append(getNome()).append("\n");
        info.append("Data de Nascimento: ").append(dataFormato).append("\n");
        info.append("Salário: ").append(salarioFormatado).append("\n");
        info.append("Função: ").append(getFuncao()).append("\n");

        return info.toString();
    }
}