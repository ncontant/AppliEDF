package com.example.appliedf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class NewReleveActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newreleve);

        //on déclare des objets java pour chaque widjet et obligatoirement en constante car passées à l'autre interface
        final EditText heurepleine = findViewById(R.id.editTextHP);
        final EditText heurecreuse = findViewById(R.id.editTextHC);
        final String[] leCompteur = new String[1];
        final String[] raison = {""};


        //gestion de la liste déroulante des numéros de compteur
        final Spinner spinnerNumCpt = (Spinner) findViewById(R.id.spinnerNumCpt);
        String[] lescompteurs = {"123", "456", "789", "147", "258", "369"};
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lescompteurs);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumCpt.setAdapter(dataAdapterR);
        spinnerNumCpt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                leCompteur[0] = String.valueOf(spinnerNumCpt.getSelectedItem());
                Toast.makeText(NewReleveActivity.this, "Vous avez choisie : " + "\nle compteur numéro : " + leCompteur[0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //cas où rien n'est sélectionné

            }
        });


        //gestion des boutons enregistrer et annuler
        Button btnEnregistrer = findViewById(R.id.btnEnregistrer);
        Button btnAnnuler = findViewById(R.id.btnAnnuler);
        //on va créer un écouteur pour un groupe de boutons
        View.OnClickListener ecouteur = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnEnregistrer:
                        //on passer les infos dans l'autre interface
                        Intent i = new Intent(NewReleveActivity.this, AfficheUnReleveActivity.class);
                        i.putExtra("EXTRA_CPT", leCompteur[0]);
                        i.putExtra("EXTRA_HP", heurepleine.getText().toString());
                        i.putExtra("EXTRA_HC", heurecreuse.getText().toString());
                        i.putExtra("EXTRA_RAISE", raison[0]);
                        //startActivity(i);
                        //on lance la seconde interface avec une attente de retour
                       startActivityForResult(i, 0);


                        break;
                    case R.id.btnAnnuler:
                        finish();
                        break;
                }
            }
        };

        btnEnregistrer.setOnClickListener(ecouteur);
        btnAnnuler.setOnClickListener(ecouteur);


        //programmation des boutons radios

        RadioGroup radioGroupRaison = findViewById(R.id.radioGroupRaison);
        radioGroupRaison.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup radioGroupRaison, int i) {
                switch (i) {
                    case R.id.radioButtonNormal:
                        raison[0] = "Normal";
                        Toast.makeText(getApplicationContext(), raison[0], Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButtonDemenagement:
                        raison[0] = "Emménagement";
                        Toast.makeText(getApplicationContext(), raison[0], Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButtonChangement:
                        raison[0] = "Changement";
                        Toast.makeText(getApplicationContext(), raison[0], Toast.LENGTH_LONG).show();
                        break;
                }
            }

        });
    }

    //on récupère le message depuis l'interface AfficheUnReleveActivity
    //On exécute dès que l'activité appelée est terminée (finish)
    //requestCode est le numéro de l'activité
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //si le code de la requête est ok alors on récupère la donnée numéro 1 qui est message
        if (resultCode == 1) {
            String s = data.getStringExtra("MESSAGE");
            TextView messageobtenu = findViewById(R.id.textViewMessage);
            messageobtenu.setText(s);
        }

    }


}