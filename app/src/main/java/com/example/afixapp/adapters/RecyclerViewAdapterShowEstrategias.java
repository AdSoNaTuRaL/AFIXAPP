package com.example.afixapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afixapp.R;
import com.example.afixapp.relajacion.GalleryActivity;

import java.util.ArrayList;

public class RecyclerViewAdapterShowEstrategias extends RecyclerView.Adapter<RecyclerViewAdapterShowEstrategias.ViewHolder>{

    private static final String TAG = "ShowEstrategias";

    private ArrayList<String> mEstrategiaTitulo = new ArrayList<>();
    private ArrayList<String> mEstrategiaDesc = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterShowEstrategias(Context context, ArrayList<String> estrategiaTit, ArrayList<String>
            estrategiaDes) {
        mEstrategiaTitulo = estrategiaTit;
        mContext = context;
        mEstrategiaDesc = estrategiaDes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_estrategia, parent,
                false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.estrategiaTitulo.setText(mEstrategiaTitulo.get(position));
        holder.estrategiaDesc.setText(mEstrategiaDesc.get(position));

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mEstrategiaTitulo.get(position));
                Toast.makeText(mContext, mEstrategiaTitulo.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, GalleryActivity.class);
                intent.putExtra("estrategiaTitulo", mEstrategiaTitulo.get(position));
                intent.putExtra("estrategiaDescricao", mEstrategiaDesc.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEstrategiaTitulo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView estrategiaTitulo;
        TextView estrategiaDesc;
        RelativeLayout parent_layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            estrategiaTitulo = itemView.findViewById(R.id.estrategia);
            estrategiaDesc = itemView.findViewById(R.id.desc_estrategia);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
