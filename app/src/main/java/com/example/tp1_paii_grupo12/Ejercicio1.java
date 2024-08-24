package com.example.tp1_paii_grupo12;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio1 extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio1);

        et1 = findViewById(R.id.txt_primerNumero);
        et2 = findViewById(R.id.txt_segundoNumero);
        result = findViewById(R.id.txt_resultado);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Sumar(View view){
        int num1 = Integer.parseInt(et1.getText().toString());
        int num2 = Integer.parseInt(et2.getText().toString());

        int suma = num1 + num2;
        String resultado = String.valueOf(suma);

        result.setText("Resultado: "+resultado);
    }
}