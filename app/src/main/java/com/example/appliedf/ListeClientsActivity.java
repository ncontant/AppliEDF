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

public class ListeClientsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeclients);
        //remplissage de la liste
        //Création d'une instance de la classe DAOClient
        DAOBdd clientBdd = new DAOBdd(this);
        final ListView listViewClients = findViewById(R.id.listViewClients);

        //On ouvre la table
        clientBdd.open();
        Cursor c = clientBdd.getDataClient();
        Toast.makeText(getApplicationContext(), "il y a " + String.valueOf(c.getCount()) + " clients dans la table", Toast.LENGTH_LONG).show();
        // colonnes à afficher
        String[] columns = new String[]{DAOBdd.COL_NOMPRENOM, DAOBdd.COL_EMAIL};
        // champs dans lesquelles afficher les colonnes
        int[] to = new int[]{R.id.textViewNomPrenom, R.id.textViewEmail};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.activity_listeclients, c, columns, to, 0);
        // Assign adapter to ListView
        listViewClients.setAdapter(dataAdapter);
        //on ferme la table
        clientBdd.close();

        // quand on selectionne un item
        listViewClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finish();
                startViewUnClientActivity(id);
            }
        });

        }
         private void startViewUnClientActivity(long clientId){
        Intent intent=new Intent(this, UnClientActivity.class);
        intent.putExtra("CLIENT_ID",clientId);
        startActivity(intent);
         }

    }

