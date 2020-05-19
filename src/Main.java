

import Exceptions.EmailJaExisteException;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws EmailJaExisteException, IOException {
        Data data= new Data();
        Scanner scanner = new Scanner(System.in);
        TrazAqui app = new TrazAqui();
        DataSaver ds = new DataSaver();
        Scanner sc = new Scanner(System.in);

        try {ds.deserializeFromXML();
        }
        catch (IOException e) {
            System.out.println("Failed to load Status");
        }



        boolean sair = false;

        while(!sair){
            String op = app.getView().menuInicial();
            switch (op) {
                case "1":
                    app.login();
                    break;
                case "2":
                    app.registo();
                    break;
                case "3":
                    sair = true;
                    app.getView().printMensagem("Obrigado!");
                    ds.serializeToXML(app);
                    break;
                default:
                    app.getView().printMensagem("Opção não encontrada!");
                    break;
            }
        }
    }


}
