

import Exceptions.EmailJaExisteException;
import Exceptions.EmailNaoRegistadoException;
import Exceptions.NomeInvalidoException;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws EmailJaExisteException, IOException, EmailNaoRegistadoException, NomeInvalidoException {

        TrazAqui app = new TrazAqui();
        DataSaver ds = new DataSaver();


        try {ds.deserializeFromXML();
        }
        catch (IOException e) {
            System.out.println("Failed to load Status");
        }

        app.carregaDados();

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
                    app.getView().printMensagem("Obrigada!");
                    ds.serializeToXML(app);
                    break;
                default:
                    app.getView().printMensagem("Opção inválida!");
                    break;
            }
        }
    }


}
