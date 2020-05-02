public class TrazAqui {
    private Data data;
    private Menu menuPrincipal, menuVoluntario, menuUtilizador, menuLoja, menuEmpresa;
    private MenuLogin menuLogin;
    int estado;


    private TrazAqui(){
        this.data = new Data();
        String[] principal = {"Administrador",
                "Utilizador",
                "Voluntário",
                "Empresa",
                "Loja"};
        String[] administrador = {"Carregar dados",
                "Salvar dados"};
        String[] sign = {"Sign-in",
                "Sign-up",
                "Top 10 ?????"};
        String[] utilizador = {};
        String[] voluntario = {};
        String[] loja = {};
        String[] empresa = {};

        }

    public static void main(String[] args) {
        new TrazAqui().run();
    }

    public void run() {
        System.out.println("------------------TrazAqui------------------");
        System.out.println("Clima de hoje: ");
       // System.out.println();
    }


    public String getEstado(){
        String[] tempo = {"Sol","Vento","Chuva", "Muita Chuva"};
        return null;
    }
}
