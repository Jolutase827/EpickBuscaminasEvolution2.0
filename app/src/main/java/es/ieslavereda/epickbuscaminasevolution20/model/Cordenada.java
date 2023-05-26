package es.ieslavereda.epickbuscaminasevolution20.model;

import androidx.annotation.Nullable;

public class Cordenada {
    private int x;
    private int y;

    public Cordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x+y;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Cordenada){
            Cordenada cordenada = (Cordenada) obj;
            return cordenada.getX() == getX() && cordenada.getY()==getY();
        }
        return false;
    }
}
