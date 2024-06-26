package com.ashmit.cal.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashmit.cal.R;

import java.util.ArrayList;

//this class is for the custom adapter of Recycler View and this is the helper class so it doenst have any layout and it cant access the other layouts directly
public class RecyclerStatAdapter extends RecyclerView.Adapter<RecyclerStatAdapter.ViewHolder> {

    Context context;
    ArrayList<StatModel> arrStat;

    RecyclerStatAdapter(Context context , ArrayList<StatModel> arrStat){
        this.context = context;
        this.arrStat = arrStat;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtX , txtO , txtDraw , txtSno;
        ViewHolder(View itemView){
            super(itemView);
            txtX = itemView.findViewById(R.id.txtVwXwin);
            txtO = itemView.findViewById(R.id.txtVwOwin);
            txtDraw = itemView.findViewById(R.id.txtVwDraw);
            txtSno = itemView.findViewById(R.id.txtVwMatch);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stat_row ,parent , false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtSno.setText("Match : " + arrStat.get(position).snoCounter);
            holder.txtX.setText("X's Win : " + arrStat.get(position).xWin);
            holder.txtO.setText("O's Win : "+ arrStat.get(position).oWin);
            holder.txtDraw.setText("Draws : " + arrStat.get(position).draw);
        }

    @Override
    public int getItemCount() {
        return arrStat.size();
    }


}
