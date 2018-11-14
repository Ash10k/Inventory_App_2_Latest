package com.example.ash.inventory_app_2.InventoryContract;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.CONTENT_ITEM_TYPE;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.CONTENT_LIST_TYPE;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.ID;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.PRICE;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.PRODUCTNAME;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.QUANTITY;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.SUPPLIER_NAME;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.SUPPLIER_PHONE_NUMBER;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.TABLE_NAME;

public class MyContentProvider extends ContentProvider {

    private inventoryHelper helper;

    public static String LOG_TAG=MyContentProvider.class.getSimpleName();

    private static final int INVENTORY=100;
    private static final int INVENTORY_ID=101;

    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(inventoryContract.CONTENT_AUTHORITY,inventoryContract.PATH_INVENTORY,INVENTORY);
        sUriMatcher.addURI(inventoryContract.CONTENT_AUTHORITY,inventoryContract.PATH_INVENTORY+"/#",INVENTORY_ID);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = helper.getWritableDatabase();
        int rowsDeleted;
        /*int val = database.delete(TABLE_NAME, selection, selectionArgs);
        return val;*/

        final int match=sUriMatcher.match(uri);
        switch (match){
            case INVENTORY:
               rowsDeleted=database.delete(TABLE_NAME, selection, selectionArgs);
               break;
            case INVENTORY_ID:
                selection=ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted=database.delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:throw new IllegalArgumentException("Delete Not Supported For "+uri);
        }
        if (rowsDeleted!=0)
            getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return CONTENT_LIST_TYPE;
            case INVENTORY_ID:
                return CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown uri " + uri + "and match " + match);

        }
    }
    
    @Override
    public Uri insert(Uri uri, ContentValues values) {
    final int match=sUriMatcher.match(uri);
        switch (match){
            case INVENTORY:
                return insertData(uri,values);
                default:throw new IllegalArgumentException("Insertion Not Supported For "+uri);
        }
    }

    //complete
    private Uri insertData(Uri uri, ContentValues values) {
        String productName=values.getAsString(PRODUCTNAME);
        if (productName==null){
            throw new IllegalArgumentException("Product Name Is Required");
        }

        String Price=values.getAsString(PRICE);
        if (Price==null){
            throw new IllegalArgumentException("Price Name Is Required");
        }

        String quantity=values.getAsString(QUANTITY);
        if (quantity==null){
            throw new IllegalArgumentException("Quantity Name Is Required");
        }

        String Supplier=values.getAsString(SUPPLIER_NAME);
        if (Supplier==null){
            throw new IllegalArgumentException("Supplier Name Is Required");
        }

        String Supplier_phone=values.getAsString(SUPPLIER_PHONE_NUMBER);
        if (Supplier_phone==null){
            throw new IllegalArgumentException("Supplier Phone Number Is Required");
        }
        SQLiteDatabase database = helper.getWritableDatabase();
        long id = database.insert(TABLE_NAME, null, values);
        if (id==-1) {
            Log.e(LOG_TAG, "Failed To Insert Row For " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);

    }

    @Override  //complete
    public boolean onCreate() {
        helper = new inventoryHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database=helper.getReadableDatabase();
        Cursor cursor;
        int match=sUriMatcher.match(uri);
        switch (match){
            case INVENTORY:
                cursor=database.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case INVENTORY_ID:
                selection=ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=database.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
                default:throw new IllegalArgumentException("Query Not Supported For "+uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        final int match=sUriMatcher.match(uri);
        switch (match){
            case INVENTORY:
               return updateData(uri,values,selection,selectionArgs);

            case INVENTORY_ID:
                selection=ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateData(uri,values,selection,selectionArgs);
            default:throw new IllegalArgumentException("Update Not Supported For "+uri);
        }
    }


    //complete
    private int updateData(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(PRODUCTNAME)){
            String productName=values.getAsString(PRODUCTNAME);
            if (productName==null){
                throw new IllegalArgumentException("Product Name Is Required");
            }
        }

        if (values.containsKey(PRICE)){
            String Price=values.getAsString(PRICE);
            if (Price==null){
                throw new IllegalArgumentException("Price Name Is Required");
            }
        }

        if (values.containsKey(QUANTITY)){
            String Price=values.getAsString(QUANTITY);
            if (Price==null){
                throw new IllegalArgumentException("Quantity Is Required");
            }
        }
        if (values.containsKey(SUPPLIER_NAME)){
            String Price=values.getAsString(SUPPLIER_NAME);
            if (Price==null){
                throw new IllegalArgumentException("Supplier Name Is Required");
            }
        }
        if (values.containsKey(SUPPLIER_PHONE_NUMBER)){
            String Price=values.getAsString(SUPPLIER_PHONE_NUMBER);
            if (Price==null){
                throw new IllegalArgumentException("Supplier Phone Is Required");
            }
        }

        if (values.size()==0){
            return 0;
        }
        SQLiteDatabase database = helper.getWritableDatabase();
        int rowsUpdated = database.update(TABLE_NAME, values, selection, selectionArgs);
        if (rowsUpdated==-0) {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }
}
