package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Empresa extends Entidade implements Serializable, LicencaMedica {
    private String codEmpresa;
    private double classificacao;
    private String nif;
    private double raio;
    private double precokm;
    private boolean disponivel;
    private boolean licenca;
    private List<Encomenda> encomendas_entregues;
    private List<Encomenda> encomendas_por_sinalizar;

    public Empresa(String email, String password, String nome, GPS gps, String nif, double raio, double precokm, int number) {
        super(email, password, nome, gps);
        this.codEmpresa = "l" + number;
        this.classificacao = 0;
        this.raio=raio;
        this.nif=nif;
        this.precokm=precokm;
        this.encomendas_entregues = new ArrayList<>();
        this.encomendas_por_sinalizar = new ArrayList<>();
    }

    /**
     * Construtor parametrizado de um Voluntario.
     * Necessário para a criação de um Voluntario através da leitura do ficheiro de logs.
     */
    public Empresa(String codEmpresa, String nome, GPS gps, String nif, double raio, double precokm) {
        super(codEmpresa,nome, gps);
        this.codEmpresa=codEmpresa;
        this.nif=nif;
        this.raio=raio;
        this.precokm=precokm;
        this.encomendas_entregues = new ArrayList<>();
        this.encomendas_por_sinalizar = new ArrayList<>();

    }

    public Empresa(Empresa empresa) {
        super(empresa.getEmail(), empresa.getPassword(), empresa.getNome(), empresa.getGps());
        this.codEmpresa = empresa.getCodEmpresa();
        this.classificacao = empresa.getClassificacao();
        this.nif=empresa.getNif();
        this.raio=empresa.getRaio();
        this.precokm=empresa.getPrecokm();
        this.encomendas_entregues = empresa.getEncomendas_entregues();
        this.encomendas_por_sinalizar = empresa.getEncomendas_sinalizar();

    }

    public double getRaio() {
        return this.raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public double getPrecokm() {
        return this.precokm;
    }

    public void setPrecokm(double precokm) {
        this.precokm = precokm;
    }

    public String getNif() {
        return this.nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCodEmpresa() {
        return this.codEmpresa;
    }

    public void setCodEmpresa(String codloja) {
        this.codEmpresa = codloja;
    }

    public double getClassificacao() {
        return this.classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public boolean isDisponivel() { return this.disponivel; }

    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    //preciso corrigir
    public List<Encomenda> getEncomendas_entregues() {
        return this.encomendas_entregues.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setEncomendas_entregues(List<Encomenda> encomendas_entregues) {
        this.encomendas_entregues = encomendas_entregues.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public List<Encomenda> getEncomendas_sinalizar() {
        return this.encomendas_por_sinalizar.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setEncomendas_sinalizar(List<Encomenda> encomendas) {
        this.encomendas_por_sinalizar = encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void addClassificacao(double cl) {
        this.classificacao = (this.classificacao + cl) / 2;
    }

    public Empresa clone() {
        return new Empresa(this);
    }

    @Override
    public boolean aceitoTransporteMedicamentos() { return this.licenca; }

    @Override
    public void aceitaMedicamentos(boolean state) { this.licenca = state; }


    public void sinalizarEncomenda(int index, double time, double price){
        Encomenda encomenda = this.encomendas_por_sinalizar.remove(index);
        encomenda.setTempo(time);
        encomenda.setPreco(price);
        this.encomendas_entregues.add(encomenda);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: ").append(this.getNome());
        sb.append("\nEmail: ").append(this.getEmail());
        sb.append("\nNIF: ").append(this.getNif());
        sb.append("\nLocalização: ").append(this.getGps());
        sb.append("\nRaio de ação: ").append(this.getRaio());
        sb.append("\nClassificação: ").append(this.getClassificacao());
        return sb.toString();
    }
}
