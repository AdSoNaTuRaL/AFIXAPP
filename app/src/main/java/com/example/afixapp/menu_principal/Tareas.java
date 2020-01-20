package com.example.afixapp.menu_principal;

import android.content.Intent;
import android.os.Bundle;

import com.example.afixapp.tareas.CadTarea;
import com.example.afixapp.R;
import com.example.afixapp.tareas.TareaData;
import com.example.afixapp.tareas.updateDelete;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Tareas extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;

    FloatingActionButton fab;
    BottomNavigationView bottomnav;
    Intent activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);


        activity = new Intent(Tareas.this, LoginActivity.class);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Toast.makeText(Tareas.this, R.string.user_is_login, Toast.LENGTH_LONG).show();
            startActivity(activity);
            finish();
        }

        //BOTAO FLOTANTE
        fab = findViewById(R.id.fab);
        //BARRA NAVEGACAO INFERIOR
        bottomnav = findViewById(R.id.bottom_navigation);
        //ListView
        lv = findViewById(R.id.listView);

        Query query = FirebaseDatabase.getInstance().getReference().child("usuario").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("tareas");

        FirebaseListOptions<TareaData> options = new FirebaseListOptions.Builder<TareaData>()
                .setLayout(R.layout.row_tareas)
                .setLifecycleOwner(Tareas.this)
                .setQuery(query, TareaData.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView tipoTarea = v.findViewById(R.id.tipoDeTarea);
                TextView frequencia = v.findViewById(R.id.frequencia);
                TextView observaciones = v.findViewById(R.id.observaciones);
                TextView dataInicio = v.findViewById(R.id.dataInicio);
                TextView dataFim = v.findViewById(R.id.dataFim);
                TextView horaInicio = v.findViewById(R.id.horaInicio);
                TextView horaFim = v.findViewById(R.id.horaFim);

                TareaData tareaData = (TareaData) model;
                tipoTarea.setText(tareaData.getEligirCategoria().toString());
                frequencia.setText(tareaData.getEligirFrequencia().toString());
                observaciones.setText(tareaData.getObservaciones().toString());
                dataInicio.setText(tareaData.getDataInicio().toString());
                dataFim.setText(tareaData.getDataFim().toString());
                horaInicio.setText(tareaData.getHoraInicio().toString());
                horaFim.setText(tareaData.getHoraFim().toString());

            }
        };

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent updateDelete = new Intent(Tareas.this, updateDelete.class);
                TareaData tareaData = (TareaData) adapterView.getItemAtPosition(i);
                updateDelete.putExtra("tipoTarea", tareaData.getEligirCategoria());
                updateDelete.putExtra("frequencia", tareaData.getEligirFrequencia());
                updateDelete.putExtra("observaciones", tareaData.getObservaciones());
                updateDelete.putExtra("dataInicio", tareaData.getDataInicio());
                updateDelete.putExtra("dataFim", tareaData.getDataFim());
                updateDelete.putExtra("horaInicio", tareaData.getHoraInicio());
                updateDelete.putExtra("horaFim", tareaData.getHoraFim());
                updateDelete.putExtra("key", tareaData.getTareaID());
                startActivity(updateDelete);
            }
        });

        //CHAMA ACTIVITY CADASTRA TAREFAS
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadTarea  = new Intent(Tareas.this, CadTarea.class);
                startActivity(cadTarea);
            }
        });



        //RESETANDO AS SELECAO DE MENUS
        Menu menu = bottomnav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        //SWITCH CASE E CRIA INTENTS PARA CADA MENU DA BARRA DE NAVEGACAO INFERIOR
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.
                OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_game:
                        Intent intentJuego = new Intent(Tareas.this, Juegos.class);
                        startActivity(intentJuego);
                        break;

                    case R.id.nav_tarea:
                        break;

                    case R.id.nav_config:
                        Intent intentConfig = new Intent(Tareas.this,
                                Configuracion.class);
                        startActivity(intentConfig);
                        break;
                    case R.id.nav_mapa:
                        Intent intentMapa = new Intent(Tareas.this, Maps.class);
                        startActivity(intentMapa);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
