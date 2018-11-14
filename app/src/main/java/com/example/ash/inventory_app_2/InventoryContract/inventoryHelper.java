package com.example.ash.inventory_app_2.InventoryContract;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class inventoryHelper extends SQLiteOpenHelper {
    public static final String databaseName="inven.db";
    public static final String LOG_TAG=inventoryHelper.class.getSimpleName();

    public inventoryHelper(Context context) {
        super(context, databaseName, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
           /* String create_database="CREATE TABLE "+inventoryContract.InventoryData.TABLE_NAME+"("+ inventoryContract.InventoryData.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    inventoryContract.InventoryData.PRODUCTNAME+" TEXT NOT NULL, "+inventoryContract.InventoryData.PRICE+" TEXT NOT NULL, "+ inventoryContract.InventoryData.QUANTITY + " INTEGER NOT NULL DEFAULT 0 ,"
                    +inventoryContract.InventoryData.SUPPLIER_NAME +" TEXT NOT NULL, "+ inventoryContract.InventoryData.SUPPLIER_PHONE_NUMBER +" TEXT );";
            db.execSQL(create_database);*/

        String create_database="CREATE TABLE "+inventoryContract.InventoryData.TABLE_NAME+"("+ inventoryContract.InventoryData.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                inventoryContract.InventoryData.PRODUCTNAME+" TEXT NOT NULL, "+inventoryContract.InventoryData.PRICE+" TEXT NOT NULL, "+ inventoryContract.InventoryData.QUANTITY + " INTEGER NOT NULL DEFAULT 0 ,"
                +inventoryContract.InventoryData.SUPPLIER_NAME +" TEXT NOT NULL, "+ inventoryContract.InventoryData.SUPPLIER_PHONE_NUMBER +" TEXT );";
        db.execSQL(create_database);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
