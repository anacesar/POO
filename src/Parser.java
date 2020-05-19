import Exceptions.EmailJaExisteException;
import Exceptions.NomeInvalidoException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Parser {
    private Data data;

    public Parser(Data data){
        this.data = data;
    }

    public void parse() throws EmailJaExisteException, NomeInvalidoException {
        List<String> linhas = lerFicheiro("logs_dados.txt"); //alterar nome do ficheiro
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Utilizador":
                    Utilizador u = parseUtilizador(linhaPartida[1]); // criar um Utilizador
                    if(checkUtilizador(u)) data.addUtilizador(u);
                   // System.out.println(u.toString()); //enviar para o ecrÃ¡n apenas para teste
                    break;
                case "Voluntario":
                    Voluntario v = parseVoluntario(linhaPartida[1]);
                    if(checkVoluntario(v)) data.addVoluntario(v);
                   // System.out.println(v.toString()); //enviar para o ecrÃ¡n apenas para teste
                case "Loja":
                    Loja l = parseLoja(linhaPartida[1]);
                    System.out.println(l.toString());
                    break;
                case "Encomenda":
                    Encomenda e = parseEncomenda(linhaPartida[1]);
                   // System.out.println(e.toString());
                    break;
                case "Aceite":
                  //  if(checkEncomendaAceite(linhaPartida[1])) data.add(u);
                default:
                    System.out.println("Linha inválida.");
                    break;
            }

        }
        System.out.println("Dados Carregados!");
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

    public boolean checkVoluntario(Voluntario voluntario) throws NomeInvalidoException {
        boolean valid = true;

        String codVoluntario = voluntario.getCodVoluntario();
        if(! (codVoluntario.charAt(0) == 'v')) valid = false;
        int nr =  Integer.parseInt(codVoluntario.substring(1));
        if(this.data.getnVoluntarios() < nr) data.setnVoluntarios(++nr);

        if (voluntario.getNome().matches("[0-9]+")) throw new NomeInvalidoException();
        return valid;
    }


    public Voluntario parseVoluntario(String input){
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

    public Encomenda parseEncomenda(String input){
        String[] campos = input.split(",");
        String codEncomenda = campos[0];
        int nr = Integer.parseInt(codEncomenda.substring(1));
        if(this.data.getnEncomendas() < nr) data.setnEncomendas(++nr);
        String codUtilizador = campos[1];
        String codLoja = campos[2];
        double peso = Double.parseDouble(campos[3]);

       ArrayList<Linha_Encomenda> lle = new ArrayList<>();
       for(int i=4 ;i<(campos.length);i=i+4){
           Linha_Encomenda le = new Linha_Encomenda(campos[i], campos[i+1], Double.parseDouble(campos[i+2]), Double.parseDouble(campos[i+3]));
           lle.add(le);

       }

        return new Encomenda( codEncomenda, codUtilizador, codLoja, peso, LocalDate.now() , lle);
    }



    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { System.out.println(exc.getMessage()); }
        return lines;
    }

}
