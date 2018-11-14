package com.example.ash.inventory_app_2.InventoryContract;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class inventoryContract {
    public inventoryContract() {}

    public static final String CONTENT_AUTHORITY="com.example.ash.inventory_app_2";
    public static final Uri BASE_CONTENT_URI=Uri.parse(CONTENT_AUTHORITY);
    public static final String PATH_INVENTORY="inventory-path";


    public static final class InventoryData implements BaseColumns {
        public static final String CONTENT_LIST_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+PATH_INVENTORY;

        public static final String CONTENT_ITEM_TYPE=ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+PATH_INVENTORY;

        public static final Uri CONTENT_URI= Uri.withAppendedPath(BASE_CONTENT_URI,PATH_INVENTORY);
        public static final String TABLE_NAME="inventoryData";
        public static final String ID=BaseColumns._ID;
        public static final String PRODUCTNAME="product_name";
        public static final String PRICE="Price";
        public static final String QUANTITY="Quantity";
        public static final String SUPPLIER_NAME="Supplier_Name";
        public static final String SUPPLIER_PHONE_NUMBER="supplier_Phone";




    }
}
