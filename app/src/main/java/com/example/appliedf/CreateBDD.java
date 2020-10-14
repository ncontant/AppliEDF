package com.example.appliedf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateBDD extends SQLiteOpenHelper {

    private static final String TABLE_CLIENT = "tclient";
    static final String COL_IDCLIENT = "_id";
    private static final String COL_NOMPRENOM = "NomPrenom";
    private static final String COL_EMAIL = "Email";
    private static final String COL_ADRESSE = "Adresse";
    private static final String COL_TEL = "Tel";

    private static final String CREATE_TABLECLIENT = "CREATE TABLE " + TABLE_CLIENT + " ("+COL_IDCLIENT+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_NOMPRENOM + " TEXT NOT NULL, " + COL_EMAIL + " TEXT NOT NULL, " + COL_ADRESSE + " TEXT NOT NULL," + COL_TEL + " TEXT NOT NULL);";

    private static final String TABLE_RELEVE = "treleve";
    static final String COL_IDRELEVE = "_id";
    private static final String COL_NUMCPT = "numcpt";
    private static final String COL_HP = "HP";
    private static final String COL_HC = "HC";
    private static final String COL_RAISON = "Raison";

    private static final String CREATE_TABLERELEVE = "CREATE TABLE " + TABLE_RELEVE + " ("+COL_IDRELEVE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_NUMCPT + " TEXT NOT NULL, " + COL_HP + " TEXT NOT NULL, " + COL_HC + " TEXT NOT NULL," + COL_RAISON + " TEXT NOT NULL);";

    //constructeur paramétré
    public CreateBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //méthodes d'instance permettant la gestion de l'objet
    @Override
    public void onCreate(SQLiteDatabase db) {
    //appelée lorsqu’aucune base n’existe et que la classe utilitaire doit en créer une
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_TABLECLIENT);
        db.execSQL(CREATE_TABLERELEVE);
    }
    // appelée si la version de la base a changé
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //On peut  supprimer la table et de la recréer
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RELEVE + ";");
        onCreate(db);
    }
}
