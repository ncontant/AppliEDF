package com.example.appliedf;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UnClientActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unclient);
        long idlu = -1;
        Intent intent = getIntent();
        if (intent != null) {
            idlu = intent.getLongExtra("CLIENT_ID", -1);

        }
        final long id = idlu;
        //recherche et affichage des infos sur ce client
        DAOBdd clientBdd = new DAOBdd(this);
        clientBdd.open();
        Client clientlu = clientBdd.getClientWithId(id);
        TextView NomPrenomLu = findViewById(R.id.textViewNomPrenomLu);
        TextView AdresseLu = findViewById(R.id.textViewAdresseLu);
        TextView EmailLu = findViewById(R.id.textViewEmailLu);
        TextView TelLu = findViewById(R.id.textViewTelLu);
        NomPrenomLu.setText(clientlu.getNomPrenom());
        AdresseLu.setText(clientlu.getAdresse());
        EmailLu.setText(clientlu.getEmail());
        TelLu.setText(clientlu.getTel());
        clientBdd.close();

        //programmation du bouton quitter pour retourner à la liste des clients
        Button btnQuitter = findViewById(R.id.btnQuitterAfficheUnClient);
        Button btnSupprimer = findViewById(R.id.btnSupprimerUnClient);
        Button btnModifier = findViewById(R.id.btnModifierUnClient);
        View.OnClickListener ecouteur = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnModifierUnClient:
                        modifierUnClient(id);
                        break;
                    case R.id.btnSupprimerUnClient:
                        supprimerUnClient(id);
                        break;
                    case R.id.btnQuitterAfficheUnClient:
                        finish();
                        break;

                }
            }
        };
        btnQuitter.setOnClickListener(ecouteur);
        btnModifier.setOnClickListener(ecouteur);
        btnSupprimer.setOnClickListener(ecouteur);

    }

    public void modifierUnClient(long id) {
        EditText nomprenom = findViewById(R.id.editTextNomprenommodif);
        EditText email = findViewById(R.id.editTextemailmodif);
        EditText ville=findViewById(R.id.editTextvillemodif);
        EditText tel=findViewById(R.id.editTextTelmodif);
        Button btnEnregistrerModifClient = findViewById(R.id.btnEnregistrerModifClient);
        nomprenom.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        ville.setVisibility(View.VISIBLE);
        tel.setVisibility(View.VISIBLE);
        btnEnregistrerModifClient.setVisibility(View.VISIBLE);

    }

    public void supprimerUnClient(long id) {
        //problème car le retour à la liste ne recharge pas la liste, il faudrait qu'ici on tue l'activity ListeClientsActivity pour la recharger

        DAOBdd clientBdd = new DAOBdd(this);
        clientBdd.open();
        clientBdd.removeClient(id);
        clientBdd.close();
        Toast.makeText(getApplicationContext(), "client supprimé !", Toast.LENGTH_LONG).show();
        finish();
    }
}