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
        setBackgroundColor(R.color.teal_200);
        setPadding(0,0,0,0);

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


}
