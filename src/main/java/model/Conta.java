package model;

import utilitarios.Utils;

import javax.swing.*;

public class Conta {
    private static int accountCounter = 1;

    private final int numeroConta;
    private Cliente cliente;
    private Double saldo = 0.0;

    public Conta(Cliente cliente) {
        this.numeroConta = Conta.accountCounter;
        this.cliente = cliente;
        this.updateSaldo();
        Conta.accountCounter++;
    }
    public int getNumeroConta() {
        return numeroConta;
    }
    public Cliente getClient() {
        return cliente;
    }
    public void setClient(Cliente pessoa) {
        this.cliente = pessoa;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    private void updateSaldo() {
        this.saldo = this.getSaldo();
    }
    public String toString() {
        return "\nBank account: " + this.getNumeroConta() +
                "\nCliente: " + this.cliente.getNome() +
                "\nCPF: " + this.cliente.getCpf() +
                "\nEmail: " + this.cliente.getEmail() +
                "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
                "\n";
    }
    public void depositar(Double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);            //this.saldo = this.getSaldo() + valor;
            JOptionPane.showMessageDialog(null, "Seu depósito foi realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o depósito!");
        }
    }
    public void sacar(Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
    }
    public void transferir(Conta contaDeposito, Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);            //this.saldo = this.getSaldo() - valor;
            contaDeposito.saldo = contaDeposito.getSaldo() + valor;
            JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a transferência");
        }
    }
}
