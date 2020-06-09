package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class EncomendaMedica extends Encomenda {

    public EncomendaMedica(){
        super();
    }

    public EncomendaMedica(String codEncomenda, String codUtilizador, String codLoja, double peso, ArrayList<Linha_Encomenda> linhas) {
        super(codEncomenda,codUtilizador,codLoja,peso,linhas);
    }
}
