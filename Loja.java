import java.util.ArrayList;
import java.util.List;

public class Loja extends Entidade {
    private String codLoja;
    private List<Encomenda> queue;

    /**
     * Construtor parametrizado de uma Loja.
     * Aceita como parâmetros cada componente necessária.
     */
    public Loja(String email, String password, String nome, GPS gps, int number) {
        super(email, password, nome, gps);
        this.codLoja = "l" + number;
        this.queue = new ArrayList<>();
    }

    /**
     * Construtor parametrizado de uma Loja.
     * Necessário para a criação de uma loja através da leitura do ficheiro de logs.
     */
    public Loja(String codLoja, String nome, GPS gps, Double raio){
        super(nome, gps);
        this.codLoja = codLoja;
        this.queue = new ArrayList<>();
    }

}
