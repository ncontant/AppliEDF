package com.example.appliedf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOBdd {
    static final int VERSION_BDD =1;
    private static final String NOM_BDD = "Edfv1.db";

    //table client
    static final String TABLE_CLIENT = "tclient";
    static final String COL_IDCLIENT = "_id";
    static final int NUM_COL_IDCLIENT = 0;
    static final String COL_NOMPRENOM = "NomPrenom";
    static final int NUM_COL_NOMPRENOM = 1;
    static final String COL_EMAIL = "Email";
    static final int NUM_COL_EMAIL = 2;
    static final String COL_ADRESSE = "Adresse";
    static final int NUM_COL_ADRESSE = 3;
    static final String COL_TEL = "Tel";
    static final int NUM_COL_TEL = 4;

    //table relevé
    static final String TABLE_RELEVE = "treleve";
    static final String COL_IDRELEVE = "_id";
    static final int NUM_COL_IDRELEVE = 0;
    static final String COL_NUMCPT = "numcpt";
    static final int NUM_COL_NUMCPT = 1;
    static final String COL_HP = "HP";
    static final int NUM_COL_HP = 2;
    static final String COL_HC = "HC";
    static final int NUM_COL_HC = 3;
    static final String COL_RAISON = "Raison";
    static final int NUM_COL_RAISON = 4;


    private CreateBDD tableCourante;
    private Context context;
    private SQLiteDatabase db;


    //le constructeur
    public DAOBdd(Context context){
        this.context = context;
        tableCourante = new CreateBDD(context, NOM_BDD, null, VERSION_BDD);
    }

    //si la bdd n’existe pas, l’objet SQLiteOpenHelper exécute la méthode onCreate
    // si la version de la base a changé, la méthode onUpgrade sera lancée
    // dans les 2 cas l’appel à getWritableDatabase ou getReadableDatabase renverra  la base
    // de données en cache, nouvellement ouverte, nouvellement créée ou mise à jour

    //les méthodes d'instance
    public DAOBdd open(){
        db = tableCourante.getWritableDatabase();
        return this;
    }
    public DAOBdd close(){
        db.close();
        return null;
    }
    public long insererClient (Client unClient){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_NOMPRENOM, unClient.getNomPrenom());
        values.put(COL_EMAIL, unClient.getEmail());
        values.put(COL_ADRESSE, unClient.getAdresse());
        values.put(COL_TEL, unClient.getTel());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_CLIENT, null, values);
    }

    private Client cursorToClient(Cursor c){ //Cette méthode permet de convertir un cursor en un client
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon
        c.moveToFirst();   //on se place sur le premier élément
        Client unClient = new Client(null,null,null,null);  //On créé un client
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        unClient.setNomPrenom(c.getString(NUM_COL_NOMPRENOM));
        unClient.setEmail(c.getString(NUM_COL_EMAIL));
        unClient.setAdresse(c.getString(NUM_COL_ADRESSE));
        unClient.setTel(c.getString(NUM_COL_TEL));
        c.close();     //On ferme le cursor
        return unClient;  //On retourne le client
    }

    public Client getClientWithId(long id){
        //Récupère dans un Cursor les valeurs correspondant à un article grâce à son id
        Cursor c = db.query(TABLE_CLIENT, new String[] {COL_IDCLIENT,COL_NOMPRENOM, COL_EMAIL, COL_ADRESSE, COL_TEL}, COL_IDCLIENT + " = \"" + id +"\"", null, null, null, null);
        return cursorToClient(c);
    }

    public int updateClient(long id, Client unClient){
        //La mise à jour d'un client dans la table fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel client on doit mettre à jour grâce à son id
        ContentValues values = new ContentValues();
        values.put(COL_NOMPRENOM, unClient.getNomPrenom());
        values.put(COL_EMAIL, unClient.getEmail());
        values.put(COL_ADRESSE, unClient.getAdresse());
        values.put(COL_TEL, unClient.getTel());
        return db.update(TABLE_CLIENT, values, COL_IDCLIENT + " = \"" + id +"\"", null);
    }
    public int removeClient(long id){
        //Suppression d'un client de la table grâce à son nomprenom
        return db.delete(TABLE_CLIENT, COL_IDCLIENT + " = \"" + id +"\"", null);
    }

    public Cursor getDataClient(){
        return db.rawQuery("SELECT * FROM tclient", null);
    }

//pour le relevé
public long insererReleve (Releve unReleve){
    //Création d'un ContentValues (fonctionne comme une HashMap)
    ContentValues values = new ContentValues();
    //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
    values.put(COL_NUMCPT, unReleve.getNumcpt());
    values.put(COL_HP, unReleve.getHp());
    values.put(COL_HC, unReleve.getHc());
    values.put(COL_RAISON, unReleve.getRaison());
    //on insère l'objet dans la BDD via le ContentValues
    return db.insert(TABLE_RELEVE, null, values);
}

    private Releve cursorToReleve(Cursor c){ //Cette méthode permet de convertir un cursor en un relevé
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon
        c.moveToFirst();   //on se place sur le premier élément
        Releve unReleve = new Releve(null,null,null,null);  //On créé un relevé
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        unReleve.setNumcpt(c.getString(NUM_COL_NUMCPT));
        unReleve.setHp(c.getString(NUM_COL_HP));
        unReleve.setHc(c.getString(NUM_COL_HC));
        unReleve.setRaison(c.getString(NUM_COL_RAISON));
        c.close();     //On ferme le cursor
        return unReleve;  //On retourne le relevé
    }

    public Releve getReleveWithNumCpt(String numcpt){
        //Récupère dans un Cursor les valeurs correspondant à un relevé grâce au numéro de compteur
        Cursor c = db.query(TABLE_RELEVE, new String[] {COL_IDRELEVE,COL_NUMCPT, COL_HP, COL_HC, COL_RAISON}, COL_NUMCPT + " = \"" + numcpt +"\"", null, null, null, null);
        return cursorToReleve(c);
    }



    public Cursor getDataReleve(){
        return db.rawQuery("SELECT * FROM treleve", null);
    }

}




