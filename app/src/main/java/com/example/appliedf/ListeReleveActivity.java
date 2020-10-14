package com.example.appliedf;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListeReleveActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listereleves);

        //Création d'une instance de la classe DAOClient
        DAOBdd releveBdd = new DAOBdd(this);
        ListView listViewReleves = findViewById(R.id.listViewReleves);

        //On ouvre la table
        releveBdd.open();
        Cursor c = releveBdd.getDataReleve();
        Toast.makeText(getApplicationContext(), "il y a " + String.valueOf(c.getCount()) + " relevés dans la table", Toast.LENGTH_LONG).show();
        // colonnes à afficher
        String[] columns = new String[]{DAOBdd.COL_NUMCPT, DAOBdd.COL_HP, DAOBdd.COL_HC};
        // champs dans lesquelles afficher les colonnes
        int[] to = new int[]{R.id.textViewNumcpt, R.id.textViewHP, R.id.textViewHC};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.activity_listereleves, c, columns, to,0);
        // Assign adapter to ListView
        listViewReleves.setAdapter(dataAdapter);
        //on ferme la table
        releveBdd.close();
    // quand on selectionne un item
        listViewReleves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finish();
                startViewUnReleveActivity(id);
            }
        });

    }
    private void startViewUnReleveActivity(long releveId){
        Intent intent=new Intent(this, UnReleveActivity.class);
        intent.putExtra("RELEVE_ID",releveId);
        startActivity(intent);
    }

}




