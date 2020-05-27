import Exceptions.EmailJaExisteException;
import Exceptions.EmailNaoRegistadoException;
import Exceptions.NomeInvalidoException;


public class TrazAqui {
    private Data data =new Data();
    private View view = new View();
    private Parser parser = new Parser(data);

    public TrazAqui(){ }

    public void carregaDados() throws EmailJaExisteException, NomeInvalidoException {
        this.parser.parse();
    }


    public View getView() {
        return this.view;
    }

    public void login() throws EmailNaoRegistadoException {
        this.view.printMensagem("Email: ");
        String email = this.view.getString();
        this.view.printMensagem("Password:  ");
        String pwd = this.view.getString();

        if (this.data.checkLogin(1, email, pwd)) {
            this.menuUtilizador();
        }else if (this.data.checkLogin(2, email, pwd)) {
            this.menuVoluntário();
        }else if (this.data.checkLogin(3, email, pwd)) {
            this.menuTransportadora();
        }else if (this.data.checkLogin(4, email, pwd)) {
            this.menuLoja();
        } else this.view.printMensagem("Dados de acesso inválidos!");
    }

        public void registo() throws EmailJaExisteException, EmailNaoRegistadoException {
             String SelecionaUser = this.view.SelecionaUser();
             if (!SelecionaUser.equals("1") && !SelecionaUser.equals("2") && !SelecionaUser.equals("3")) {
                 this.view.printMensagem("Opção Inválida! ");
                 this.registo();
             } else {
                 this.view.printMensagem("Email: ");
                 String email = this.view.getString();
                 this.view.printMensagem("Password:  ");
                 String pwd = this.view.getString();
                 this.view.printMensagem("Nome: ");
                 String nome = this.view.getString();
                 this.view.printMensagem("Localização X");
                 Double gpsx = this.view.getDouble();this.view.getString();
                 this.view.printMensagem("Localização Y");
                 Double gpsy = this.view.getDouble();

                 if (SelecionaUser.equals("1")) {
                     int nu= this.data.getnUtilizadores();
                     Utilizador u = new Utilizador(email, pwd, nome, new GPS(gpsx, gpsy), nu+1);
                     this.data.setnUtilizadores(nu+1);

                     if (!this.data.checkLogin(1,email,pwd)) {
                         this.data.addUtilizador(u);
                         this.view.printMensagem("Utilizador registado com sucesso, por favor proceda a login");
                     } else {
                         this.view.printMensagem("Utilizador já existente! ");
                     }

                 } else if (SelecionaUser.equals("2")){
                     this.view.printMensagem("Raio de ação: ");
                     double raio= this.view.getDouble();

                     int nv= this.data.getnVoluntarios();
                     Voluntario v = new Voluntario(email, pwd, nome,new GPS(gpsx, gpsy), raio, nv+1);
                     this.data.setnVoluntarios(nv+1);

                     if (!this.data.checkLogin(2,email,pwd)) {
                         this.data.addVoluntario(v);
                         this.view.printMensagem("Voluntário registado com sucesso, por favor proceda a login");
                     } else {
                         this.view.printMensagem("Voluntário já existente! ");
                     }
                 }else {
                     this.view.printMensagem("Raio de ação: ");
                     double raio= this.view.getDouble();

                     this.view.printMensagem("Nif: ");
                     String nif= this.view.getString();

                     this.view.printMensagem("Preço por km: ");
                     Double precokm= this.view.getDouble();

                     int ne= this.data.getnEmpresas();
                     Empresa e = new Empresa(email, pwd, nome,new GPS(gpsx, gpsy),nif,raio,precokm, ne+1);
                     this.data.setnEmpresas(ne+1);

                     if (!this.data.checkLogin(3,email,pwd)) {
                         this.data.addEmpresa(e);
                         this.view.printMensagem("Empresa Transportadora registado com sucesso, por favor proceda a login");
                     } else {
                         this.view.printMensagem("Empresa Transportadora já existente! ");
                     }

                 }

             }

         }

    private void menuUtilizador() {
        String op = this.view.menuUtilizador();

        switch(op) {
            case "1":
                fazerEncomenda();
                break;
            case "2":

            default:

        }

    }

    private void menuVoluntário() {
        String op = this.view.menuVoluntário();

        switch(op) {
            case "1":
                fazerEncomenda();
                break;
            case "2":

            default:

        }

    }

    private void menuTransportadora() {
        String op = this.view.menuTransportadora();

        switch(op) {
            case "1":
                fazerEncomenda();
                break;
            case "2":

            default:

        }

    }

    private void menuLoja() {
        String op = this.view.menuLoja();

        switch(op) {
            case "1":
                fazerEncomenda();
                break;
            case "2":

            default:

        }

    }

    private void fazerEncomenda(){

    }

}
