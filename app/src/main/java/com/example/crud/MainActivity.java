package com.example.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edCodigo;
    EditText edNome;
    EditText edNota;

    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.edCodigo = findViewById(R.id.edCodigo);
        this.edNome = findViewById(R.id.edNome);
        this.edNota = findViewById(R.id.edNota);

        bd = openOrCreateDatabase("alunos", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS notas" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR NOT NULL," +
                "nota DECIMAL NOT NULL)");
    }

    public void bntCadastrar(View v){
        ContentValues registro = new ContentValues();
        registro.put("nome", edNome.getText().toString());
        registro.put("nota", Double.parseDouble(edNota.getText().toString()));
        bd.insert("notas", null, registro);
        Toast.makeText(this, "Aluno inserido com sucesso", Toast.LENGTH_LONG).show();
    }

    public void bntAlterar(View v) {
    }

    public void bntExcluir(View v) {
    }

    public void bntPesquisar(View v) {
    }

    public void bntListar(View v) {
    }
}
