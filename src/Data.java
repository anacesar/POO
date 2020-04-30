import Exceptions.EmailJaExisteException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Data implements Serializable {

    private Map<String, Utilizador> utilizadores; // EMAIL -> utilizador
    private Map<String, Voluntario> voluntarios; // EMAIL -> voluntario
    private Map<String, Empresa> empresas; // EMAIL -> empresa
    private Map<String, Loja> lojas; // EMAIL -> loja
    private int nUtilizadores; //inteiro usado para atribuir código de utilizador
    private int nVoluntarios;
    private int nEmpresas;
    private int nLojas;

    public Data() {
        this.utilizadores = new HashMap<>();
        this.voluntarios = new HashMap<>();
        this.empresas = new HashMap<>();
        this.lojas = new HashMap<>();
        this.nUtilizadores = 0;
        this.nVoluntarios = 0;
        this.nEmpresas = 0;
        this.nLojas = 0;
    }

    public int getnUtilizadores() { return this.nUtilizadores; }

    public void setnUtilizadores(int nUtilizadores) { this.nUtilizadores = nUtilizadores; }

    public int getnVoluntarios() { return this.nVoluntarios; }

    public void setnVoluntarios(int nVoluntarios) { this.nVoluntarios = nVoluntarios; }

    public int getnEmpresas() { return this.nEmpresas; }

    public void setnEmpresas(int nEmpresas) { this.nEmpresas = nEmpresas; }

    public int getnLojas() { return this.nLojas; }

    public void setnLojas(int nLojas) {
        this.nLojas = nLojas;
    }

    public boolean checkLogin(Integer tipo, String email, String password){
        boolean exist;
        switch (tipo){
            case 1:
                exist = this.utilizadores.containsKey(email);
                if(exist && this.utilizadores.get(email).getPassword().equals(password)) exist = true;
                else  exist = false;
                break;
            case 2:
                exist = this.voluntarios.containsKey(email);
                if(exist && this.voluntarios.get(email).getPassword().equals(password)) exist = true;
                else  exist = false;
                break;
            case 3:
                exist = this.empresas.containsKey(email);
                if(exist && this.empresas.get(email).getPassword().equals(password)) exist = true;
                else  exist = false;
                break;
            case 4:
                exist = this.lojas.containsKey(email);
                if(exist && this.lojas.get(email).getPassword().equals(password)) exist = true;
                else  exist = false;
                break;
            default:
                exist = false;
        }
        return exist;
    }

    public void Entidade(Entidade entidade) throws EmailJaExisteException{
        if(entidade instanceof Utilizador && ! utilizadores.containsKey(entidade.getEmail())) utilizadores.put(entidade.getEmail(), (Utilizador) entidade.clone());
    }

    public void addUtilizador(Utilizador utilizador) throws EmailJaExisteException {
        if(! utilizadores.containsKey(utilizador.getEmail())) utilizadores.put(utilizador.getEmail(), utilizador.clone());
        else throw new EmailJaExisteException();
    }

    public void addVoluntario(Voluntario voluntario) throws EmailJaExisteException {
        if(! voluntarios.containsKey(voluntario.getEmail())) voluntarios.put(voluntario.getEmail(), voluntario.clone());
        else throw new EmailJaExisteException();
    }
}
