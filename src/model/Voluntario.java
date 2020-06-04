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
    private List<Encomenda> encomendas_por_sinalizar;

    /**
     * Construtor parametrizado de um Voluntario.
     * Aceita como parâmetros cada componente necessária.
     */
    public Voluntario(String email, String password, String nome, GPS gps, Double raio, int number) {
        super(email, password, nome, gps);
        this.codVoluntario = "v" + number;
        this.raio = raio;
        this.classificacao = 0;
        this.encomendas_entregues = new ArrayList<>();
        this.encomendas_por_sinalizar = new ArrayList<>();
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
        this.encomendas_por_sinalizar = new ArrayList<>();
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
        this.encomendas_por_sinalizar = voluntario.getEncomendas_por_sinalizar();
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

    public boolean getDisponivel() { return this.disponivel; }

    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public List<Encomenda> getEncomendas_entregues() { return this.encomendas_entregues.stream().map(Encomenda::clone).collect(Collectors.toList()); }

    public void setEncomendas_entregues(List<Encomenda> encomendas) { this.encomendas_entregues = encomendas.stream().map(Encomenda::clone).collect(Collectors.toList()); }

    public List<Encomenda> getEncomendas_por_sinalizar() { return this.encomendas_por_sinalizar.stream().map(Encomenda::clone).collect(Collectors.toList()); }

    public void setEncomendas_por_sinalizar(List<Encomenda> encomendas) { this.encomendas_por_sinalizar = encomendas.stream().map(Encomenda::clone).collect(Collectors.toList()); }

    public void addClassificacao(double cl) {
        this.classificacao = (this.classificacao + cl) /2;
    }

    public Voluntario clone(){ return new Voluntario(this); }

    @Override
    public boolean aceitoTransporteMedicamentos() { return this.licenca; }

    @Override
    public void aceitaMedicamentos(boolean state) { this.licenca = state; }

    public void sinalizarEncomenda(int index, double time){
        Encomenda encomenda = this.encomendas_por_sinalizar.remove(index);
        encomenda.setTempo(time);
        this.encomendas_entregues.add(encomenda);
    }


}
