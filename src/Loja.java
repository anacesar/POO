import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Loja(String codLoja, String nome, GPS gps){
        super(nome, gps);
        this.codLoja = codLoja;
        this.queue = new ArrayList<>();
    }

    public Loja(Loja loja){
        super(loja.getEmail(), loja.getPassword(), loja.getNome(), loja.getGps());
        this.codLoja = loja.getCodLoja();
    }


    public String getCodLoja() {
        return this.codLoja;
    }

    public void setCodLoja(String codLoja) {
        this.codLoja = codLoja;
    }

    public List<Encomenda> getQueue() {
        return new ArrayList<>(this.queue);
    }

    public void setQueue(List<Encomenda> queue) {
        this.queue = queue;
    }
}
