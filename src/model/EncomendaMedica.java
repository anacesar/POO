package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class EncomendaMedica extends Encomenda {

    public EncomendaMedica(){
        super();
    }

    public EncomendaMedica(String codEncomenda, String codUtilizador, String codLoja, double peso, LocalDate data, ArrayList<Linha_Encomenda> linhas) {
        super(codEncomenda,codUtilizador,codLoja,peso,data,linhas);
    }
}
