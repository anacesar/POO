import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Empresa extends Entidade {
    private String codEmpresa;
    private double classificacao;
    private List<Encomenda> encomendas_aceites;

    public Empresa(String email, String password, String nome, GPS gps, int number) {
        super( nome,email, password, gps);
        this.codEmpresa = "l" + number;
        this.classificacao = 0;
        this.encomendas_aceites = new ArrayList<>();
    }

    /**
     * Construtor parametrizado de um Voluntario.
     * Necessário para a criação de um Voluntario através da leitura do ficheiro de logs.
     */
    public Empresa (String nome, GPS gps, String codEmpresa) {
        super(codEmpresa,nome, gps);
        this.codEmpresa=codEmpresa;
        this.encomendas_aceites = new ArrayList<>();
    }

    public Empresa(Empresa empresa) {
        super(empresa.getEmail(), empresa.getPassword(), empresa.getNome(), empresa.getGps());
        this.codEmpresa = empresa.getCodloja();
        this.classificacao = empresa.getClassificacao();
        this.encomendas_aceites = empresa.getEncomendas_aceites();
    }

    public String getCodloja() {
        return this.codEmpresa;
    }

    public void setCodloja(String codloja) {
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
