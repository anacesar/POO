public class Empresa extends Entidade{
    private String codloja;

    public Empresa(String email, String password, String nome, GPS gps, String codloja) {
        super(email, password, nome, gps);
        this.codloja = codloja;
    }

    public Empresa(String nome, GPS gps, String codloja) {
        super(nome, gps);
        this.codloja = codloja;
    }

    public Empresa(Empresa empresa) {
        super(empresa.getEmail(), empresa.getPassword(), empresa.getNome(), empresa.getGps());
        this.codloja = empresa.getCodloja();
    }

    public String getCodloja() {
        return this.codloja;
    }

    public void setCodloja(String codloja) {
        this.codloja = codloja;
    }
}
