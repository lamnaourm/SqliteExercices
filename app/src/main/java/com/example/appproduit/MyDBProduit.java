package com.example.appproduit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBProduit extends SQLiteOpenHelper {

    public static String DB_NAME = "PRODUIT.db";
    public static String TABLE_NAME = "PRODUIT";
    public static String COL1 = "ID";
    public static String COL2 = "LIBELLE";
    public static String COL3 = "FAMILLE";
    public static String COL4 = "PRIX_ACHAT";
    public static String COL5 = "PRIX_VENTE";


    public MyDBProduit(Context c) {
        super(c, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " integer primary key autoincrement," + COL2 + " text," + COL3 + " text," + COL4 + " double," + COL5 + " double)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public static long insertProduit(SQLiteDatabase sql, Produit p){
        ContentValues c = new ContentValues();
        c.put(COL2,p.getLibelle());
        c.put(COL3,p.getFamille());
        c.put(COL4,p.getPrixAchat());
        c.put(COL5,p.getPrixVente());
        return sql.insert(TABLE_NAME,null,c);
    }
    public static long updateProduit(SQLiteDatabase sql, Produit p){
        ContentValues c = new ContentValues();
        c.put(COL2,p.getLibelle());
        c.put(COL3,p.getFamille());
        c.put(COL4,p.getPrixAchat());
        c.put(COL5,p.getPrixVente());
        return sql.update(TABLE_NAME,c,"ID = " + p.getId(),null);
    }
    public static long deleteProduit(SQLiteDatabase sql, int id){
        return sql.delete(TABLE_NAME,"ID = " + id,null);
    }
    public static ArrayList<Produit> getAllProduits(SQLiteDatabase sql){
        ArrayList<Produit> prds = new ArrayList<>();

        Cursor cur = sql.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        while(cur.moveToNext()){
            Produit p = new Produit();

            p.setId(cur.getInt(0));
            p.setLibelle(cur.getString(1));
            p.setFamille(cur.getString(2));
            p.setPrixAchat(cur.getDouble(3));
            p.setPrixVente(cur.getDouble(4));

            prds.add(p);
        }
        return prds;
    }
    public static Produit getAOneProduit(SQLiteDatabase sql, int id){
        Produit p = null;

        Cursor cur = sql.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE ID="+id ,null);

        if(cur.moveToNext()){
            p.setId(cur.getInt(0));
            p.setLibelle(cur.getString(1));
            p.setFamille(cur.getString(2));
            p.setPrixAchat(cur.getDouble(3));
            p.setPrixVente(cur.getDouble(4));
        }
        return p;
    }

}
