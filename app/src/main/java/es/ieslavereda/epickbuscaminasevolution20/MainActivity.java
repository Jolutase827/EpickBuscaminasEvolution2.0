package es.ieslavereda.epickbuscaminasevolution20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ieslavereda.epickbuscaminasevolution20.base.BaseActivity;
import es.ieslavereda.epickbuscaminasevolution20.base.CallInterface;
import es.ieslavereda.epickbuscaminasevolution20.model.CampoDeMinas;
import es.ieslavereda.epickbuscaminasevolution20.model.CeldaMina;
import es.ieslavereda.epickbuscaminasevolution20.model.Model;
import es.ieslavereda.epickbuscaminasevolution20.model.PartidaBuscaminas;

public class MainActivity extends BaseActivity implements View.OnClickListener,CallInterface {

    private CampoDeMinas cm;
    private TextView contador, numero_banderas,numeroBombas,numeroBomasIzquierda,numeroBombasDerecha;
    private Button start,cancelar, empezar;
    private ImageButton partidas;

    private FloatingActionButton mas,menos;
    private Switch aSwitch;

    private int seconds = 0;
    private boolean isRunning = false;
    private boolean flagOrShow;
    private int numeroDeMinas;
    private int numeroDebanderasPuestas;

    private ConstraintLayout cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cm = findViewById(R.id.contenedorMinas);
        partidas = findViewById(R.id.partidas);
        contador = findViewById(R.id.contador);
        numero_banderas = findViewById(R.id.numero_banderas);
        start = findViewById(R.id.start);
        aSwitch = findViewById(R.id.opcion);
        cl = findViewById(R.id.opciones_bombas);
        cancelar = findViewById(R.id.cancelar);
        empezar = findViewById(R.id.empezar);
        mas = findViewById(R.id.masseleccion);
        menos = findViewById(R.id.menosseleccion);
        numeroBombas = findViewById(R.id.numerodebombasSeleccion);
        numeroBomasIzquierda = findViewById(R.id.numero_menos_uno);
        numeroBombasDerecha = findViewById(R.id.numero_masuno);
        numeroDeMinas = 150;
        numeroDebanderasPuestas =0;
        flagOrShow = true;
        numero_banderas.setText(""+(numeroDeMinas-numeroDebanderasPuestas));
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    aSwitch.setText("Bandera");
                    flagOrShow = false;
                }else {
                    aSwitch.setText("Descubrir");
                    flagOrShow = true;
                }
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cl.setVisibility(View.VISIBLE);
                if (numeroDeMinas<200) {
                    numeroBombasDerecha.setText((numeroDeMinas + 1)+"");
                }
                if (numeroDeMinas>100){
                    numeroBomasIzquierda.setText((numeroDeMinas-1)+"");
                }
                numeroBombas.setText(""+numeroDeMinas);
                stopTimer();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cl.setVisibility(View.GONE);
                startTimer();
            }
        });
        partidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Partidas.class);
                startActivity(i);
            }
        });
        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (empezar.getText().equals("Reiniciar y guardar")){
                    PartidaBuscaminas partidaBuscaminas = new PartidaBuscaminas(null,contador.getText()+"",(cm.ganado())?"VICTORIA":"DERROTA",cm.getMinasDetectadas());
                    showProgress();
                    executeCall(new CallInterface() {
                        @Override
                        public void doInBackground() {
                            Model.getInstance().insertPartida(partidaBuscaminas);
                        }

                        @Override
                        public void doInUI() {
                            hideProgress();
                        }
                    });
                }
                cm.reinicio();
                cm.ponerBombas(numeroDeMinas);
                start.setText("Reiniciar");
                cl.setVisibility(View.GONE);
                cm.setVisibility(View.VISIBLE);
                numero_banderas.setText(""+(numeroDeMinas-numeroDebanderasPuestas));
                empezar.setText("Reiniciar y guardar");
                seconds =0;
                contador.setText("00:00");
                startTimer();
            }
        });
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeroDeMinas<200){
                    numeroDeMinas++;
                }
                numeroBombasDerecha.setText((numeroDeMinas + 1)+"");
                numeroBombas.setText(""+numeroDeMinas);
                numeroBomasIzquierda.setText((numeroDeMinas-1)+"");
                if (numeroDeMinas ==  200){
                    numeroBombasDerecha.setText("");
                }
            }
        });
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeroDeMinas>100){
                    numeroDeMinas--;
                }
                numeroBombasDerecha.setText((numeroDeMinas + 1)+"");
                numeroBombas.setText(""+numeroDeMinas);
                numeroBomasIzquierda.setText((numeroDeMinas-1)+"");
                if (numeroDeMinas ==  100){
                    numeroBomasIzquierda.setText("");
                }
            }
        });

        cm.setOnClickListener(this);
        cl.setVisibility(View.GONE);

        executeCall(this);
    }


    @Override
    public void onClick(View view) {
        CeldaMina cdm = (CeldaMina) view;
        if (flagOrShow&&!cdm.isClicada()) {
            if (cdm.isMina()) {
                cm.showMinas();
                stopTimer();
            } else {
                cm.mostrarMinasCercanas(cdm.getCordenada());
            }
        }else if (!flagOrShow){
            if (numeroDebanderasPuestas<numeroDeMinas) {
                if (cdm.isFlag()){
                    numeroDebanderasPuestas--;
                }else {
                    numeroDebanderasPuestas++;
                }
                cdm.putFlag();
                numero_banderas.setText(""+(numeroDeMinas-numeroDebanderasPuestas));
            }
        }
        if (cm.ganado()){
            startTimer();
        }
    }

    private void startTimer() {
        isRunning = true;

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (isRunning) {
                        Thread.sleep(1000);
                        seconds++;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTimerText();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    private void stopTimer() {
        isRunning = false;
    }

    private void updateTimerText() {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        String timerText = String.format("%02d:%02d", minutes, remainingSeconds);
        contador.setText(timerText);
    }

    @Override
    public void doInBackground() {
        Model.getInstance().getPartidaBuscaminas();
    }

    @Override
    public void doInUI() {
        hideProgress();
    }
}