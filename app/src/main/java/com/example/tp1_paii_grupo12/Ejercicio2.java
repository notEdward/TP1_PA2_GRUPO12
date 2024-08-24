package com.example.tp1_paii_grupo12;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ejercicio2 extends AppCompatActivity {

    private TextView pantalla;
    private String entradaActual = "";
    private String operador = "";
    private double primerOperando = 0;
    private boolean operadorClickeado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        pantalla = findViewById(R.id.screen);

        configurarBotones();
    }

    private void configurarBotones() {
        int[] botonesNumeros = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9
        };

        for (int id : botonesNumeros) {
            Button boton = findViewById(id);
            boton.setOnClickListener(v -> clickNumero((Button) v));
        }

        int[] botonesOperadores = {
                R.id.button_plus, R.id.button_minus, R.id.button_multiply, R.id.button_divide
        };

        for (int id : botonesOperadores) {
            Button boton = findViewById(id);
            boton.setOnClickListener(v -> clickOperador((Button) v));
        }

        Button botonLimpiar = findViewById(R.id.button_clear);
        botonLimpiar.setOnClickListener(v -> limpiar());

        Button botonIgual = findViewById(R.id.button_equals);
        botonIgual.setOnClickListener(v -> calcularResultado());
    }

    private void clickNumero(Button boton) {
        // Si se hizo clic en un operador, reiniciar la entrada actual
        if (operadorClickeado) {
            entradaActual = "";
            operadorClickeado = false;
        }
        // Si el botón de número es un "0" y entradaActual está vacía, no hacer nada
        if (boton.getText().toString().equals("0") && entradaActual.isEmpty()) {
            return;
        }
        // Manejar la entrada de un número negativo
        if (entradaActual.isEmpty() && boton.getText().toString().equals("-")) {
            entradaActual = "-";
        } else {
            entradaActual += boton.getText().toString();
        }
        pantalla.setText(entradaActual);
    }

    private void clickOperador(Button boton) {

        if (!entradaActual.isEmpty()) {
            try {
                primerOperando = Double.parseDouble(entradaActual);
            } catch (NumberFormatException e) {
                pantalla.setText("Error");
                return;
            }
            operador = boton.getText().toString();
            entradaActual = "";
            operadorClickeado = true;
        }
    }

    private void limpiar() {
        entradaActual = "";
        operador = "";
        primerOperando = 0;
        pantalla.setText("0");
    }

    private void calcularResultado() {
        if (!entradaActual.isEmpty()) {
            double segundoOperando;
            try {
                segundoOperando = Double.parseDouble(entradaActual);
            } catch (NumberFormatException e) {
                pantalla.setText("Error");
                return;
            }

            double resultado;
            switch (operador) {
                case "+":
                    resultado = primerOperando + segundoOperando;
                    break;
                case "-":
                    resultado = primerOperando - segundoOperando;
                    break;
                case "x":
                    resultado = primerOperando * segundoOperando;
                    break;
                case "/":
                    if (segundoOperando != 0) {
                        resultado = primerOperando / segundoOperando;
                    } else {
                        pantalla.setText("Error");
                        return;
                    }
                    break;
                default:
                    pantalla.setText("Error");
                    return;
            }
            pantalla.setText(String.valueOf(resultado));
            entradaActual = String.valueOf(resultado);
            operador = "";
        } else {
            pantalla.setText("Error");
        }
    }
}

