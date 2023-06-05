package es.ieslavereda.epickbuscaminasevolution20.model;

import es.ieslavereda.epickbuscaminasevolution20.model.OracleDB;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Model model;
    private List<PartidaBuscaminas> partidaBuscaminas;

    private Model() {
        partidaBuscaminas = new ArrayList<>();
    }


    public static Model getInstance() {
        if (model == null)
            model = new Model();

        return model;
    }



    public List<PartidaBuscaminas> getPartidaBuscaminas(){
        OracleDB oracleDB = new OracleDB();
        if (partidaBuscaminas.isEmpty())
            partidaBuscaminas = oracleDB.getAllPartidas();
        return partidaBuscaminas;
    }

    public int insertPartida(PartidaBuscaminas partidaBuscaminas) {
        OracleDB oracleDB = new OracleDB();
        PartidaBuscaminas u = oracleDB.addUser(partidaBuscaminas);
        this.partidaBuscaminas.add(u);
        return u!=null?1:0;
    }

}
