public class Empresa extends Entidade{
    private String codloja;
    private double classificacao;
    private List<Encomenda> encomendas_aceites;

    public Empresa(String email, String password, String nome, GPS gps, String codloja) {
        super(email, password, nome, gps);
        this.codloja = codloja;
        this.classificacao = 0;
        this.encomendas_aceites = new ArrayList<>();
    }

    public Empresa(String nome, GPS gps, String codloja) {
        super(nome, gps);
        this.codloja = codloja;
        this.encomendas_aceites = new ArrayList<>();
    }

    public Empresa(Empresa empresa) {
        super(empresa.getEmail(), empresa.getPassword(), empresa.getNome(), empresa.getGps());
        this.codloja = empresa.getCodloja();
        this.classificacao = empresa.getClassificacao();
        this.encomendas_aceites = empresa.getEncomendas_aceites();
    }

    public String getCodloja() {
        return this.codloja;
    }

    public void setCodloja(String codloja) {
        this.codloja = codloja;
    }

    public double getClassificacao() {
        return this.classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public List<Encomenda> getEncomendas_aceites() {
        return new ArrayList<>(this.encomendas_aceites);
    }

    public void setEncomendas_aceites(List<Encomenda> encomendas_aceites) {
        this.encomendas_aceites = encomendas_aceites.stream().collect(Collectors.toList());
    }

    public void addClassificacao(double cl) {
        this.classificacao = (this.classificacao + cl) /2;
    }


}
