import Exceptions.EmailJaExisteException;
import Exceptions.NomeInvalidoException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Data data;

    public Parser(Data data){
        this.data = data;
    }

    public void parse() throws EmailJaExisteException, NomeInvalidoException {
        List<String> linhas = lerFicheiro("LogsGerados.csv"); //alterar nome do ficheiro
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Utilizador":
                    Utilizador u = parseUtilizador(linhaPartida[1]); // criar um Utilizador
                    if(checkUtilizador(u)) data.addUtilizador(u);
                    System.out.println(u.toString()); //enviar para o ecrÃ¡n apenas para teste
                    break;
                case "Voluntario":
                    Voluntario v = parseVoluntaro(linhaPartida[1]);
                    System.out.println(v.toString()); //enviar para o ecrÃ¡n apenas para teste
                case "Loja":
                    Loja l = parseLoja(linhaPartida[1]);
                    System.out.println(l.toString());
                    break;
                default:
                    System.out.println("Linha inválida.");
                    break;
            }

        }
        System.out.println("done!");
    }

    public Utilizador parseUtilizador(String input){
        String[] campos = input.split(",");
        String codUtilizador = campos[0];
        String nome = campos[1];
        double gpsx = Double.parseDouble(campos[2]);
        double gpsy = Double.parseDouble(campos[3]);
        return new Utilizador(codUtilizador, nome, new GPS(gpsx, gpsy));
    }

    public boolean checkUtilizador(Utilizador utilizador) throws NomeInvalidoException {
        boolean valid = true;

        String codUtilizador = utilizador.getCodUtilizador();
        if(! (codUtilizador.charAt(0) == 'u')) valid = false;
        int nr =  Integer.parseInt(codUtilizador.substring(1));
        if(this.data.getnUtilizadores() < nr) data.setnUtilizadores(++nr);

        if (utilizador.getNome().matches("[0-9]+")) throw new NomeInvalidoException(); // o nome de uma pessoa nao pode ter numeros
        return valid;
    }

    public Voluntario parseVoluntaro(String input){
        String[] campos = input.split(",");
        String codVoluntario = campos[0];
        int nr =  Integer.parseInt(codVoluntario.substring(1));
        if(this.data.getnVoluntarios() < nr) data.setnVoluntarios(++nr);
        String nome = campos[1];
        double gpsx = Double.parseDouble(campos[2]);
        double gpsy = Double.parseDouble(campos[3]);
        double raio = Double.parseDouble(campos[4]);
        return new Voluntario(codVoluntario, nome, new GPS(gpsx, gpsy), raio);
    }

    public Loja parseLoja(String input){
        String[] campos = input.split(",");
        String codLoja = campos[0];
        int nr = Integer.parseInt(codLoja.substring(1));
        if(this.data.getnLojas() < nr) data.setnLojas(++nr);
        String nomeLoja = campos[1];
        double gpsx = Double.parseDouble(campos[2]);
        double gpsy = Double.parseDouble(campos[3]);
        return new Loja(codLoja, nomeLoja, new GPS(gpsx,gpsy));
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { System.out.println(exc.getMessage()); }
        return lines;
    }

}
