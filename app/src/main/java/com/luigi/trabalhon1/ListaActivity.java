package com.luigi.trabalhon1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ListaActivity extends AppCompatActivity {

    private EditText valorNome;
    private EditText valorRaca;
    private EditText valorIdade;
    private Button salvar;
    private Button checarLista;
    private Cachorro cachorro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        valorNome = findViewById(R.id.valorNome);
        valorRaca = findViewById(R.id.valorRaca);
        valorIdade = findViewById(R.id.valorIdade);
        salvar = findViewById(R.id.salvar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar() {
        cachorro = new Cachorro();

        if (isNumeric(valorIdade.getText().toString())) {
            cachorro.nome = valorNome.getText().toString();
            cachorro.raca = valorRaca.getText().toString();
            cachorro.idade = Integer.valueOf(valorIdade.getText().toString());
            CachorroDAO.inserir(cachorro, this);
            valorNome.setText("");
            valorRaca.setText("");
            valorIdade.setText("");
        } else {
            Toast.makeText(this, "O campo 'idade' deve conter apenas números.", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}