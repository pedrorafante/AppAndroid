package com.pedrorafante.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<String> itemList;

    public CustomAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        String item = itemList.get(position);
        holder.textView.setText(item);

        // Configurar o botão de excluir aqui
        holder.btnExcluir.setOnClickListener(view -> {
            // Implemente a lógica para excluir o item da lista
            // e notificar o RecyclerView sobre a mudança nos dados.
            itemList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button btnExcluir;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}
