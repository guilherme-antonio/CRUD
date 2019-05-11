package com.example.crud;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
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
        ContentValues registro = new ContentValues();
        registro.put("nome", edNome.getText().toString());
        registro.put("nota", Double.parseDouble(edNota.getText().toString()));

        bd.update("notas", registro, "id=" + edCodigo.getText().toString(), null);
        Toast.makeText(this, "Aluno atualizado com sucesso", Toast.LENGTH_LONG).show();
    }

    public void bntExcluir(View v) {
        bd.delete("notas","id=" + edCodigo.getText().toString(), null);
        edCodigo.setText("");
        edNome.setText("");
        edNota.setText("");
        Toast.makeText(this, "Aluno excluído com sucesso", Toast.LENGTH_LONG).show();
    }

    public void bntPesquisar(View v) {
        final EditText edCodigoDialog = new EditText(this);

        AlertDialog.Builder telaPesquisa = new AlertDialog.Builder(this);

        DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realizarPesquisa(Integer.parseInt(edCodigoDialog.getText().toString()));
            }
        };

        telaPesquisa.setTitle("Pesquisa");
        telaPesquisa.setIcon(android.R.drawable.ic_menu_search);
        telaPesquisa.setNegativeButton("Cancelar", null);
        telaPesquisa.setPositiveButton("Ok", clickListener);
        telaPesquisa.setView(edCodigoDialog);
        telaPesquisa.show();
    }

    private void realizarPesquisa(int id){
        Cursor registro = bd.query("notas", null, "id=" + id, null, null, null, null);

        if (registro.moveToNext()) {
            String nome = registro.getString(registro.getColumnIndex("nome"));
            double nota = registro.getDouble(registro.getColumnIndex("nota"));

            this.edNota.setText(Double.toString(nota));
            this.edNome.setText(nome);
            this.edCodigo.setText(Integer.toString(id));
        }
        else {
            Toast.makeText(this, "registro não encontrado", Toast.LENGTH_LONG).show();
        }
    }

    public void bntListar(View v) {
    }
}
