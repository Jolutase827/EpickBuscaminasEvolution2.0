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
    private Map<Cordenada,CeldaMina> celdas;
    public CampoDeMinas(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        celdas = new HashMap<>();
        CeldaMina cm;
        TableRow.LayoutParams lp;
        TextView tx;
        TableRow tr;
        for (int i=0; i<22; i++){
            tr = new TableRow(context);
            tr.setPadding(0,7,0,0);
            addView(tr);
            tx=new TextView(context);
            tx.setWidth(2);
            tr.addView(tx);
            for (int j =0;j<15;j++){
                tx=new TextView(context);
                tx.setWidth(7);
                tr.addView(tx);
                cm = new CeldaMina(context,new Cordenada(i,j),this);
                tr.addView(cm);
                celdas.put(cm.getCordenada(),cm);

            }
        }
    }
    private void ponerBombas(int numeroBombas){
        int i;
        int j;
        double probabilidad = (numeroBombas<150)?0.39:0.40;
        do {
            i=0;
            do {
                j=0;
                do {
                    if (Math.random()<probabilidad)
                        getCelda(i,j).setMina(true);
                }while (numeroBombas>0||j<15);
            }while (numeroBombas>0||i<22);
        } while (numeroBombas>0);


    }

    private CeldaMina getCelda(int x, int y){
        return celdas.get(new Cordenada(x,y));
    }

}
