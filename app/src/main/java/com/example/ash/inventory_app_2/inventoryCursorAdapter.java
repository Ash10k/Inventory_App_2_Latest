package com.example.ash.inventory_app_2;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.PRICE;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.PRODUCTNAME;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.QUANTITY;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.SUPPLIER_NAME;
import static com.example.ash.inventory_app_2.InventoryContract.inventoryContract.InventoryData.SUPPLIER_PHONE_NUMBER;

public class inventoryCursorAdapter extends CursorAdapter{
    TextView pname,ptitle,pPrice,pQuantity,pPhone;

    public inventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v= LayoutInflater.from(context).inflate(R.layout.my_custom_layout,viewGroup,false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        pname=view.findViewById(R.id.ProductName);
        ptitle=view.findViewById(R.id.Supplier);
        pPhone=view.findViewById(R.id.supp_phone);
        pPrice=view.findViewById(R.id.price);
        pQuantity=view.findViewById(R.id.quan);
       // Sale=view.findViewById(R.id.sale_quan);

        String product=cursor.getString(cursor.getColumnIndexOrThrow(PRODUCTNAME));
        String supplier=cursor.getString(cursor.getColumnIndexOrThrow(SUPPLIER_NAME));
        String supplier_phone=cursor.getString(cursor.getColumnIndexOrThrow(SUPPLIER_PHONE_NUMBER));
        String price=cursor.getString(cursor.getColumnIndexOrThrow(PRICE));
        String quantity=cursor.getString(cursor.getColumnIndexOrThrow(QUANTITY));

        if(TextUtils.isEmpty(product)){
            pname.setText(context.getString(R.string.unknown));
        }
        if(TextUtils.isEmpty(price)){
            pPrice.setText(context.getString(R.string.unknown));
        }
        pname.setText(product);
        ptitle.setText(supplier);
        pPhone.setText(supplier_phone);
        pPrice.setText(price);
        pQuantity.setText(quantity);
    }
}
