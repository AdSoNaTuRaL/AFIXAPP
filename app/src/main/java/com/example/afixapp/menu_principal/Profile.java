package com.example.afixapp.menu_principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.afixapp.R;
import com.example.afixapp.tareas.CadTarea;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    private Button btnSalir;
    private EditText updateName, updateEmail;
    private ImageView updatePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnSalir = findViewById(R.id.logOutBtn);
        updateName = findViewById(R.id.updateName);
        updateEmail = findViewById(R.id.updateMail);
        updatePhoto = findViewById(R.id.updateProfilePicutre);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });

        updateProfile();

    }


    private void updateProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            updateName.setText(user.getDisplayName());
            updateEmail.setText(user.getEmail());

            boolean emailVerified = user.isEmailVerified();

            Glide.with(this).load(user.getPhotoUrl()).into(updatePhoto);
        }else{
            // No user is signed in
            Intent it = new Intent(Profile.this, LoginActivity.class);
            startActivity(it);
        }
    }
}
