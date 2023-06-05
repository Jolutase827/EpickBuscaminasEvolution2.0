package es.ieslavereda.epickbuscaminasevolution20.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import es.ieslavereda.epickbuscaminasevolution20.R;

public class CampoDeMinas extends TableLayout {
    private Map<Cordenada, CeldaMina> celdas;

    public CampoDeMinas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        celdas = new HashMap<>();
        CeldaMina cm;
        TableRow.LayoutParams lp;
        TextView tx;
        TableRow tr;
        for (int i = 0; i < 22; i++) {
            tr = new TableRow(context);
            tr.setPadding(0, 7, 0, 0);
            addView(tr);
            tx = new TextView(context);
            tx.setWidth(2);
            tr.addView(tx);
            for (int j = 0; j < 15; j++) {
                tx = new TextView(context);
                tx.setWidth(7);
                tr.addView(tx);
                cm = new CeldaMina(context, new Cordenada(i, j), this);
                tr.addView(cm);
                celdas.put(cm.getCordenada(), cm);

            }
        }
    }

    public void ponerBombas(int numeroBombas) {
        int i;
        int j;
        double probabilidad = (numeroBombas < 150) ? 0.39 : 0.40;
        do {
            i = 0;
            do {
                j = 0;
                do {
                    if (Math.random() < probabilidad) {
                        getCelda(i, j).setMina(true);
                        numeroBombas--;
                    }
                    j++;
                } while (numeroBombas > 0 && j < 15);
                i++;
            } while (numeroBombas > 0 && i < 22);
        } while (numeroBombas > 0);


    }

    public void setOnClickListener(OnClickListener onClickListener) {
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 15; j++) {
                getCelda(i, j).setOnClickListener(onClickListener);
            }
        }
    }

    public void showMinas() {
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 15; j++) {
                if (getCelda(i, j).isMina()) {
                    getCelda(i, j).mostrarContenido();
                }
            }
        }
    }


    private CeldaMina getCelda(int x, int y) {
        return celdas.get(new Cordenada(x, y));
    }

    public void mostrarMinasCercanas(Cordenada cordenada) {
        int cont = 0;
        int x = cordenada.getX();
        boolean encontradamina = false;
        int y = cordenada.getY();
        if (getCelda(x, y) != null) {
            if (!getCelda(x, y).isClicada()) {
                if (getCelda(x + 1, y + 1) != null) {
                    if (getCelda(x + 1, y + 1).isMina()) {
                        cont++;
                        encontradamina = true;
                    }
                }
                if (getCelda(x + 1, y - 1) != null) {
                    if (getCelda(x + 1, y - 1).isMina()) {
                        cont++;
                        encontradamina = true;
                    }
                }
                if (getCelda(x - 1, y + 1) != null) {
                    if (getCelda(x - 1, y + 1).isMina()) {
                        cont++;
                        encontradamina = true;
                    }
                }
                if (getCelda(x, y + 1) != null) {
                    if (getCelda(x, y + 1).isMina()) {
                        cont++;
                        encontradamina = true;
                    }
                }
                if (getCelda(x + 1, y) != null) {
                    if (getCelda(x + 1, y).isMina()) {
                        cont++;
                        encontradamina = true;
                    }
                }
                if (getCelda(x - 1, y - 1) != null) {
                    if (getCelda(x - 1, y - 1).isMina()) {
                        cont++;
                        encontradamina = true;
                    }
                }
                if (getCelda(x - 1, y) != null) {
                    if (getCelda(x - 1, y).isMina()) {
                        cont++;
                        encontradamina = true;
                    }
                }
                if (getCelda(x, y - 1) != null) {
                    if (getCelda(x, y - 1).isMina()) {
                        cont++;
                        encontradamina = true;
                    }
                }
                if (encontradamina) {
                    getCelda(x, y).ponerNumero(cont);
                } else {
                    getCelda(x, y).setClicada(true);
                    propagar(x, y);
                }
                getCelda(x, y).setBackgroundColor(getResources().getColor(R.color.descubierto,getContext().getTheme()));
            }
        }
    }
    private void propagar(int x, int y){
        if (getCelda(x + 1, y + 1) != null) {
            if (!getCelda(x + 1, y + 1).isClicada())
                mostrarMinasCercanas(new Cordenada(x + 1, y + 1));
        }
        if (getCelda(x + 1, y - 1) != null) {
            if (!getCelda(x + 1, y - 1).isClicada())
                mostrarMinasCercanas(new Cordenada(x + 1, y - 1));
        }
        if (getCelda(x - 1, y + 1) != null) {
            if (!getCelda(x - 1, y + 1).isClicada())
                mostrarMinasCercanas(new Cordenada(x - 1, y + 1));
        }
        if (getCelda(x, y + 1) != null) {
            if (!getCelda(x, y + 1).isClicada())
                mostrarMinasCercanas(new Cordenada(x, y + 1));
        }
        if (getCelda(x + 1, y) != null) {
            if (!getCelda(x + 1, y).isClicada())
                mostrarMinasCercanas(new Cordenada(x + 1, y));
        }
        if (getCelda(x - 1, y - 1) != null) {
            if (!getCelda(x - 1, y - 1).isClicada())
                mostrarMinasCercanas(new Cordenada(x - 1, y - 1));
        }
        if (getCelda(x, y - 1) != null) {
            if (!getCelda(x, y - 1).isClicada())
                mostrarMinasCercanas(new Cordenada(x, y - 1));
        }
        if (getCelda(x - 1, y) != null) {
            if (!getCelda(x - 1, y).isClicada())
                mostrarMinasCercanas(new Cordenada(x - 1, y));
        }
    }

    public boolean ganado(){
        for (int i = 0;i<22;i++){
            for (int j = 0; j<15;j++){
                if (!(getCelda(i,j).isFlag()&&getCelda(i,j).isMina())){
                    return false;
                }
            }
        }
        return true;
    }

    public void reinicio(){
        CeldaMina cm;
        for (int i = 0;i<22;i++){
            for (int j = 0; j<15;j++){
               cm = getCelda(i,j);
               cm.setFlag(false);
               cm.setMina(false);
               cm.setClicada(false);
               cm.setImageResource(0);
               cm.setBackgroundColor(getResources().getColor(R.color.celda_no_marcada,getContext().getTheme()));
            }
        }
    }

    public int getMinasDetectadas(){
        int salida =0;
        for (int i =0;i<22;i++){
            for (int j = 0; j<15;j++){
                if (getCelda(i,j).isMina()&&getCelda(i,j).isFlag())
                    salida++;
            }
        }
        return salida;
    }
}
