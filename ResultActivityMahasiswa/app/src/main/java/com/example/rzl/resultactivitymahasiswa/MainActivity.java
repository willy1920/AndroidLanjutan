package com.example.rzl.resultactivitymahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button pilihMahasiswaButton;
    private Intent pilihMahasiwaIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pilihMahasiwaIntent = new Intent(this, PilihMahasiswaActivity.class);

        initPilihMahasiswaButton();
    }

    private void initPilihMahasiswaButton() {
        pilihMahasiswaButton = (Button) findViewById(R.id.pilihMahasiswaButton);

        pilihMahasiswaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivityForResult(pilihMahasiwaIntent, 1);
            }
        });
    }
}
