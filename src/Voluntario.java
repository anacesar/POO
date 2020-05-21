import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Voluntario extends Entidade implements Serializable {
    private String codVoluntario;
    private double classificacao;
    private Double raio;
    private List<Encomenda> encomendas_aceites;

    /**
     * Construtor parametrizado de um Voluntario.
     * Aceita como parâmetros cada componente necessária.
     */
    public Voluntario(String email, String password, String nome, GPS gps, Double raio, int number) {
        super(email, password, nome, gps);
        this.codVoluntario = "v" + number;
        this.raio = raio;
        this.classificacao = 0;
        this.encomendas_aceites = new ArrayList<>();
    }

    /**
     * Construtor parametrizado de um Voluntario.
     * Necessário para a criação de um Voluntario através da leitura do ficheiro de logs.
     */
    public Voluntario(String codVoluntario, String nome, GPS gps, Double raio){
        super(codVoluntario,nome, gps);
        this.codVoluntario=codVoluntario;
        this.raio= raio;
        this.encomendas_aceites = new ArrayList<>();
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
        this.encomendas_aceites = voluntario.getEncomendas_aceites();
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

    //corrigir
    public List<Encomenda> getEncomendas_aceites() {
        return new ArrayList<>(this.encomendas_aceites);
    }

    public void setEncomendas_aceites(List<Encomenda> encomendas_aceites) {
        this.encomendas_aceites = encomendas_aceites.stream().collect(Collectors.toList());
    }

    public void addClassificacao(double cl) {
        this.classificacao = (this.classificacao + cl) /2;
    }

    public Voluntario clone(){ return new Voluntario(this); }
}
