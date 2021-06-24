package com.luigi.trabalhon1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.luigi.trabalhon1.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ListView listaHistorias;
    private AdapterHistoria adapter;
    private List<Historia> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                intent.putExtra("acao", "novo");
                startActivity(intent);
            }
        });

        listaHistorias = findViewById(R.id.listaHistorias);
        carregarHistorias();

        configurarListView();
    }

    private void configurarListView() {
        listaHistorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Historia historiaSelecionada = lista.get(position);
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idHistoria", historiaSelecionada.id);
                startActivity(intent);
            }
        });

        listaHistorias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Historia historiaSelecionada = lista.get(position);
                excluirHistoria(historiaSelecionada);
                return true;
            }
        });
    }

    private void excluirHistoria(Historia historia) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle("Atenção");
        alerta.setMessage("Confirma a exclusão da história " + historia.nome + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HistoriaDAO.excluir(historia.id, MainActivity.this);
                carregarHistorias();
            }
        });

        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarHistorias();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregarHistorias() {
        lista = HistoriaDAO.getHistorias(this);


        adapter = new AdapterHistoria(this, lista);
        listaHistorias.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}