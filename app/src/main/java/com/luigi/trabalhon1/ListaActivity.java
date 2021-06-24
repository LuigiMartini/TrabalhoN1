package com.luigi.trabalhon1;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.ByteArrayOutputStream;

public class ListaActivity extends AppCompatActivity {

    private EditText valorNome;
    private EditText valorAutor;
    private EditText valorAno;
    private Button salvar;
    private FloatingActionButton btnAdd;
    private Historia historia;
    private String acao;
    private ImageView comicImage;
    private String  fotoString ="";
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        valorNome = findViewById(R.id.valorNome);
        valorAutor = findViewById(R.id.valorAutor);
        valorAno = findViewById(R.id.valorAno);
        salvar = findViewById(R.id.salvar);
        btnAdd = findViewById(R.id.btnAdd);
        comicImage =(ImageView) findViewById(R.id.comicImage);


        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")) {
            carregarFormulario();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);

            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){

            try {
                byte [] fotoByte;
                ByteArrayOutputStream streamFoto = new ByteArrayOutputStream();
                Bitmap imagemSalva = (Bitmap) data.getExtras().get("data");

                imagemSalva.compress(Bitmap.CompressFormat.JPEG,100,streamFoto);
                comicImage.setImageBitmap(imagemSalva);

                fotoByte = streamFoto.toByteArray();
                fotoString = Base64.encodeToString(fotoByte, Base64.DEFAULT);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void salvar() {

        if (acao.equals("novo")) {

            historia = new Historia();
        }

        if (isNumeric(valorAno.getText().toString())) {
            historia.nome = valorNome.getText().toString();
            historia.autor = valorAutor.getText().toString();
            historia.ano = Integer.valueOf(valorAno.getText().toString());
            historia.foto = fotoString;

            if (acao.equals("editar")) {
                HistoriaDAO.editar(historia, this);
                Toast.makeText(this, "Comic Salva!", Toast.LENGTH_SHORT).show();
                finish();
            } else {

                HistoriaDAO.inserir(historia, this);

                valorNome.setText("");
                valorAutor.setText("");
                valorAno.setText("");
                comicImage.setImageResource(R.drawable.comicc);;

                Toast.makeText(this, "Comic Salva!", Toast.LENGTH_SHORT).show();
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
            try {
                byte[] fotoBytes = Base64.decode(historia.getFoto(), Base64.DEFAULT);
                Bitmap fotoBitmap = BitmapFactory.decodeByteArray(fotoBytes, 0, 10000);
                comicImage.setImageBitmap(fotoBitmap);

            }catch (Exception e){

                comicImage.setImageResource(R.drawable.comic);;
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}





