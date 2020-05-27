import java.io.Serializable;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

public class Menu implements Serializable {
    private List<String> opcoes;
    private int op;
    private int nopcoes;
    private static Scanner sc = new Scanner(System.in);

    public Menu(String[] opcoes){
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
        this.nopcoes = this.opcoes.size();
    }

    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    public void showMenu(){
        System.out.println("\n Selecionar opção:\n");
        for (int i=0; i<this.nopcoes; i++){
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }

    public int lerOpcao(){
        int op;
        Scanner sc = new Scanner(System.in);
        out.print("Opção: ");
        try {
            op = sc.nextInt();
        }
        catch (InputMismatchException e){
            op = -1;
        }

        if (op < 0 || op > this.opcoes.size()){
            out.println("Opção inválida!!!");
            op = -1;
        }
        return op;
    }

    public String lerTipo(){
        String op;
        Scanner sc = new Scanner(System.in);
        try{
            op = sc.nextLine();
            if(op.equals("Electrico") || op.equals("Hibrido") || op.equals("Gasolina")) {
                return op;
            }
            else {
                op = null;
            }
        }
        catch (InputMismatchException e) {
            out.println("Não é um tipo válido.");
            op = null;
        }

        return op;
    }

    public int getOp(){
        return this.op;
    }

    public double lerDouble(){
        double op = -1;
        Scanner sc = new Scanner(System.in);
        //out.print("Input: ");
        try {
            op = sc.nextDouble();
        }
        catch (InputMismatchException e){
            out.println("Não foi um double");
        }
        return op;
    }

    public GPS lerLocalizacao(){
        double cx,cy;
        Scanner sc = new Scanner(System.in);
        out.println("Inserir (cx , cy): ");
        try{
            out.print("Componente cx: ");
            cx = sc.nextDouble();
        }
        catch (InputMismatchException e){
            out.println("A componente X não é um double.");
            return null;
        }
        try {
            out.print("Componente cy: ");
            cy = sc.nextDouble();
        }
        catch (InputMismatchException e){
            out.println("A componente Y não é um double.");
            return null;
        }
        return new GPS(cx,cy);
    }


    public String leString(){
        String op = null;
        Scanner sc = new Scanner(System.in);
        try {
            op = sc.nextLine();
        }
        catch(InputMismatchException e) {
            out.println("Não foi uma String válida.");
            op = null;
        }
        return op;
    }

    public int leInt(){
        int op;
        Scanner sc = new Scanner(System.in);
        try{
            op = sc.nextInt();
            if(op<0) {
                op = -1;
            }
        }
        catch(InputMismatchException e){
            out.println("Não é um número inteiro válido.");
            op = -1;
        }
        return op;
    }

    public String leYesNo(){
        String op;
        Scanner sc = new Scanner(System.in);
        try{
            op = sc.nextLine();
            if(op.equals("y") || op.equals("n")) {
                return op;
            }
            else {
                op = null;
            }
        }
        catch (InputMismatchException e) {
            out.println("Não é uma escolha válida.");
            op = null;
        }
        return op;
    }

    public void setOp(int op){ this.op = op; }
}
