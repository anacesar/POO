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
    private List<Encomenda> queue;
    private List<Encomenda> encomendas_entregues; //tempo de entrega + custo da entrega

    public Empresa(String email, String password, String nome, GPS gps, String nif, double raio, double precokm, int number) {
        super(email, password, nome, gps);
        this.codEmpresa = "l" + number;
        this.classificacao = 5;
        this.raio=raio;
        this.nif=nif;
        this.precokm=precokm;
        this.queue = new ArrayList<>();
        this.encomendas_entregues = new ArrayList<>();
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
        this.queue = new ArrayList<>();
        this.encomendas_entregues = new ArrayList<>();

    }

    public Empresa(Empresa empresa) {
        super(empresa.getEmail(), empresa.getPassword(), empresa.getNome(), empresa.getGps());
        this.codEmpresa = empresa.getCodEmpresa();
        this.classificacao = empresa.getClassificacao();
        this.nif=empresa.getNif();
        this.raio=empresa.getRaio();
        this.precokm=empresa.getPrecokm();
        this.queue = empresa.getQueue();
        this.encomendas_entregues = empresa.getEncomendas_entregues();

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

    public void addClassificacao(double cl) {
        this.classificacao = (this.classificacao + cl) / 2;
    }

    public boolean isDisponivel() { return this.disponivel; }

    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public boolean isLicenca() {
        return licenca;
    }

    public void setLicenca(boolean licenca) {
        this.licenca = licenca;
    }


    public List<Encomenda> getQueue() {
        return this.queue.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void addToQueue (Encomenda e){
        this.queue.add(e);
    }

    public List<Encomenda> getEncomendas_entregues() {
        return this.encomendas_entregues.stream().map(Encomenda::clone).collect(Collectors.toList());
    }


    public Empresa clone() {
        return new Empresa(this);
    }

    @Override
    public boolean aceitoTransporteMedicamentos() { return this.licenca; }

    @Override
    public void aceitaMedicamentos(boolean state) { this.licenca = state; }


    public void sinalizarEncomenda(int index, double time, double price){
        Encomenda encomenda = this.queue.remove(index);
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

        sb.append("\nLicença: ").append(this.isLicenca());
        sb.append("\nDisponiblidade: ").append(this.isDisponivel());

        return sb.toString();
    }

    public String toStringTOclients(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: ").append(this.getNome());
        sb.append("\nClassificação: ").append(this.getClassificacao());
        sb.append("\nPreço: ").append(this.getPrecokm());
        return sb.toString();
    }
}
