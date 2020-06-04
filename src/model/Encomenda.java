package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Encomenda implements Serializable {
    /* variáveis de instância que classificam uma encomenda */
    private String codEncomenda;
    private String codUtilizador;
    private String codLoja;
    private double peso;

    private String nif;
    private String morada;

    private int numero;   // ?
    private LocalDate data;
    private List<Linha_Encomenda> linhas;

    /* após entrega da encomenda */
    private double tempo; //tempo que demorou a entrega da encomenda
    private double preco; //preco associado a entrega

    public Encomenda() {
        this.codEncomenda = "";
        this.codUtilizador = "";
        this.codLoja = "";
        this.peso=0.0;

        this.nif = "";
        this.morada = "";
        this.numero = 0;
        this.data = LocalDate.now();
        this.linhas = new ArrayList<>();

        this.tempo = 0;
        this.preco = 0;
    }


    public Encomenda(String codEncomenda, String codUtilizador, String codLoja, double peso, String nif, String morada, int numero, LocalDate data, ArrayList<Linha_Encomenda> linhas) {
        this.codEncomenda = codEncomenda;
        this.codUtilizador = codUtilizador;
        this.codLoja = codLoja;
        this.peso=peso;

        this.nif = nif;
        this.morada = morada;
        this.numero = numero;
        this.data = data;
        this.linhas=linhas;

        this.tempo = 0;
        this.preco = 0;
    }

    /**
     * Construtor parametrizado de uma encomenda.
     * Necessário para a criação de um Encomenda através da leitura do ficheiro de logs.
     */
    public Encomenda(String codEncomenda, String codUtilizador, String codLoja, double peso, LocalDate data, ArrayList<Linha_Encomenda> linhas) {
        this.codEncomenda = codEncomenda;
        this.codUtilizador = codUtilizador;
        this.codLoja = codLoja;
        this.peso=peso;

        this.data = data;
        this.linhas=linhas;

        this.tempo = 0;
        this.preco = 0;
    }

    public Encomenda(Encomenda encomenda) {
        this.codEncomenda = encomenda.getCodEncomenda();
        this.codUtilizador = encomenda.getCodUtilizador();
        this.codLoja = encomenda.getCodLoja();
        this.peso= encomenda.getPeso();

        this.nif = encomenda.getNif();
        this.morada = encomenda.getMorada();
        this.numero = encomenda.getNumero();
        this.data = encomenda.getData();
        this.linhas=encomenda.getLinhas();
        this.tempo = encomenda.getTempo();
        this.preco = encomenda.getPreco();
    }

    public String getCodEncomenda() {
        return codEncomenda;
    }

    public void setCodEncomenda(String codEncomenda) {
        this.codEncomenda = codEncomenda;
    }

    public String getCodUtilizador() {
        return codUtilizador;
    }

    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public String getCodLoja() {
        return codLoja;
    }

    public void setCodLoja(String codLoja) {
        this.codLoja = codLoja;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getNif() {return this.nif;}

    public void setNif(String nif) {this.nif = nif;}

    public String getMorada() {return this.morada;}

    public void setMorada(String morada) {this.morada = morada;}

    public int getNumero() {return this.numero;}

    public void setNumero(int numero) {this.numero = numero;}

    public LocalDate getData() {return this.data;}

    public void setData(LocalDate data) {this.data = data;}

    public ArrayList<Linha_Encomenda> getLinhas() {
        return this.linhas.stream().map(Linha_Encomenda::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public void setLinhas(ArrayList<Linha_Encomenda> l){
        this.linhas = l.stream().map(Linha_Encomenda::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public double getTempo() { return this.tempo; }

    public void setTempo(double tempo) { this.tempo = tempo; }

    public double getPreco() { return this.preco; }

    public void setPreco(double preco) { this.preco = preco; }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    public double calculaValorTotal(){
        double res= 0;
        Iterator<Linha_Encomenda> it = linhas.iterator();
        Linha_Encomenda l;
        while(it.hasNext()){
            l = it.next();
            res+= l.calculaValorLinhaEnc();
        }
        return res;
    }

    public int numeroTotalProdutos(){
        int n=0;
        for(Linha_Encomenda le : this.linhas)
            n+= le.getQuantidade();
        return n;
    }

    public boolean existeProdutoEncomenda(String refProduto){
        boolean found = false;
        Iterator<Linha_Encomenda> it = linhas.iterator();
        Linha_Encomenda l;
        while(it.hasNext() && !found){
            l = it.next();
            found = refProduto.compareTo(l.getCodProduto())==0;
        }
        return found;
    }

    public void adicionaLinha(Linha_Encomenda linha){
        this.linhas.add(linha);
    }

    public void removeProduto(String codProd){
        Iterator<Linha_Encomenda> it = linhas.iterator();
        Linha_Encomenda l;
        while(it.hasNext()){
            l = it.next();
            if(codProd.compareTo(l.getCodProduto())==0) it.remove();
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Código da encomenda: ").append(this.codEncomenda);

        return sb.toString();
    }

}
