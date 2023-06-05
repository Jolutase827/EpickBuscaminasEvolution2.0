package es.ieslavereda.epickbuscaminasevolution20.model;

public class PartidaBuscaminas {
    private Integer numero;
    private String tiempo;
    private String resultado;
    private Integer m_detectadas;

    public PartidaBuscaminas(Integer numero, String tiempo, String resultado, Integer m_detectadas) {
        this.numero = numero;
        this.tiempo = tiempo;
        this.resultado = resultado;
        this.m_detectadas = m_detectadas;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Integer getM_detectadas() {
        return m_detectadas;
    }

    public void setM_detectadas(Integer m_detectadas) {
        this.m_detectadas = m_detectadas;
    }
}
