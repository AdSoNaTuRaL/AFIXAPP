package com.example.afixapp.tareas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.afixapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class updateDelete extends AppCompatActivity implements  AdapterView.OnItemSelectedListener,
        View.OnClickListener{

    EditText observaciones, dataInicio, dataFim, horaInicio, horaFim;
    Spinner categoria, frequencia;
    DatabaseReference reference;
    private int diaInicio, diaFim, mesInicio, mesFim, anoInicio, anoFim, horaInicio2, horaFim2,
            minutoInicio, minutoFim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);


        frequencia = findViewById(R.id.eligeFrequenciaUpdate);
        categoria = findViewById(R.id.eligeCategoriaUpdate);
        observaciones = findViewById(R.id.observacionesInputEditUpdate);
        dataInicio = findViewById(R.id.inicioInputEditUpdate);
        dataFim = findViewById(R.id.fimInputEditUpdate);
        horaInicio = findViewById(R.id.horaInicioInputEditUpdate);
        horaFim = findViewById(R.id.horaFimInputEditUpdate);

        //PEGO A CHAVE GERADA PELO PUSH
        String key = getIntent().getExtras().get("key").toString();

        //REFERENCIA FIREBASE DAS TAREFAS DE CLICADA PELO USUÁRIO
        reference = FirebaseDatabase.getInstance().getReference().child("usuario").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("tareas").child(key);

        //pego os valores preselecionados e salvo do spinner na lista
        String compareValueCategoria = getIntent().getExtras().get("tipoTarea").toString();
        String compareValueFrequencia = getIntent().getExtras().get("frequencia").toString();


        //SPINNER CATEGORIA
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this,
                R.array.categorias_item, R.layout.layout_spinner);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapterCategoria);
        categoria.setOnItemSelectedListener(this);

        //SPINNER FREQUENCIA
        ArrayAdapter<CharSequence> adapterFrequencia = ArrayAdapter.createFromResource(this,
                R.array.frequencias_item, R.layout.layout_spinner);
        adapterFrequencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequencia.setAdapter(adapterFrequencia);
        frequencia.setOnItemSelectedListener(this);

        if(compareValueCategoria != null && compareValueFrequencia != null){
            int spinnerPosistionCategoria = adapterCategoria.getPosition(compareValueCategoria);
            int spinnerPosistionFrequencia = adapterFrequencia.getPosition(compareValueFrequencia);
            categoria.setSelection(spinnerPosistionCategoria);
            frequencia.setSelection(spinnerPosistionFrequencia);
        }


        observaciones.setText(getIntent().getStringExtra("observaciones"));
        dataInicio.setText(getIntent().getStringExtra("dataInicio"));
        dataFim.setText(getIntent().getStringExtra("dataFim"));
        horaInicio.setText(getIntent().getStringExtra("horaInicio"));
        horaFim.setText(getIntent().getStringExtra("horaFim"));

        //TALVEZ APAGAR
        dataInicio.setOnClickListener(updateDelete.this);
        dataFim.setOnClickListener(this);
        horaInicio.setOnClickListener(this);
        horaFim.setOnClickListener(this);

    }

    public void btnUpdate_Click(View view){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("eligirCategoria").setValue(categoria.getSelectedItem().toString());
                dataSnapshot.getRef().child("eligirFrequencia").setValue(frequencia.getSelectedItem().toString());
                dataSnapshot.getRef().child("observaciones").setValue(observaciones.getText().toString());
                dataSnapshot.getRef().child("dataInicio").setValue(dataInicio.getText().toString());
                dataSnapshot.getRef().child("dataFim").setValue(dataFim.getText().toString());
                dataSnapshot.getRef().child("horaInicio").setValue(horaInicio.getText().toString());
                dataSnapshot.getRef().child("horaFim").setValue(horaFim.getText().toString());

                Toast.makeText(updateDelete.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                updateDelete.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void btnDelete_Click(View view){
        reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(updateDelete.this, "Eliminado con éxito",
                            Toast.LENGTH_SHORT).show();
                    updateDelete.this.finish();
                }else{
                    Toast.makeText(updateDelete.this, "No ha sido eliminado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == dataInicio){
            final Calendar c = Calendar.getInstance();
            diaInicio = c.get(Calendar.DAY_OF_MONTH);
            mesInicio = c.get(Calendar.MONTH);
            anoInicio = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dataInicio.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        }
                    }, diaInicio, mesInicio, anoInicio);
            datePickerDialog.show();
        }
        if(v == dataFim) {
            final Calendar c = Calendar.getInstance();
            diaFim = c.get(Calendar.DAY_OF_MONTH);
            mesFim = c.get(Calendar.MONTH);
            anoFim = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dataFim.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        }
                    }, diaFim, mesFim, anoFim);
            datePickerDialog.show();
        }
        if(v == horaInicio) {
            final Calendar c = Calendar.getInstance();
            horaInicio2 = c.get(Calendar.HOUR_OF_DAY);
            minutoInicio = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaInicio.setText(hourOfDay + ":" + minute);
                        }
                    }, horaInicio2, minutoInicio, true);
            timePickerDialog.show();
        }
        if(v == horaFim) {
            final Calendar c = Calendar.getInstance();
            horaFim2 = c.get(Calendar.HOUR_OF_DAY);
            minutoFim = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaFim.setText(hourOfDay + ":" + minute);
                        }
                    }, horaFim2, minutoFim, true);
            timePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
