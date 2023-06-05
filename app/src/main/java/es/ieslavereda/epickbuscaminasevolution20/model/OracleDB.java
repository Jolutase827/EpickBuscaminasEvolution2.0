package es.ieslavereda.epickbuscaminasevolution20.model;



import java.util.List;

import es.ieslavereda.epickbuscaminasevolution20.API.Connector;

public class OracleDB {



    public List<PartidaBuscaminas> getAllPartidas(){
         return Connector.getConector().getAsList(PartidaBuscaminas.class,"buscaminas");
    }

    public PartidaBuscaminas addUser(PartidaBuscaminas partidaBuscaminas){
        partidaBuscaminas.setNumero(null);
        return Connector.getConector().post(PartidaBuscaminas.class,partidaBuscaminas,"buscaminas");
    }
}
