package service;

import model.Cliente;
import model.Conta;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AgenciaBancaria {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }
    public static void operacoes() {
        int operacao =
                Integer.parseInt(JOptionPane.showInputDialog("Selecione uma operação" +
                        "|   Opção 1 - Criar conta   |" +
                        "|   Opção 2 - Depositar     |" +
                        "|   Opção 3 - Sacar         |" +
                        "|   Opção 4 - Transferir    |" +
                        "|   Opção 5 - Listar        |" +
                        "|   Opção 6 - Sair          |"));

        switch (operacao) {
            case 1:
                criarConta();
                break;
            case 2:
                depositar();
                break;
            case 3:
                sacar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                listarContas();
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "Thank You");   // usa showMessage pra mostrar mensagem na tela
                System.exit(0); // pára o sistema
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                operacoes();
                break;
        }
    }
    public static void criarConta() {
        Cliente cliente = new Cliente();

        cliente.setNome(JOptionPane.showInputDialog("Nome: ")); // usa showImput pra receber informação do usuario
        cliente.setCpf(JOptionPane.showInputDialog("CPF: "));
        cliente.setEmail(JOptionPane.showInputDialog("Email: "));

        Conta conta = new Conta(cliente);
        contasBancarias.add(conta);
        JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
        operacoes();
    }
    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if (contasBancarias.size() > 0) {
            for (Conta contaa : contasBancarias) {
                if (contaa.getNumeroConta() == numeroConta) {
                    conta = contaa;
                }
            }
        }
        return conta;
    }
    public static void depositar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta: "));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorDeposito =
                    Double.parseDouble(JOptionPane.showInputDialog("Informe valor para depósito"));
            conta.depositar(valorDeposito);
            JOptionPane.showMessageDialog(null, "Valor depositado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "--- Conta não encontrada ---");
        }
        operacoes();
    }
    public static void sacar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta para saque: "));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorSaque =
                    Double.parseDouble(JOptionPane.showInputDialog("Informe quanto deseja sacar"));
            conta.sacar(valorSaque);
        } else {
            JOptionPane.showMessageDialog(null, "--- Conta não encontrada ---");
        }
        operacoes();
    }
    public static void transferir() {
        int numContaOrigem = Integer.parseInt(JOptionPane.showInputDialog("Número da conta de origem: "));
        Conta contaOrigem = encontrarConta(numContaOrigem);
        if (contaOrigem != null) {
            int numContaDestino = Integer.parseInt(JOptionPane.showInputDialog("Número da conta de destino: "));
            Conta contaDestino = encontrarConta(numContaDestino);
            if (contaDestino != null) {
                Double valor =
                        Double.parseDouble(JOptionPane.showInputDialog("Informe quanto deseja transferir"));
                contaOrigem.transferir(contaDestino, valor);
            } else {
                JOptionPane.showMessageDialog(null, "--- Conta de destino não encontrada ---");
            }
        } else {
            JOptionPane.showMessageDialog(null, "--- Conta de origem não encontrada ---");
        }
        operacoes();
    }
    public static void listarContas() {
        if (contasBancarias.size() > 0) {
            for (Conta conta : contasBancarias) {
                System.out.println(conta);
            }
        } else {
            JOptionPane.showMessageDialog(null, "--- Não há contas cadastradas ---");
        }
        operacoes();
    }
}
