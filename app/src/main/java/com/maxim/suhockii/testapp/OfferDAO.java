package com.maxim.suhockii.testapp;

import android.database.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.maxim.suhockii.testapp.catalog.Offer;

/**
 * Created by hzkto on 11/10/2016.
 */

public class OfferDAO extends BaseDaoImpl<Offer, Long> {
    public void clear() {
        try {
            this.delete(this.queryForAll());
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public OfferDAO(ConnectionSource connectionSource, Class<Offer> dataClass) throws SQLException, java.sql.SQLException {
        super(connectionSource, dataClass);
    }
}
