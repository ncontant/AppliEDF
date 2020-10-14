package com.example.appliedf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewClientActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newclient);
        //gestion des boutons enregistrer et annuler
        Button btnQuitter = findViewById(R.id.btnQuitterClient);
        Button btnEnregistrer = findViewById(R.id.btnEnregistrerClient);
        final DAOBdd clientBdd = new DAOBdd(this);
        clientBdd.open();
        //récupération des données saisies
        final EditText nomprenom = findViewById(R.id.editTextPersonName);

        final EditText email = findViewById(R.id.editTextEmail);

        final EditText adresse = findViewById(R.id.editTextAdresse);

        final EditText tel = findViewById(R.id.editTextTel);

        //on va créer un écouteur pour un groupe de boutons
        View.OnClickListener ecouteur = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnEnregistrerClient:
                        Client client = new Client(nomprenom.getText().toString(), email.getText().toString(), adresse.getText().toString(), tel.getText().toString());
                        clientBdd.insererClient(client);
                        clientBdd.close();
                        finish();
                        break;
                    case R.id.btnQuitterClient:
                        finish();
                        break;
                }
            }
        };

        btnEnregistrer.setOnClickListener(ecouteur);
        btnQuitter.setOnClickListener(ecouteur);

    }
}
