package com.maxim.suhockii.testapp;

import android.database.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.maxim.suhockii.testapp.catalog.Category;

/**
 * Created by hzkto on 11/10/2016.
 */

public class CategoryDAO extends BaseDaoImpl<Category, Long> {
    public void clear() {
        try {
            this.delete(this.queryForAll());
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public CategoryDAO(ConnectionSource connectionSource, Class<Category> dataClass) throws SQLException, java.sql.SQLException {
        super(connectionSource, dataClass);
    }
}
