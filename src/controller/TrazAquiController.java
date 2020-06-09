package controller;

import exceptions.EmailJaExisteException;
import model.*;
import view.Menu;
import view.MenuLogin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrazAquiController {
    /* camada lógica */
    private TrazAquiModel model;

    /* Menus da aplicaçãoo - View */
    private Menu mainMenu, signUpMenu, menuAdmin, menuUtilizador, menuVoluntario, menuLoja, menuEmpresa, auxiliar;
    private MenuLogin menuLogin;


    public TrazAquiController(TrazAquiModel model){
        this.model = model;

        /* criação dos menus */
        String[] mainMenu = {"Sign-in",
                "Sign-up",
                "Top 10 utilizadores",
                "Top 10 empresas"};
        String[] signup = {"Novo Utilizador",
                "Novo Voluntário",
                "Nova Loja",
                "Nova Empresa"};
        String[] signin = {"Administrador",
                "Utilizador",
                "Voluntário",
                "Loja",
                "Empresa"};
        String[] admin = {"Carregar dados atráves de ficheiro",
                "Gravar Estado"};
        String[] utilizador = {"Fazer Encomenda",
                "Ver encomendas disponíveis para entrega",
                "Classificar encomendas recebidas",
                "Consultar as minhas encomendas",
                "Ver minha informação pessoal"};
        String[] voluntario = {"Sinalizar disponibilidade",
                "Registar tempo de entrega de uma encomenda",
                "Licença para entregar encomendas médicas",
                "Consultar encomendas que transportei",
                "Ver minha informação pessoal"};
        String[] loja = {"Registar encomenda pronta para entrega",
                "Consultar histórico de encomendas",
                "Ver minha informação pessoal"};
        String[] empresa = {"Sinalizar disponiblidade",
                "Registar tempo de entrega de uma encomenda e custo associado",
                "Licença para entregar encomendas médicas",
                "Consultar encomendas transportadas",
                "Ver minha informação pessoal"};
        String[] aux = {};
        this.mainMenu = new Menu(mainMenu);
        this.signUpMenu = new Menu(signup);
        this.menuAdmin = new Menu(admin);
        this.menuUtilizador = new Menu(utilizador);
        this.menuVoluntario = new Menu(voluntario);
        this.menuLoja = new Menu(loja);
        this.menuEmpresa = new Menu(empresa);
        this.auxiliar = new Menu(aux);
        this.menuLogin = new MenuLogin(signin);

        //carregar obj
        try {
            this.model = TrazAquiModel.carregaEstado();
        } catch(FileNotFoundException e) {
            System.out.println("Parece que é a primeira utilização... A carregar ficheiro 'logs.txt' ...");
        try {
                carregaFicheiro(null);
            } catch(IOException | EmailJaExisteException ex) {
                System.out.println("Erro a carregar ficheiro 'logs.txt'. ");
            }
        } catch(IOException e) {
            System.out.println("Ops! Erro de leitura!");
        } catch(ClassNotFoundException e) {
            System.out.println("Ops! Formato de ficheiro de dados errado!");
        }
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() throws IOException {
        System.out.println("----------------------TrazAquiApp------------------------");
        System.out.println("nr users: " + model.nUsers());
        System.out.println("nr volu: " + model.nVolu());
        System.out.println("nr lojas: " + model.nLojas());
        System.out.println("nr encomendas: " + model.nEncomendas());


        do {
            /* executing main menu while user don't want to leave */
            this.mainMenu.executa();
            switch(this.mainMenu.getOp()) {
                case 1:
                    clearScreen();
                    do{
                        /* entidade to login */
                        this.menuLogin.executaReader();
                        if (this.menuLogin.getOp() == 0) {
                            break;
                        }
                        do{
                            /* do login */
                            this.menuLogin.executaParametros();
                        }while(! this.model.checkLogin(this.menuLogin.getOp(), this.menuLogin.getEmail(), this.menuLogin.getPassword()));
                        /* check what entidade logged */
                        switch(menuLogin.getOp()){
                            /* Admin is logged ??? e preciso admin */
                            case 1:
                                do{
                                    this.menuAdmin.executa();
                                    switch(this.menuAdmin.getOp()){
                                        case 1: /* admin wants to load file */
                                            admin_load_file();
                                            break;
                                        case 2: /* admin wants to save obj */
                                            this.model.guardaEstado();
                                            break;

                                    }
                                }while(this.menuAdmin.getOp()!=0);
                                break;
                            /* utilizador is logged */
                            case 2:
                                do{
                                    String uEmail = this.menuLogin.getEmail();
                                    this.menuUtilizador.executa();
                                    switch(this.menuUtilizador.getOp()){
                                        case 1: /* Fazer Encomenda */
                                            fazerEncomenda(uEmail);
                                            break;
                                        case 2: /* Ver encomendas para entrega (encomendas por entregar)*/
                                            encomendasEntrega(uEmail);
                                            break;
                                        case 3: /* Classificar encomendas recebidas */
                                            break;
                                        case 4: /* As minhas encomendas */
                                            minhasEncomendas(uEmail);
                                            break;
                                        case 5: /*Ver a minha informação pessoal*/
                                            this.menuUtilizador.sendMessage(this.model.getUtilizador(uEmail).toString());
                                            break;
                                    }
                                }while(this.menuUtilizador.getOp()!=0);
                                break;
                            /* voluntario is logged */
                            case 3:
                                do{
                                    String vEmail = this.menuLogin.getEmail();
                                    this.menuVoluntario.executa();
                                    switch(this.menuVoluntario.getOp()){
                                        case 1: /* Sinalizar disponibilidade */
                                            disponibilidade(this.menuLogin.getOp(), vEmail);
                                            break;
                                        case 2: /* Registar tempo de entrega de uma encomenda */
                                            tempo_entrega(this.menuLogin.getOp(), vEmail);
                                            break;
                                        case 3: /*Licença para entregar encomendas médicas */
                                            licenca(this.menuLogin.getOp(), vEmail);
                                            break;
                                        case 4: /*Consultar encomendas que transportei*/
                                            this.menuVoluntario.sendMessage(String.valueOf(this.model.getVoluntario(vEmail).getEncomendas_entregues()));
                                            break;
                                        case 5: /*Ver a minha informação pessoal*/
                                            this.menuVoluntario.sendMessage(this.model.getVoluntario(vEmail).toString());
                                            break;

                                    }
                                }while(this.menuVoluntario.getOp()!=0);
                                break;
                            /* loja is logged */
                            case 4:
                                do{
                                    String lEmail=this.menuLogin.getEmail();
                                    this.menuLoja.executa();
                                    switch(this.menuLoja.getOp()){
                                        case 1: /* Registar encomenda pronta para entrega */
                                            readyToentrega(lEmail);
                                            break;
                                        case 2: /*Consultar histórico de encomendas */
                                            this.menuLoja.sendMessage(String.valueOf(this.model.getLoja(lEmail).getEncomendas_aceites()));
                                            break;
                                        case 3: /*Ver minha informação pessoal*/
                                            this.menuLoja.sendMessage(this.model.getLoja(lEmail).toString());
                                            break;

                                    }
                                }while(this.menuLoja.getOp()!=0);
                                break;
                            /* empresa is logged */
                            case 5:
                                do{
                                    String eEmail = this.menuLogin.getEmail();
                                    this.menuEmpresa.executa();
                                    switch(this.menuEmpresa.getOp()) {
                                        case 1: /* Sinalizar disponibilidade */
                                            disponibilidade(this.menuLogin.getOp(), eEmail);
                                            break;
                                        case 2: /* Registar tempo de entrega de uma encomenda e custo */
                                            tempo_entrega(this.menuLogin.getOp(), eEmail);
                                            break;
                                        case 3: /*Licença para entregar encomendas médicas */
                                            licenca(this.menuLogin.getOp(), eEmail);
                                            break;
                                        case 4: /*Consultar encomendas que transportei*/
                                            this.menuEmpresa.sendMessage(String.valueOf(this.model.getEmpresa(eEmail).getEncomendas_entregues()));
                                            break;
                                        case 5: /*Ver a minha informação pessoal*/
                                            this.menuEmpresa.sendMessage(this.model.getEmpresa(eEmail).toString());
                                            break;
                                    }
                                }while(this.menuEmpresa.getOp()!=0);
                                break;
                            default:
                                break;
                        }
                    } while(this.menuLogin.getOp()!=0);
                    break;
                case 2:
                    /* sign up*/
                    clearScreen();
                    this.signUpMenu.executa();
                    if(this.signUpMenu.getOp() == 0) break;
                    //switch(this.signUpMenu.getOp()){
                        registo(this.signUpMenu.getOp());
                       // case 1:
                            /* new utilizador */
                         //   break;

                    //}
                case 0:
                    mainMenu.sendMessage("\n                                   ＧＯＯＤＢＹＥ\n");
                    break;
                default:
                    mainMenu.sendMessage("Opção inválida.");
                    break;
            }
        } while(this.mainMenu.getOp() != 0);
        clearScreen();
        try {
            this.model.guardaEstado();
        } catch(IOException e) {
            System.out.println("Erro ao salvar dados.");
        }
        System.out.println("Saindo do programa...");
    }


    private void clearScreen() {
        System.out.print('\u000C');
    }

    public void carregaFicheiro(String filename) throws IOException, EmailJaExisteException {
        Parser parser = new Parser(this.model);
        parser.loadData(filename);
    }

    public void admin_load_file(){
        menuAdmin.sendMessage("Indique o nome do ficheiro a ler: ");
        try {
            carregaFicheiro(menuAdmin.leString());
        } catch(IOException | EmailJaExisteException e) {
            System.out.println("Erro na leitura do ficheiro...");
        }
    }

    private void registo(int tipo){
        this.signUpMenu.sendMessage("Email: ");
        String user = this.signUpMenu.leString();
        this.signUpMenu.sendMessage("Password:  ");
        String pwd = this.signUpMenu.leString();

        this.signUpMenu.sendMessage("Nome: ");
        String nome = this.signUpMenu.leString();
        if(tipo==4) {
            Integer nif;
            do {
                this.signUpMenu.sendMessage("NIF: ");
                nif = this.signUpMenu.leInt();
            } while (String.valueOf(nif).length() != 9);
        }
        this.signUpMenu.sendMessage("Morada: ");
        String morada = this.signUpMenu.leString();

    }


    private void fazerEncomenda(String email){

        this.menuUtilizador.sendMessage("Indique a loja onde quer efetuar a sua encomenda: ");
        String res= menuUtilizador.leString();
        for(Loja l: this.model.getMLoja().values()){
            if (l.getNome().equals(res)){
                this.menuUtilizador.sendMessage("Faça a sua encomenda: ");
                String input=menuUtilizador.leString();
                String[] campos = input.split(",");
                ArrayList<Linha_Encomenda> lle = new ArrayList<>();
                for(int i=0 ;i<(campos.length);i=i+4){
                    Linha_Encomenda le = new Linha_Encomenda(campos[i], campos[i+1], Double.parseDouble(campos[i+2]), Double.parseDouble(campos[i+3]));
                    lle.add(le);
                }
                int nEncomenda = this.model.getnEncomendas() +1;
                Encomenda e = new Encomenda("e"+nEncomenda, this.model.getMUtilizador().get(email).getCodUtilizador(),l.getCodLoja(),0.0, lle);
                this.model.setnEncomendas(nEncomenda);
                this.model.getLoja(l.getEmail()).addToQueue(e);
                this.menuUtilizador.sendMessage(e.toString());
                Voluntario v= this.model.temVoluntario(e.getCodLoja(),this.model.getUtilizador(email));
                if (v!=null) {
                    this.menuUtilizador.sendMessage("\nTemos um voluntário para fazer a sua entrega: ");
                    this.menuUtilizador.sendMessage(v.toString());
                    v.getEncomendas_por_sinalizar().add(e);
                }
                else{
                    List<Empresa> empresasDisp=this.model.empresasDisponiveis(e.getCodLoja(),this.model.getUtilizador(email));
                    List<String> empresasDS=this.model.empresasDS(e.getCodLoja(),this.model.getUtilizador(email));

                    Menu auxiliar = new Menu(empresasDS);
                    auxiliar.sendMessage("Escolha a empresa pelo código: ");
                    do {
                        auxiliar.executa();
                        if (auxiliar.getOp() == 0) break;
                        int empresa_index = auxiliar.getOp();
                        auxiliar.sendMessage("\nComfirma a seleção da empresa:\n" + empresasDisp.get(empresa_index - 1));
                        if (auxiliar.leYesNo().equals("y")) {
                                empresasDisp.get(empresa_index - 1).getEncomendas_sinalizar().add(e);
                        }
                    }while (auxiliar.getOp()!= 0);
                }
            }

        }
    }


    private void encomendasEntrega(String email){
        this.model.getUtilizador(email).getEncomendasEntrega().stream().forEach(encomenda -> this.menuVoluntario.sendMessage(encomenda.toString()));
    }

    private void minhasEncomendas(String email){
        for (Encomenda e: this.model.getMEncomenda().values()) if(e.getCodUtilizador().equals(this.model.getUtilizador(email).getCodUtilizador())){
            this.menuUtilizador.sendMessage(String.valueOf(e));
        }
    }

    private void disponibilidade(int op, String email){
        System.out.println("Está disponível para fazer entregas (y/n)");
        if (this.auxiliar.leYesNo().equals("y")) this.model.available(op, email, true);
        else this.model.available(op, email, false);
    }

    public void tempo_entrega(int op, String email){
        Menu auxiliar = new Menu(this.model.encomendas_por_sinalizar(op, email));
        auxiliar.sendMessage("Escolha a encomenda pelo código: ");
        do{
            auxiliar.executa();
            if(auxiliar.getOp()== 0) break;
            int encomenda = auxiliar.getOp();
            auxiliar.sendMessage("Indique o tempo que demorou a entregar a encomenda: ");
            double time = auxiliar.lerDouble();
            if(op == 5){
                auxiliar.sendMessage("Indique o custo associado: ");
                double price = auxiliar.lerDouble();
                this.model.getEmpresa(email).sinalizarEncomenda(encomenda -1, time, price);
            }else{
                this.model.getVoluntario(email).sinalizarEncomenda(encomenda -1, time);
            }
        }while(auxiliar.getOp()!= 0);
    }

    public void readyToentrega(String lEmail){
        Menu auxiliar = new Menu(this.model.toEntrega(lEmail));
        auxiliar.sendMessage("Escolha a encomenda pelo código: ");
            auxiliar.executa();
            if(auxiliar.getOp()!= 0) {
                int encomenda_index= auxiliar.getOp();
                this.model.getLoja(lEmail).addToAceites(encomenda_index - 1);
                auxiliar.sendMessage("\nA encomenda "+ this.model.getLoja(lEmail).getEncomendas_aceites().get(encomenda_index-1).getCodEncomenda() + " está sinalizada para entrega!");
            }
    }

    public void licenca(int op, String email){
        System.out.println("Tem licença para entregas de encomendas médicas? (y/n)");
        if (this.auxiliar.leYesNo().equals("y")) this.model.availableToMed(op, email, true);
        else this.model.availableToMed(op, email, false);
    }

}
