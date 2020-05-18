import Exceptions.EmailJaExisteException;



public class TrazAqui {
    private Data data;
    private View view = new View();

    public TrazAqui(){ }

    public View getView() {
        return this.view;
    }

     void login(){
        this.view.printMensagem("Email: ");
        String user = this.view.getString();
        this.view.printMensagem("Password:  ");
        String pwd = this.view.getString();

          // VER SE O UTILIZADOR EXISTE.....
         
        this.view.menuInicial();
    }

     public void registo() throws EmailJaExisteException {
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

                     if (this.data.checkLogin(2,email,pwd) == false) {
                         this.data.addVoluntario(v);
                         this.view.printMensagem("Voluntário registado com sucesso, por favor proceda a login");
                     } else {
                         this.view.printMensagem("Voluntário já existente! ");
                     }
                 }else {
                     int ne= this.data.getnEmpresas();
                     Empresa e = new Empresa(email, pwd, nome,new GPS(gpsx, gpsy), ne+1);
                     this.data.setnEmpresas(ne+1);

                     if (this.data.checkLogin(3,email,pwd) == false) {
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
                this.fazerEncomenda();
                break;
            case "2":

            default:

        }

    }

    private void fazerEncomenda(){

    }

}
