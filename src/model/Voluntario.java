package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Voluntario extends Entidade implements Serializable, LicencaMedica {
    private String codVoluntario;
    private double classificacao;
    private Double raio;
    private boolean disponivel;
    private boolean licenca; //licença para entregar encomendas médicas
    private List<Encomenda> encomendas_entregues;
    private List<Encomenda> queue; // após sinalizar na app o tempo que levou a entrega ->passa para entregue

    /**
     * Construtor parametrizado de um Voluntario.
     * Aceita como parâmetros cada componente necessária.
     */
    public Voluntario(String email, String password, String nome, GPS gps, Double raio, int number) {
        super(email, password, nome, gps);
        this.codVoluntario = "v" + number;
        this.raio = raio;
        this.classificacao = 5;
        this.encomendas_entregues = new ArrayList<>();
        this.queue = new ArrayList<>();
    }

    /**
     * Construtor parametrizado de um Voluntario.
     * Necessário para a criação de um Voluntario através da leitura do ficheiro de logs.
     */
    public Voluntario(String codVoluntario, String nome, GPS gps, Double raio){
        super(codVoluntario,nome, gps);
        this.codVoluntario=codVoluntario;
        this.raio= raio;
        this.encomendas_entregues = new ArrayList<>();
        this.queue = new ArrayList<>();
    }

    /**
     * Construtor de cópia, ou seja, copia os dados de um Voluntario já existente.
     * @param voluntario que vamos copiar.
     */
    public Voluntario(Voluntario voluntario) {
        super(voluntario.getEmail(), voluntario.getPassword(), voluntario.getNome(), voluntario.getGps());
        this.codVoluntario = voluntario.getCodVoluntario();
        this.raio = voluntario.getRaio();
        this.classificacao = voluntario.getClassificacao();
        this.encomendas_entregues = voluntario.getEncomendas_entregues();
        this.queue = voluntario.getQueue();
    }

    public String getCodVoluntario() {
        return this.codVoluntario;
    }

    public void setCodVoluntario(String codVoluntario) {
        this.codVoluntario = codVoluntario;
    }

    public double getClassificacao() {
        return this.classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public Double getRaio() {
        return this.raio;
    }

    public void setRaio(Double raio) {
        this.raio = raio;
    }

    public List<Encomenda> getEncomendas_entregues() { return this.encomendas_entregues.stream().map(Encomenda::clone).collect(Collectors.toList()); }

    public void setEncomendas_entregues(List<Encomenda> encomendas) { this.encomendas_entregues = encomendas.stream().map(Encomenda::clone).collect(Collectors.toList()); }

    public List<Encomenda> getQueue() { return this.queue.stream().map(Encomenda::clone).collect(Collectors.toList()); }

    public void addToQueue (Encomenda e){
        this.queue.add(e);
    }

    public void setQueue(List<Encomenda> encomendas) { this.queue = encomendas.stream().map(Encomenda::clone).collect(Collectors.toList()); }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public boolean isLicenca() {
        return licenca;
    }

    public void setLicenca(boolean licenca) {
        this.licenca = licenca;
    }

    public void addClassificacao(double cl) {
        this.classificacao = (this.classificacao + cl) /2;
    }

    public Voluntario clone(){ return new Voluntario(this); }

    @Override
    public boolean aceitoTransporteMedicamentos() { return this.licenca; }

    @Override
    public void aceitaMedicamentos(boolean state) { this.licenca = state; }

    public void sinalizarEncomenda(int index, double time){
        Encomenda encomenda = this.queue.remove(index);
        encomenda.setTempo(time);
        this.encomendas_entregues.add(encomenda);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nNome: ").append(this.getNome());
        sb.append("\nEmail: ").append(this.getEmail());
        sb.append("\nMorada: ").append(this.getGps());
        sb.append("\nRaio de ação: ").append(this.getRaio());
        sb.append("\nClassificação: ").append(this.getClassificacao());


        sb.append("\nLicença: ").append(this.isLicenca());
        sb.append("\nDisponiblidade: ").append(this.isDisponivel());


        return sb.toString();
    }

}
