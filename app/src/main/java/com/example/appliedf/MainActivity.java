package com.example.appliedf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //on associe à un objet java de type Button, un widget repéré physiquement par son id
        Button btnNewReleve = findViewById(R.id.btnNvReleve);
        Button btnListeClients = findViewById(R.id.btnListeClient);
        Button btnListeReleves = findViewById(R.id.btnListeReleves);
        Button btnNewClient=findViewById(R.id.btnNvClient);

       //on place un écouteur dessus
        View.OnClickListener ecouteur = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnNvReleve:
                        Intent intent1 = new Intent(MainActivity.this, NewReleveActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.btnListeClient:
                        Intent intent2 = new Intent(MainActivity.this, ListeClientsActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.btnNvClient:
                        Intent intent3 = new Intent(MainActivity.this, NewClientActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.btnListeReleves:
                        Intent intent4 = new Intent(MainActivity.this, ListeReleveActivity.class);
                        startActivity(intent4);
                        break;
                }
            }
        };
        //on affecte au bouton l'écouteur
        btnNewReleve.setOnClickListener(ecouteur);
        btnListeClients.setOnClickListener(ecouteur);
        btnListeReleves.setOnClickListener(ecouteur);
        btnNewClient.setOnClickListener(ecouteur);




        //procédure pour créer la table client avec deux clients
        //remplirTableClient();
        //procédure pour créer la table relevé et y insérer un relevé
       // remplirTableReleve();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_newClient:
                Toast.makeText(getApplicationContext(), "ouverture fenêtre Saisir nouveau client !", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(MainActivity.this, NewClientActivity.class);
                startActivity(intent1);
                return true;
            case R.id.menu_newReleve:
                Toast.makeText(getApplicationContext(), "ouverture fenêtre nouveau relevé !", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(MainActivity.this, NewReleveActivity.class);
                startActivity(intent2);
                return true;
             case R.id.menu_listeClients:
                Toast.makeText(getApplicationContext(), "ouverture fenêtre liste des clients !", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(MainActivity.this, ListeClientsActivity.class);
                startActivity(intent3);
                return true;
            case R.id.menu_listeReleves:
                Toast.makeText(getApplicationContext(), "ouverture fenêtre liste des relevés !", Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(MainActivity.this, ListeReleveActivity.class);
                startActivity(intent4);
                return true;

        }
        return false;
    }


    public void remplirTableClient() {
        DAOBdd clientBdd = new DAOBdd(this);
        Client client1 = new Client("ContantNelly", "ncontant@la_joliverie.com", "Nantes","02.40.78.12.35");
        Client client2 = new Client("BourgeoisNicolas", "nbourgeois@la-joliverie.com", "Vertou","02.40.56.89.78");
        //on ouvre la base de données
        clientBdd.open();
        //on insère client1 puis client2
        clientBdd.insererClient(client1);
        clientBdd.insererClient(client2);
        //le curseur pour afficher le nombre de clients dans la base
        Cursor c = clientBdd.getDataClient();
        Toast.makeText(getApplicationContext(), " il y a " + String.valueOf(c.getCount()) + " clients ", Toast.LENGTH_LONG).show();
        clientBdd.close();
    }
    public void remplirTableReleve() {
        DAOBdd relevebdd = new DAOBdd(this);
        Releve releve1 = new Releve("789", "0", "0","changement");

        //on ouvre la base de données
        relevebdd.open();
        //on insère client1 puis client2
        relevebdd.insererReleve(releve1);

        //le curseur pour afficher le nombre de clients dans la base
        Cursor c = relevebdd.getDataReleve();
        Toast.makeText(getApplicationContext(), " il y a " + String.valueOf(c.getCount()) + " relevés ", Toast.LENGTH_LONG).show();
        relevebdd.close();
    }
}