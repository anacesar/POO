package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utilizador extends Entidade implements Serializable {
    private String codUtilizador;
    private List<Encomenda> encomendas_para_entrega;
    private List<Encomenda> encomendas_entregues;

    /**
     * Construtor parametrizado de um Utilizador.
     * * Aceita como parâmetros cada componente necessária excepto o código de Utilizador.
     */
    public Utilizador(String email, String password, String nome, GPS gps, int number) {
        super(email, password, nome,gps);
        this.codUtilizador = "u" + number;
        this.encomendas_para_entrega = new ArrayList<>();
        this.encomendas_entregues = new ArrayList<>();

    }

    /**
     * Construtor parametrizado de um Utilizador.
     * Necessário para a criação de um Utilizador através da leitura do ficheiro de logs.
     */
    public Utilizador(String codUtilizador, String nome, GPS gps) {
        super(codUtilizador,nome, gps);
        this.codUtilizador=codUtilizador;
        this.encomendas_para_entrega = new ArrayList<>();
        this.encomendas_entregues = new ArrayList<>();
    }

    /**
     * Construtor de cópia, ou seja, copia os dados de um Utilizador já existente.
     * @param utilizador que vamos copiar.
     */
    public Utilizador(Utilizador utilizador){
        super(utilizador.getEmail(), utilizador.getPassword(), utilizador.getNome(), utilizador.getGps());
        this.codUtilizador = utilizador.getCodUtilizador();
        this.encomendas_para_entrega = utilizador.getEncomendasEntrega();
        this.encomendas_entregues = utilizador.getEncomendas_entregues();
    }

    public String getCodUtilizador() {
        return this.codUtilizador;
    }

    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public List<Encomenda> getEncomendasEntrega() {
        return this.encomendas_para_entrega.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setEncomendasEntrega(List<Encomenda> encomendas) {
        this.encomendas_para_entrega = encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public List<Encomenda> getEncomendas_entregues() {
        return this.encomendas_entregues.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setEncomendas_entregues(List<Encomenda> encomendas) {
        this.encomendas_entregues = encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void addToEntrega (Encomenda e){
        this.encomendas_para_entrega.add(e);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nEmail: ").append(this.getEmail());
        sb.append("\nNome: ").append(this.getNome());
        sb.append("\nMorada: ").append(this.getGps());

        return sb.toString();
    }

    public Utilizador clone(){
        return new Utilizador(this);
    }

}
