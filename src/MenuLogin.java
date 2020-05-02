import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLogin extends Menu implements Serializable {
    private String email;
    private String password;

    public MenuLogin(String[] opcoes) {
        super(opcoes);
        this.email = "";
        this.password = "";
    }

    public void readParametros() {
        System.out.print("E-mail: ");
        this.email = leString();
        System.out.print("Password: ");
        this.password = leString();
    }

    public void executaLogin(){
        int op;
        showMenu();
        do{
            op = lerOpcao();
        } while(op<0 || op>4);
        this.setOp(op);
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
