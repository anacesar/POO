import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Empresa extends Entidade implements Serializable {
    private String codEmpresa;
    private double classificacao;
    private String nif;
    private double raio;
    private double precokm;
    private List<Encomenda> encomendas_aceites;

    public Empresa(String email, String password, String nome, GPS gps, String nif, double raio, double precokm, int number) {
        super( nome,email, password, gps);
        this.codEmpresa = "l" + number;
        this.classificacao = 0;
        this.raio=raio;
        this.nif=nif;
        this.precokm=precokm;
        this.encomendas_aceites = new ArrayList<>();
    }

    /**
     * Construtor parametrizado de um Voluntario.
     * Necessário para a criação de um Voluntario através da leitura do ficheiro de logs.
     */
    public Empresa ( String codEmpresa,String nome, GPS gps, String nif, double raio, double precokm) {
        super(codEmpresa,nome, gps);
        this.codEmpresa=codEmpresa;
        this.nif=nif;
        this.raio=raio;
        this.precokm=precokm;
        this.encomendas_aceites = new ArrayList<>();
    }

    public Empresa(Empresa empresa) {
        super(empresa.getEmail(), empresa.getPassword(), empresa.getNome(), empresa.getGps());
        this.codEmpresa = empresa.getCodEmpresa();
        this.classificacao = empresa.getClassificacao();
        this.nif=empresa.getNif();
        this.raio=empresa.getRaio();
        this.precokm=empresa.getPrecokm();
        this.encomendas_aceites = empresa.getEncomendas_aceites();
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

    public List<Encomenda> getEncomendas_aceites() {
        return this.encomendas_aceites;
    }

    public void setEncomendas_aceites(List<Encomenda> encomendas_aceites) {
        this.encomendas_aceites = encomendas_aceites.stream().collect(Collectors.toList());
    }

    public void addClassificacao(double cl) {
        this.classificacao = (this.classificacao + cl) / 2;
    }

    public Empresa clone() {
        return new Empresa(this);
    }
}
