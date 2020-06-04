package model;

import exceptions.EmailJaExisteException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TrazAquiModel implements Serializable{
    private Utilizador admin;
    private Map<String, Utilizador> utilizadores; // email -> utilizador
    private Map<String, Voluntario> voluntarios; // email -> voluntario
    private Map<String, Empresa> empresas; // email -> empresa
    private Map<String, Loja> lojas; // email -> loja
    private Map<String, Encomenda> encomendas; //// codEncomenda -> encomenda

    private Set<Entidade> disponiveis;

    private int nUtilizadores; //inteiro usado para atribuir código de utilizador
    private int nVoluntarios;
    private int nEmpresas;
    private int nLojas;
    private int nEncomendas;

    public TrazAquiModel(){
        this.admin = new Utilizador("admin@uminho.pt", "admin", "Admin", null, 0);
        this.utilizadores = new HashMap<>();
        this.voluntarios = new HashMap<>();
        this.empresas = new HashMap<>();
        this.lojas = new HashMap<>();
        this.encomendas = new HashMap<>();
        this.nUtilizadores = 0;
        this.nVoluntarios = 0;
        this.nEmpresas = 0;
        this.nLojas = 0;
    }

    public int getnUtilizadores() {
        return this.nUtilizadores;
    }

    public void setnUtilizadores(int nUtilizadores) {
        this.nUtilizadores = nUtilizadores;
    }

    public int getnVoluntarios() {
        return this.nVoluntarios;
    }

    public void setnVoluntarios(int nVoluntarios) {
        this.nVoluntarios = nVoluntarios;
    }

    public int getnEmpresas() {
        return this.nEmpresas;
    }

    public void setnEmpresas(int nEmpresas) {
        this.nEmpresas = nEmpresas;
    }

    public int getnLojas() {
        return this.nLojas;
    }

    public void setnLojas(int nLojas) {
        this.nLojas = nLojas;
    }

    public int getnEncomendas() {
        return this.nEncomendas;
    }

    public void setnEncomendas(int nEncomendas) {
        this.nEncomendas = nEncomendas;
    }

    public void guardaEstado() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("estado.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public static TrazAquiModel carregaEstado() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("estado.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TrazAquiModel new_model = (TrazAquiModel) ois.readObject();
        ois.close();
        return new_model;
    }

    public boolean checkLogin(Integer tipo, String email, String password){
        boolean valid = false;
        switch (tipo) {
            case 1:
                if(this.admin.getEmail().equals(email) && this.admin.getPassword().equals(password)) valid = true;
                break;
            case 2:
                if(this.utilizadores.containsKey(email) && this.utilizadores.get(email).getPassword().equals(password)) valid = true;
                break;
            case 3:
                if(this.voluntarios.containsKey(email) && this.voluntarios.get(email).getPassword().equals(password)) valid = true;
                break;
            case 4:
                if(this.empresas.containsKey(email) && this.empresas.get(email).getPassword().equals(password)) valid = true;
                break;
            case 5:
                if(this.lojas.containsKey(email) && this.lojas.get(email).getPassword().equals(password)) valid = true;
                break;
            default:
                break;
        }
        return valid;
    }



    public void addUtilizador(Utilizador utilizador) throws EmailJaExisteException {
        if(! utilizadores.containsKey(utilizador.getEmail())) utilizadores.put(utilizador.getEmail(), utilizador.clone());
        else throw new EmailJaExisteException();
    }

    public void addVoluntario(Voluntario voluntario) throws EmailJaExisteException {
        if(! voluntarios.containsKey(voluntario.getEmail())) voluntarios.put(voluntario.getEmail(), voluntario.clone());
        else throw new EmailJaExisteException();
    }

    public void addEmpresa(Empresa empresa) throws EmailJaExisteException {
        if(! empresas.containsKey(empresa.getEmail())) empresas.put(empresa.getEmail(), empresa.clone());
        else throw new EmailJaExisteException();
    }

    public void addLoja(Loja loja) throws EmailJaExisteException {
        if(! lojas.containsKey(loja.getEmail())) lojas.put(loja.getEmail(), loja.clone());
        else throw new EmailJaExisteException();
    }

    public void addEncomenda(Encomenda encomenda) throws EmailJaExisteException {
        if(! encomendas.containsKey(encomenda.getCodEncomenda())) encomendas.put(encomenda.getCodEncomenda(), encomenda.clone());
        else throw new EmailJaExisteException();
    }

    public void available(int tipo, String email, boolean state){
        if(tipo == 3) this.voluntarios.get(email).setDisponivel(state);
        else this.empresas.get(email).setDisponivel(state);
    }

    public List<String> encomendas_por_sinalizar(int tipo, String email){
        if(tipo == 3) return this.voluntarios.get(email).getEncomendas_por_sinalizar().stream().map(Encomenda::getCodEncomenda).collect(Collectors.toList());
        else return this.empresas.get(email).getEncomendas_sinalizar().stream().map(Encomenda::getCodEncomenda).collect(Collectors.toList());
    }

    public Utilizador getUtilizador(String email){ return this.utilizadores.get(email); }

    public Voluntario getVoluntario(String email){ return this.voluntarios.get(email); }

    public Loja getLoja(String email){ return this.lojas.get(email); }

    public Empresa getEmpresa(String email){ return this.empresas.get(email); }

    public Encomenda getEncomenda(String cod) {return this.encomendas.get(cod);}


    public int nUsers() {
        return utilizadores.size();
    }

    public int nVolu() {
        return voluntarios.size();
    }

    public int nLojas() {
        return lojas.size();
    }
}
