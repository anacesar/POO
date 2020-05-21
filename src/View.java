import java.util.Scanner;

public class View{

    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public String menuInicial() {
        System.out.println(" -------- Traz Aqui! --------");
        System.out.println("| 1 - LOGIN                  |");
        System.out.println("| 2 - REGISTO                |");
        System.out.println("| 3 - SAIR                   |");
        System.out.println(" ---------------------------- ");
        return this.scanner.nextLine();
    }

    public String menuVoluntário() {
        System.out.println(" -------- Área Voluntário --------");
        System.out.println("| 1 - Recolha de encomenda        |");
        System.out.println("| 2 - Entrega de encomenda        |");
        System.out.println("| 3 - SAIR                        |");
        System.out.println(" --------------------------------- ");
        return this.scanner.nextLine();
    }

    public String menuUtilizador() {
        System.out.println(" -------- Área Utilizador --------");
        System.out.println("| 1 - Fazer encomenda             |");
        System.out.println("| 2 - As minhas encomendas        |");
        System.out.println("| 3 - SAIR                        |");
        System.out.println(" --------------------------------- ");
        return this.scanner.nextLine();

    }

    public String menuTransportadora() {
        System.out.println(" -------- Área Transportadora -------");
        System.out.println("| 1 - Recolha de encomenda           |");
        System.out.println("| 2 - Registo de entrega             |");
        System.out.println("| 3 - SAIR                           |");
        System.out.println(" ------------------------------------ ");
        return this.scanner.nextLine();
    }

    public String menuLoja() {
        System.out.println(" ------------ Área Loja -------------");
        System.out.println("| 1 - Sinalizar encomenda            |");
        System.out.println("| 2 - Atualizar fila                 |");
        System.out.println("| 3 - SAIR                           |");
        System.out.println(" ------------------------------------ ");
        return this.scanner.nextLine();

    }

    public String SelecionaUser() {
        System.out.println(" -------------------------------------");
        System.out.println("| Selecione uma opção:                |");
        System.out.println("|   1 - Utilizador                    |");
        System.out.println("|   2 - Voluntário                    |");
        System.out.println("|   3 - Empresa Transportadora        |");
        System.out.println(" ------------------------------------- ");
        return this.scanner.nextLine();
    }



    public void printMensagem(String m) {
        System.out.println(m);
    }

    public String getString() {
        return this.scanner.nextLine();
    }

    public Integer getInteger() {
        return this.scanner.nextInt();
    }

    public Double getDouble() {
        return this.scanner.nextDouble();
    }
}