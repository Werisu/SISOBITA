/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Wellysson
 */
public class OS {
    
    protected int num_os;
    protected String dataDeLancamento;
    protected String horaDeLancamento;
    protected int cliente;
    protected int funcionario;
    protected String desc_problema;
    protected String desc_servico;
    protected Double valor_servico;
    protected Double total;
    protected String status;

    public OS(int num_os, String dataDeLancamento, String horaDeLancamento, int cliente, int funcionario, String desc_problema, String desc_servico, Double valor_servico, Double total, String status) {
        this.num_os = num_os;
        this.dataDeLancamento = dataDeLancamento;
        this.horaDeLancamento = horaDeLancamento;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.desc_problema = desc_problema;
        this.desc_servico = desc_servico;
        this.valor_servico = valor_servico;
        this.total = total;
        this.status = status;
    }

    public int getNum_os() {
        return num_os;
    }

    public void setNum_os(int num_os) {
        this.num_os = num_os;
    }

    public String getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(String dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public String getHoraDeLancamento() {
        return horaDeLancamento;
    }

    public void setHoraDeLancamento(String horaDeLancamento) {
        this.horaDeLancamento = horaDeLancamento;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public String getDesc_problema() {
        return desc_problema;
    }

    public void setDesc_problema(String desc_problema) {
        this.desc_problema = desc_problema;
    }

    public String getDesc_servico() {
        return desc_servico;
    }

    public void setDesc_servico(String desc_servico) {
        this.desc_servico = desc_servico;
    }

    public Double getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(Double valor_servico) {
        this.valor_servico = valor_servico;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
