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
    private EditText valorAutor;
    private EditText valorAno;
    private Button salvar;
    private Button checarLista;
    private Historia historia;
    private String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        valorNome = findViewById(R.id.valorNome);
        valorAutor = findViewById(R.id.valorAutor);
        valorAno = findViewById(R.id.valorAno);
        salvar = findViewById(R.id.salvar);

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")) {
            carregarFormulario();
        }
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar() {
        if (acao.equals("novo")) {
            historia = new Historia();
        }

        if (isNumeric(valorAno.getText().toString())) {
            historia.nome = valorNome.getText().toString();
            historia.autor = valorAutor.getText().toString();
            historia.ano = Integer.valueOf(valorAno.getText().toString());
            if (acao.equals("editar")) {
                HistoriaDAO.editar(historia, this);
                finish();
            } else {
                HistoriaDAO.inserir(historia, this);
                valorNome.setText("");
                valorAutor.setText("");
                valorAno.setText("");
            }

        } else {
            Toast.makeText(this, "O campo 'ano' deve conter apenas números.", Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarFormulario() {
        int idHistoria = getIntent().getIntExtra("idHistoria", 0);
        if (idHistoria != 0) {
            historia = HistoriaDAO.getHistoriaById(this, idHistoria);
            valorNome.setText(historia.nome);
            valorAno.setText(String.valueOf(historia.ano));
            valorAutor.setText(historia.autor);
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