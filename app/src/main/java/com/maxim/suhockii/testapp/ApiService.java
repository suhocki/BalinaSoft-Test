package com.maxim.suhockii.testapp;


import com.maxim.suhockii.testapp.catalog.YmlCatalog;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hzkto on 11/9/2016.
 */

public interface ApiService {
    @GET("/getyml/?key=ukAXxeJYZN")
    Call<YmlCatalog> getUser();
}
