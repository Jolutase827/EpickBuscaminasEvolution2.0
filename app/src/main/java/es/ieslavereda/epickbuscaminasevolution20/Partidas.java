package es.ieslavereda.epickbuscaminasevolution20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import es.ieslavereda.epickbuscaminasevolution20.base.BaseActivity;
import es.ieslavereda.epickbuscaminasevolution20.base.CallInterface;
import es.ieslavereda.epickbuscaminasevolution20.model.Model;
import es.ieslavereda.epickbuscaminasevolution20.model.MyRecyclerViewAdapter;
import es.ieslavereda.epickbuscaminasevolution20.model.PartidaBuscaminas;

public class Partidas extends BaseActivity implements CallInterface {
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidas);
        recyclerView = findViewById(R.id.reciclerview);
        floatingActionButton = findViewById(R.id.atras);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showProgress();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        Model.getInstance().getPartidaBuscaminas();
    }

    @Override
    public void doInUI() {
        hideProgress();
        List<PartidaBuscaminas> partidaBuscaminas = Model.getInstance().getPartidaBuscaminas();
        myRecyclerViewAdapter.setPartidas(partidaBuscaminas);

    }
}