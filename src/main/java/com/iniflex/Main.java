package main.java.com.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import main.java.com.iniflex.model.Funcionario;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = criarListaDeFuncionarios();

        Locale locale = new Locale("pt", "BR");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        imprimirListaDeFuncionarios(funcionarios, "Lista de Funcionários:");

        aplicarAumentoSalarios(funcionarios, new BigDecimal("0.10"));
        imprimirListaDeFuncionarios(funcionarios, "Lista de Funcionários com salários atualizados:");

        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparFuncionariosPorFuncao(funcionarios);
        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        imprimirFuncionariosComAniversarioNoMes(funcionarios, 10, 12);

        Funcionario funcionarioMaisVelho = encontrarFuncionarioMaisVelho(funcionarios);
        imprimirFuncionarioMaisVelho(funcionarioMaisVelho);

        ordenarFuncionariosPorNome(funcionarios);
        imprimirListaDeFuncionarios(funcionarios, "Funcionários em ordem alfabética pelo nome:");

        imprimirSomaDosSalarios(funcionarios, locale);

        imprimirSalariosMinimosPorFuncionario(funcionarios, salarioMinimo, locale);
    }

    //3.1
    //Retorna uma lista do tipo Funcionáriom, passando seus dados específicos
    private static List<Funcionario> criarListaDeFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        //3.2
        funcionarios.remove(1); // Remove "João"
        return funcionarios;
    }

    //3.3
    //Imprime a lista de funcionários, iterando no método getInfo()
    private static void imprimirListaDeFuncionarios(List<Funcionario> funcionarios, String titulo) {
        System.out.println("------------------------------------------------------");
        System.out.println(titulo);
        System.out.println("------------------------------------------------------");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getInfo());
        }
    }

    //3.4
    //Aplica o aumento salarial a todos os funcionários e calcula novamente o salário
    private static void aplicarAumentoSalarios(List<Funcionario> funcionarios, BigDecimal percentual) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(percentual);
            BigDecimal novoSalario = salarioAtual.add(aumento);
            funcionario.setSalario(novoSalario);
        }
    }

    //3.5
    //usa o método collect da API Stream, tendo um mapa onde a chave é a função e o valor é a lista de funcionários
    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    //3.6
    // Agrupa os funcionários por função
    private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("------------------------------------------------------");
        System.out.println("Filtro por Função:");
        System.out.println("------------------------------------------------------");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            for (Funcionario funcionario : lista) {
                System.out.println("Funcionário: " + funcionario.getNome());
                System.out.println("Função: " + funcao);
                System.out.println();
            }
        });
    }

    //3.8
    // Imprime todos os funcionários que fazem aniversário nos meses 10 e 12
    private static void imprimirFuncionariosComAniversarioNoMes(List<Funcionario> funcionarios, int... meses) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Funcionários que fazem aniversário entre os meses 10 e 12:");
        System.out.println("----------------------------------------------------------");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            for (int mes : meses) {
                if (mesAniversario == mes) {
                    String dataFormato = funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    System.out.println("Funcionário: " + funcionario.getNome());
                    System.out.println("Data de Nascimento: " + dataFormato);
                    System.out.println();
                    break;
                }
            }
        }
    }

    //3.9
    // Usa o método min da API Collections para comparar os valores baseados na data de nascimento
    private static Funcionario encontrarFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        return Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
    }

    // Imrpime quem é o funcionári mais velho com o resultado da função anterior
    private static void imprimirFuncionarioMaisVelho(Funcionario funcionarioMaisVelho) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Funcionário mais velho:");
        System.out.println("----------------------------------------------------------");
        System.out.println("Nome: " + funcionarioMaisVelho.getNome());
        System.out.println("Data de Nascimento: " + funcionarioMaisVelho.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println();
    }

    //3.10
    //usa o método sort da API Collections usando o nome como comparador
    private static void ordenarFuncionariosPorNome(List<Funcionario> funcionarios) {
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
    }

    //3.11
    // Imprime a soma dos salários usando o método reduce da API Stream para somar e formatar a saída do resultado
    private static void imprimirSomaDosSalarios(List<Funcionario> funcionarios, Locale locale) {
        BigDecimal somaSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(locale);
        String somaSalariosFormatada = formatadorMoeda.format(somaSalarios);

        System.out.println("----------------------------------------------------------");
        System.out.println("Soma dos salários dos funcionários: " + somaSalariosFormatada);
        System.out.println("----------------------------------------------------------");
    }

    //3.12
    //Imprime a quantidade de salários mínimos que cada funcionário ganha
    private static void imprimirSalariosMinimosPorFuncionario(List<Funcionario> funcionarios, BigDecimal salarioMinimo, Locale locale) {
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(locale);
        System.out.println("----------------------------------------------------------");
        System.out.println("Quantidade de salários mínimos que cada funcionário ganha:");
        System.out.println("----------------------------------------------------------");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salario = funcionario.getSalario();
            BigDecimal salariosMinimos = salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            String salarioFormatado = formatadorMoeda.format(salario);
            String salariosMinimosFormatado = salariosMinimos.toString();

            System.out.println("Funcionário: " + funcionario.getNome());
            System.out.println("Salário: " + salarioFormatado);
            System.out.println("Salários mínimos: " + salariosMinimosFormatado);
            System.out.println();
        }
    }
}