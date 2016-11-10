package com.maxim.suhockii.testapp;

/**
 * Created by hzkto on 11/10/2016.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.maxim.suhockii.testapp.catalog.Category;
import com.maxim.suhockii.testapp.catalog.Offer;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "myappname.db";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private CategoryDAO categoryDAO = null;
    private OfferDAO offerDAO = null;

    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Offer.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.dropTable(connectionSource, Offer.class, true);

            onCreate(database, connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public CategoryDAO getCategoryDAO() throws SQLException {
        if (categoryDAO == null) try {
            categoryDAO = new CategoryDAO(getConnectionSource(), Category.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return categoryDAO;
    }

    public OfferDAO getOfferDAO() throws SQLException {
        if (offerDAO == null) try {
            offerDAO = new OfferDAO(getConnectionSource(), Offer.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return offerDAO;
    }


    public void close() {
        super.close();
    }
}
