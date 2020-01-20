package com.example.afixapp.tareas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.afixapp.R;
import com.example.afixapp.menu_principal.Tareas;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CadTarea extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener {

    private TextInputEditText etDateInicio, etDateFim, etHoraInicio, etHoraFim, observaciones;

    private int diaInicio, diaFim, mesInicio, mesFim, anoInicio, anoFim, horaInicio, horaFim,
            minutoInicio, minutoFim;

    private Button botaoGravar, botaoCancelar;

    private Spinner spinnerFrequencia, spinnerCategoria;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_tarea);

        //SPINNER CATEGORIA
        spinnerCategoria = findViewById(R.id.eligeCategoria);
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this,
                R.array.categorias_item, R.layout.layout_spinner);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerCategoria.setOnItemSelectedListener(this);

        //SPINNER FREQUENCIA
        spinnerFrequencia = findViewById(R.id.eligeFrequencia);
        ArrayAdapter<CharSequence> adapterFrequencia = ArrayAdapter.createFromResource(this,
                R.array.frequencias_item, R.layout.layout_spinner);
        adapterFrequencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrequencia.setAdapter(adapterFrequencia);
        spinnerFrequencia.setOnItemSelectedListener(this);

        //DATEPICKERDIALOG
        etDateInicio = findViewById(R.id.inicioInputEdit);
        etDateFim = findViewById(R.id.fimInputEdit);

        //TIMEPICKER
        etHoraInicio = findViewById(R.id.horaInicioInputEdit);
        etHoraFim = findViewById(R.id.horaFimInputEdit);

        //OUTROS CAMPOS
        observaciones = findViewById(R.id.observacionesInputEdit);
        botaoGravar = findViewById(R.id.btn_guardar);
        botaoCancelar = findViewById(R.id.btn_cancelar);

        //SETA O CLICK NO CAMPO (NAO APAGAR)
        etDateInicio.setOnClickListener(this);
        etDateFim.setOnClickListener(this);
        etHoraInicio.setOnClickListener(this);
        etHoraFim.setOnClickListener(this);


        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        botaoGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validaCampos()){
                    saveTarea();
                    Intent it = new Intent(CadTarea.this, Tareas.class);
                    startActivity(it);
                }
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    //VALIDA CAMPOS DO FORMULARIO DE CRIAR TAREFAS
    private boolean validaCampos(){

        boolean res;

        String validaTextCategoria = spinnerCategoria.getSelectedItem().toString();
        String validaObservaciones = observaciones.getText().toString();
        String validaDataInicio = etDateInicio.getText().toString();
        String validaHoraInicio = etHoraInicio.getText().toString();
        String validaDataFim = etDateFim.getText().toString();
        String validaHoraFim = etHoraFim.getText().toString();
        String validaTextFrequencia = spinnerFrequencia.getSelectedItem().toString();

        if(res = isCampoVazio(validaObservaciones)){
            observaciones.requestFocus();
        }
        else
            if(res = isCampoVazio(validaDataInicio)){
                etDateInicio.requestFocus();
            }
            else
                if(res = isCampoVazio(validaDataFim)){
                    etDateFim.requestFocus();
                }
                else
                    if(res = isCampoVazio(validaHoraInicio)){
                        etHoraInicio.requestFocus();
                    }
                    else
                        if(res = isCampoVazio(validaHoraFim)){
                            etHoraFim.requestFocus();
                        }
                        else
                            if(res = isCampoVazio(validaTextCategoria)){
                                spinnerCategoria.requestFocus();
                            }
                            else
                                if(res = isCampoVazio(validaTextFrequencia)){
                                    spinnerFrequencia.requestFocus();
                                }
         if (res){
             AlertDialog.Builder dlg = new AlertDialog.Builder(this);
             dlg.setTitle(R.string.title_aviso);
             dlg.setMessage(R.string.message_campos_brancos);
             dlg.setNeutralButton(R.string.lbl_ok, null);
             dlg.show();
         }else{
             res = false;
         }
         return res;
    }

    //VALIDA CAMPOS VAZIOS
    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //CRIA A VIEW DO DATEPICKER E TIMEPICKER
    @Override
    public void onClick(View v) {
        if(v == etDateInicio){
            final Calendar c = Calendar.getInstance();
            diaInicio = c.get(Calendar.DAY_OF_MONTH);
            mesInicio = c.get(Calendar.MONTH);
            anoInicio = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    etDateInicio.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, diaInicio, mesInicio, anoInicio);
            datePickerDialog.show();
        }
        if(v == etDateFim) {
            final Calendar c = Calendar.getInstance();
            diaFim = c.get(Calendar.DAY_OF_MONTH);
            mesFim = c.get(Calendar.MONTH);
            anoFim = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    etDateFim.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, diaFim, mesFim, anoFim);
            datePickerDialog.show();
        }
        if(v == etHoraInicio) {
            final Calendar c = Calendar.getInstance();
            horaInicio = c.get(Calendar.HOUR_OF_DAY);
            minutoInicio = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    etHoraInicio.setText(hourOfDay + ":" + minute);
                }
            }, horaInicio, minutoInicio, true);
            timePickerDialog.show();
        }
        if(v == etHoraFim) {
            final Calendar c = Calendar.getInstance();
            horaFim = c.get(Calendar.HOUR_OF_DAY);
            minutoFim = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    etHoraFim.setText(hourOfDay + ":" + minute);
                }
            }, horaFim, minutoFim, true);
            timePickerDialog.show();
        }
    }

    private void saveTarea(){
        String key = databaseReference.child("usuario").push().getKey();

        String observacionesData = observaciones.getText().toString().trim();
        String eligiFrequenciaData = spinnerFrequencia.getSelectedItem().toString().trim();
        String eligiCategoriaData = spinnerCategoria.getSelectedItem().toString().trim();
        String etDateFimData = etDateFim.getText().toString().trim();
        String etHoraFimData = etHoraFim.getText().toString().trim();
        String etDateInicioData = etDateInicio.getText().toString().trim();
        String etHoraInicioData = etHoraInicio.getText().toString().trim();
        String tareaID = key;

        TareaData tareaData = new TareaData(tareaID, observacionesData, eligiFrequenciaData, eligiCategoriaData,
                etDateFimData, etHoraFimData, etDateInicioData, etHoraInicioData);



        String user = firebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child("usuario").child(user).child("tareas").child(key).setValue(tareaData);
        Toast.makeText(this, R.string.save_tarea, Toast.LENGTH_SHORT).show();

    }
}
