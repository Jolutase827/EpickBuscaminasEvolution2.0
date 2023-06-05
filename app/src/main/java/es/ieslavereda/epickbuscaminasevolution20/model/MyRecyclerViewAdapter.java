package es.ieslavereda.epickbuscaminasevolution20.model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;


import es.ieslavereda.epickbuscaminasevolution20.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<PartidaBuscaminas> list;
    private final LayoutInflater inflater;


    public MyRecyclerViewAdapter(Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = new ArrayList<>();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PartidaBuscaminas partida = list.get(position);
       holder.mDetec.setText(holder.mDetec.getText().toString()+partida.getM_detectadas()+"");
       holder.numero.setText(partida.getNumero()+"");
       holder.tiempo.setText(holder.tiempo.getText().toString()+partida.getTiempo()+"");
       holder.resultado.setText(partida.getResultado()+"");
       if (partida.getResultado().compareToIgnoreCase("DERROTA")!=0) {
           holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.ganada,holder.itemView.getContext().getTheme()));
       }else
           holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.derrota,holder.itemView.getContext().getTheme()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setPartidas(List<PartidaBuscaminas> partidaBuscaminas) {
        this.list = partidaBuscaminas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;

        TextView mDetec, tiempo,resultado,numero;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            mDetec = itemView.findViewById(R.id.det);
            tiempo = itemView.findViewById(R.id.tiempo);
            resultado = itemView.findViewById(R.id.res);
            numero = itemView.findViewById(R.id.idPar);
        }

    }
}
