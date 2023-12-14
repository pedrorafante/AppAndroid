package com.pedrorafante.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Usuario> itemList;

    private OnItemClickListener onItemClickListener;

    private DatabaseHelper databaseHelper;

    public CustomAdapter(List<Usuario> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }



    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        databaseHelper = new DatabaseHelper(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Usuario item = itemList.get(position);
        holder.textView.setText(item.getUser());

        // Configurar o botão de excluir aqui
        holder.btnExcluir.setOnClickListener(view -> {
            // Implemente a lógica para excluir o item da lista
            // e notificar o RecyclerView sobre a mudança nos dados.
            databaseHelper.deleteUser(itemList.get(position).getId());
            itemList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button btnExcluir;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });

        }
    }

    public void atualizarList(List<Usuario> novaLista){
        this.itemList = novaLista;
        notifyDataSetChanged();
    }
}
