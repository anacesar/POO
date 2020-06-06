package controller;

import model.*;
import view.Menu;
import view.MenuLogin;
import exceptions.EmailJaExisteException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrazAquiController {
    /* camada lógica */
    private TrazAquiModel model;

    /* Menus da aplicaçãoo - View */
    private Menu mainMenu, signUpMenu, menuAdmin, menuUtilizador, menuVoluntario, menuLoja, menuEmpresa, auxiliar;
    private MenuLogin menuLogin;


    public TrazAquiController(TrazAquiModel model) throws EmailJaExisteException{
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
        String[] loja = {"Ver encomendas prontas para entrega",
                "Consultar histórico de encomendas",
                "Ver minha informação pessoal"};
        String[] empresa = {"Ver encomendas disponíveis para entrega",
                "Registar tempo de entrega de uma encomenda e custo associado",
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
                System.out.println("Erro a careggar ficheiro 'logs.txt'. ");
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
    public void run() throws EmailJaExisteException {
        System.out.println("----------------------TrazAquiApp------------------------");
        System.out.println("nr users: " + model.nUsers());
        System.out.println("nr volu: " + model.nVolu());
        System.out.println("nr lojas: " + model.nLojas());


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
                                            //admin_load_file();
                                            break;
                                        case 2: /* admin wants to save obj */
                                            //this.model.guardaEstado();

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
                                            fazerEncomenda();
                                            break;
                                        case 2: /* Ver encomendas para entrega (encomendas por entregar)*/

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
                                            tempo_entega(this.menuLogin.getOp(), vEmail);
                                            break;
                                    }
                                }while(this.menuVoluntario.getOp()!=0);
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
                    switch(this.signUpMenu.getOp()){
                        case 1:
                            /* new utilizador */

                            break;
                    }
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

    private void fazerEncomenda() throws EmailJaExisteException {

        this.menuUtilizador.sendMessage("Indique a loja onde quer efetuar a sua encomenda: ");
        for(Loja l: this.model.getMLoja().values()){
            if (l.getNome().equals( menuUtilizador.leString())){

                this.menuUtilizador.sendMessage("Faça a sua encomenda: ");
                String input=menuUtilizador.leString();
                String[] campos = input.split(",");
                ArrayList<Linha_Encomenda> lle = new ArrayList<>();
                for(int i=0 ;i<(campos.length);i=i+4){
                    Linha_Encomenda le = new Linha_Encomenda(campos[i], campos[i+1], Double.parseDouble(campos[i+2]), Double.parseDouble(campos[i+3]));
                    lle.add(le);
                }
                this.menuUtilizador.sendMessage("Morada para qual pretende receber a encomenda: ");
                String morada = menuUtilizador.leString();
                Encomenda e = new Encomenda("e"+(this.model.getnEncomendas()+1), this.model.getMUtilizador().get(menuLogin.getEmail()).getCodUtilizador(),l.getCodLoja(),0.0, morada, LocalDate.now(),lle);
                this.model.addEncomenda(e);
                this.model.getLoja(l.getEmail()).getQueue().add(e);
            }
            else this.menuAdmin.sendMessage("Nome de Loja inválido...");
        }




    }

    private void disponibilidade(int op, String email){
        System.out.println("Está disponível para fazer entregas (y/n)");
        if (this.auxiliar.leYesNo().equals("y")) this.model.available(op, email, true);
        else this.model.available(op, email, false);
    }

    public void tempo_entega(int op, String email){
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

}
