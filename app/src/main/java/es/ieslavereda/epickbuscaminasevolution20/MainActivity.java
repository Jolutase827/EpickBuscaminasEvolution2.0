package es.ieslavereda.epickbuscaminasevolution20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import es.ieslavereda.epickbuscaminasevolution20.model.CampoDeMinas;

public class MainActivity extends AppCompatActivity {

    private CampoDeMinas cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cm = findViewById(R.id.contenedorMinas);

    }
}