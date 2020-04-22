public class Entidade {
    private String email;
    private String password;
    private String nome;
    private GPS gps;


    /**
     * Construtor parametrizado de um Ator.
     * Aceita como parâmetros cada componente necessária.
     */

    public Entidade(String email, String password, String nome, GPS gps) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.gps = gps;
    }

    public Entidade(String nome, GPS gps){
        String[] token = nome.split(" ");
        this.email = token[0] + "@email.com";
        this.password = token[0];
        this.nome = nome;
        this.gps = gps;
    }

    /**
     * Construtor de cópia de um Ator.
     * Aceita como parâmetro outro Ator e utiliza os métodos de acesso aos valores das variáveis de instância.
     */
    public Entidade(Entidade entidade) {
        this.email = entidade.getEmail();
        this.nome = entidade.getNome();
        this.password = entidade.getPassword();
        this.gps = entidade.getGps().clone();
    }

    /**
     * Método de instância (get).
     * Devolve o email associado ao Ator.
     *
     * @return email do Ator.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Método de instância (set).
     * Atualiza o email do Ator.
     *
     * @param email novo email do Ator.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método de instância (get).
     * Devolve a password associada ao Ator.
     *
     * @return password do Ator.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Método de instância (set).
     * Atualiza a password do Ator.
     *
     * @param password nova password do Ator.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método de instância (get).
     * Devolve o nome associado ao Ator.
     *
     * @return nome do Ator.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Método de instância (set).
     * Atualiza o email do utilizador.
     *
     * @param nome novo nome do Ator.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método de instância (get).
     * Devolve o gps associado ao Ator.
     *
     * @return GPS do Ator.
     */
    public GPS getGps() {
        return this.gps;
    }

    /**
     * Método de instância (set).
     * Atualiza o gps do Ator.
     *
     * @param gps novo GPS do utilizador.
     */
    public void setGps(GPS gps) {
        this.gps = gps;
    }


    /**
     * Método que devolve a representação em String do Ator.
     * @return String com toda a informação do Ator.
     */
     public String toString(){
        return "Nome: " + this.nome +
                "\nE-mail: " + this.email +
                "\nPassword: " + this.password +
                "\nGPS: " + this.gps.toString();
     }

    /**
     * Método que compara dois objetos.
     * @return booleano que dá verdadeiro se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Entidade entidade = (Entidade) o;
        return (this.email.equals(entidade.getEmail()) &&
                this.nome.equals(entidade.getNome()) &&
                this.password.equals(entidade.getPassword()) &&
                this.gps.equals(entidade.getGps()));
    }

    /**
     * Método que cria uma cópia do Ator passado como parâmetro.
     * @return clone do Ator.
     */
    public Entidade clone(){
        return new Entidade(this);
    }
}
