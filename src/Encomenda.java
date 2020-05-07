import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Encomenda {
    private String nome;
    private String nif;
    private String morada;
    private int numero;
    private LocalDate data;
    private List<Linha_Encomenda> linhas;

    public Encomenda() {
        this.nome = "";
        this.nif = "";
        this.morada = "";
        this.numero = 0;
        this.data = LocalDate.now();
        this.linhas = new ArrayList<>();
    }

    public Encomenda(String nome, String nif, String morada, int numero, LocalDate data, ArrayList<Linha_Encomenda> linhas) {
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.numero = numero;
        this.data = data;
        setLinhas(linhas);
    }

    public Encomenda(Encomenda encEficiente) {
        this.nome = encEficiente.getNome();
        this.nif = encEficiente.getNif();
        this.morada = encEficiente.getMorada();
        this.numero = encEficiente.getNumero();
        this.data = encEficiente.getData();
        setLinhas(encEficiente.getLinhas());
    }

    public String getNome() {return this.nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getNif() {return this.nif;}

    public void setNif(String nif) {this.nif = nif;}

    public String getMorada() {return this.morada;}

    public void setMorada(String morada) {this.morada = morada;}

    public int getNumero() {return this.numero;}

    public void setNumero(int numero) {this.numero = numero;}

    public LocalDate getData() {return this.data;}

    public void setData(LocalDate data) {this.data = data;}

    public ArrayList<Linha_Encomenda> getLinhas() {
        ArrayList<Linha_Encomenda> l = new ArrayList<>();
        for(Linha_Encomenda le : this.linhas){
            l.add(le.clone());
        }
        return l;
    }

    public void setLinhas(ArrayList<Linha_Encomenda> l) {
        for(Linha_Encomenda le: l){
            this.linhas.add(le.clone());
        }
    }

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

    void printLinhasEncomenda(){
        for(Linha_Encomenda le : this.linhas)
            System.out.println(le.toString());
    }
}
