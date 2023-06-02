package es.ieslavereda.epickbuscaminasevolution20.model;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import es.ieslavereda.epickbuscaminasevolution20.R;

public class CeldaMina extends AppCompatImageButton {
    private Cordenada cordenada;
    private CampoDeMinas campoDeMinas;
    private boolean marcada;
    private boolean mina;

    private boolean clicada;

    private boolean flag;

    @SuppressLint("ResourceAsColor")
    public CeldaMina(Context context, Cordenada cordenada, CampoDeMinas cm) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widh = displayMetrics.widthPixels;
        marcada = false;
        this.campoDeMinas = cm;
        this.cordenada = cordenada;
        this.mina = false;
        setMaxHeight((widh/15)-8);
        setMinimumHeight((widh/15)-8);
        setMaxWidth((widh/15)-8);
        setMinimumWidth((widh/15)-8);
        setBackgroundColor(getResources().getColor(R.color.celda_no_marcada,context.getTheme()));
        setPadding(0,0,0,0);
        setScaleType(ScaleType.FIT_CENTER);
        setAdjustViewBounds(true);
        flag = false;
        clicada = false;

    }

    @SuppressLint("ResourceAsColor")
    public void mostrarContenido(){
        if (isMina()){
            setImageResource(R.mipmap.mina_foreground);
            setBackgroundColor(getResources().getColor(R.color.celda_mal,this.getContext().getTheme()));
            clicada = true;
        }
    }
    public Cordenada getCordenada() {
        return cordenada;
    }

    public void setCordenada(Cordenada cordenada) {
        this.cordenada = cordenada;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    @Override
    public String toString() {
        return mina +"";
    }

    public boolean isClicada() {
        return clicada;
    }

    public void setClicada(boolean clicada) {
        this.clicada = clicada;
    }

    public void putFlag() {
        if (!flag) {
            setImageResource(R.mipmap.bandera_foreground);
            flag = true;
            clicada = true;
        }else {
            setImageResource(0);
            flag = false;
            clicada = false;
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void ponerNumero(int i){
        switch (i){
            case (1):
                setImageResource(R.mipmap.uno_foreground);
                break;
            case (2):
                setImageResource(R.mipmap.dos_foreground);
                break;
            case (3):
                setImageResource(R.mipmap.tres_foreground);
                break;
            case (4):
                setImageResource(R.mipmap.cuatro_foreground);
                break;
            case (5):
                setImageResource(R.mipmap.cinco_foreground);
                break;
            case (6):
                setImageResource(R.mipmap.seis_foreground);
                break;
            case (7):
                setImageResource(R.mipmap.siete_foreground);
                break;
            case (8):
                setImageResource(R.mipmap.ocho_foreground);
                break;
            default:
                break;
        }
        clicada = true;
    }
}
