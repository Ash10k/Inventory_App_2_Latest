package com.example.ash.inventory_app_2;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ash.inventory_app_2.InventoryContract.inventoryContract;

import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.CONTENT_URI;

public class Editor extends AppCompatActivity {
    public EditText product, price, quantity, supplier, supplier_phone;
    public String val_product, val_quantity, val_supplier, val_supplier_phone, val_price;
    public Button save;
    private boolean mHasChanged=true;
    private View.OnTouchListener mTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mHasChanged=true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        product = findViewById(R.id.eProduct);
        price = findViewById(R.id.ePrice);
        quantity = findViewById(R.id.eQuantity);
        supplier = findViewById(R.id.eSupplierName);
        supplier_phone = findViewById(R.id.eSupplierPhone);
        save=findViewById(R.id.save_data);

        product.setOnTouchListener(mTouchListener);
        price.setOnTouchListener(mTouchListener);
        quantity.setOnTouchListener(mTouchListener);
        supplier.setOnTouchListener(mTouchListener);
        supplier_phone.setOnTouchListener(mTouchListener);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDATA();
            }
        });

    }

    private void insertDATA() {

        val_product = product.getText().toString().trim();
        val_price = price.getText().toString().trim();
        val_quantity = quantity.getText().toString().trim();
        val_supplier = supplier.getText().toString().trim();
        val_supplier_phone = supplier_phone.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(inventoryContract.InventoryData.PRODUCTNAME, val_product);
        values.put(inventoryContract.InventoryData.PRICE, val_price);
        values.put(inventoryContract.InventoryData.QUANTITY, val_quantity);
        values.put(inventoryContract.InventoryData.SUPPLIER_NAME, val_supplier);
        values.put(inventoryContract.InventoryData.SUPPLIER_PHONE_NUMBER, val_supplier_phone);
        Uri result = getContentResolver().insert(CONTENT_URI, values);

        if (result!=null)
            Toast.makeText(getApplicationContext(),"SuccessFully Inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Failure To Insert",Toast.LENGTH_SHORT).show();

    }
}
