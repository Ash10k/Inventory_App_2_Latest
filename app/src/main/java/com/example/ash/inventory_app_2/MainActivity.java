package com.example.ash.inventory_app_2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ash.inventory_app_2.InventoryContract.inventoryHelper;

import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.CONTENT_URI;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.ID;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.PRICE;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.PRODUCTNAME;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.QUANTITY;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.SUPPLIER_NAME;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.SUPPLIER_PHONE_NUMBER;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
public inventoryHelper dBhelper;
    inventoryCursorAdapter mCursorAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.listview);
        dBhelper=new inventoryHelper(this);

        mCursorAdapter=new inventoryCursorAdapter(this,null);
        listView.setAdapter(mCursorAdapter);

        getSupportLoaderManager().initLoader(0,null,this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Editor.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


        @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        String[] projection={ID,PRODUCTNAME,PRICE,QUANTITY,SUPPLIER_NAME,SUPPLIER_PHONE_NUMBER};
        return new CursorLoader(this,CONTENT_URI,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

}
