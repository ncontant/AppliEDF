package com.example.appliedf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AfficheUnReleveActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficheunreleve);

       String cptlu ="";
        String hplu="";
        String hclu="";
        String raisonlu="";

        //on va récupérer les trois valeurs provenant de NewReleveActivity
        Intent intent = getIntent();
        if (intent != null) {

            cptlu= intent.getStringExtra("EXTRA_CPT");
            hplu=intent.getStringExtra("EXTRA_HP");
            hclu=intent.getStringExtra("EXTRA_HC");
            raisonlu=intent.getStringExtra("EXTRA_RAISE");
        }


    ListView ListeInfosUnReleve = (ListView) findViewById(R.id.listeInfosUnReleve);

        List<String> info = new ArrayList<String>();

        info.add("numéro compteur : "+cptlu);
        info.add("heure pleine : "+ hplu);
        info.add("heure creuse : "+hclu);
        info.add("raison : "+raisonlu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);
        ListeInfosUnReleve.setAdapter(adapter);

        //programmation du bouton quitter
        Button btnQuitter = findViewById(R.id.btnQuitterAfficheUnReleve);
        //on va créer un écouteur
        View.OnClickListener ecouteur = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                final String message = "le relevé doit être modifié";
                Intent i1 = new Intent();
                i1.putExtra("MESSAGE",message);
                setResult(1,i1);
                //on ferme l'interface
                finish();
            }
        };
        //on affecte l'écouteur au bouton
        btnQuitter.setOnClickListener(ecouteur);
        final DAOBdd releveBdd = new DAOBdd(this);
        releveBdd.open();
        final String cpt = cptlu;
        final String hp=hplu;
        final String hc=hclu;
        final String raison=raisonlu;

        //programmation du bouton valider
        Button btnValider = findViewById(R.id.btnValiderNewReleve);
        //on va créer un écouteur
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                //on va enregistrer le releve dans la table releve
                Releve releve = new Releve(cpt, hp,hc,raison);
                releveBdd.insererReleve(releve);
                releveBdd.close();
                final String message = "le relevé a bien été enregistré";
                Intent i1 = new Intent();
                i1.putExtra("MESSAGE",message);
                setResult(1,i1);
                //on ferme l'interface
                finish();
            }
        };
        //on affecte l'écouteur au bouton
        btnValider.setOnClickListener(ecouteur1);
    }
}
