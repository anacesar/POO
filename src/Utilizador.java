import java.util.List;

public class Utilizador extends Entidade {
    private String codUtilizador;
    private List<Encomenda> encomendas;

    /**
     * Construtor parametrizado de um Utilizador.
     * * Aceita como parâmetros cada componente necessária excepto o código de Utilizador.
     */
    public Utilizador(String email, String password, String nome, GPS gps, int number) {
        super(nome, email,password,gps);
        this.codUtilizador = "u" + number;
    }

    /**
     * Construtor parametrizado de um Utilizador.
     * Necessário para a criação de um Utilizador através da leitura do ficheiro de logs.
     */
    public Utilizador(String codUtilizador, String nome, GPS gps) {
        super(codUtilizador,nome, gps);
        this.codUtilizador=codUtilizador;
    }

    /**
     * Construtor de cópia, ou seja, copia os dados de um Utilizador já existente.
     * @param utilizador que vamos copiar.
     */
    public Utilizador(Utilizador utilizador){
        super(utilizador.getEmail(), utilizador.getPassword(), utilizador.getNome(), utilizador.getGps());
        this.codUtilizador = utilizador.getCodUtilizador();
        this.encomendas = utilizador.getEncomendas();
    }

    public String getCodUtilizador() {
        return this.codUtilizador;
    }

    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public List<Encomenda> getEncomendas() {
        return this.encomendas;
    }

    public void setEncomendas(List<Encomenda> encomendas) {
        this.encomendas = encomendas;
    }

    public Utilizador clone(){
        return new Utilizador(this);
    }
}
