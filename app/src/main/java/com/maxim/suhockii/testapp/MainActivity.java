package com.maxim.suhockii.testapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.maxim.suhockii.testapp.catalog.Category;
import com.maxim.suhockii.testapp.catalog.Offer;
import com.maxim.suhockii.testapp.catalog.YmlCatalog;
import com.maxim.suhockii.testapp.fragments.CategoriesFragment;
import com.maxim.suhockii.testapp.fragments.ContactsFragment;
import com.maxim.suhockii.testapp.fragments.DishesFragment;

import java.sql.SQLException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CategoriesFragment.OnFragmentInteractionListener,
        DishesFragment.OnFragmentInteractionListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    CategoriesFragment categoriesFragment;
    DishesFragment dishesFragment;
    ContactsFragment contactsFragment;
    YmlCatalog catalog;
    Context context;
    ProgressDialog progressDialog;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        categoriesFragment = new CategoriesFragment();
        dishesFragment = new DishesFragment();
        contactsFragment = new ContactsFragment();
        initView();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        loadFragment();


        updateData();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    private void loadFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, categoriesFragment)
                .commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_catalog) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, categoriesFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_contacts) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, contactsFragment)
                    .addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateData() {
        try {
            if (DBSingletone.getHelper().getCategoryDAO().countOf() == 0) {
                progressDialog = ProgressDialog.show(this, "Минуточку", "Выполняется загрузка меню...", true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("http://ufa.farfor.ru")
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        ApiService apiService = restAdapter.create(ApiService.class);
        apiService.getUser().enqueue(new Callback<YmlCatalog>() {
            @Override
            public void onResponse(Call<YmlCatalog> call, Response<YmlCatalog> response) {
                catalog = response.body();
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                CategoryDAO categoryDAO = DBSingletone.getHelper().getCategoryDAO();
                OfferDAO offerDAO = DBSingletone.getHelper().getOfferDAO();
                categoryDAO.clear();
                offerDAO.clear();
                for (Category category : response.body().shop.mCategories.mCategoryForAdapterList) {
                    try {
                        categoryDAO.create(category);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                for (Offer offer : response.body().shop.mOffers.mOfferList) {
                    try {
                        offer.category = categoryDAO.queryForId(offer.mCategoryId);
                        offerDAO.create(offer);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

//                RecyclerViewAdapter adapter = new RecyclerViewAdapter(catalog, context);
            }

            @Override
            public void onFailure(Call<YmlCatalog> call, Throwable t) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onFragmentCategoriesInteraction(int position) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, dishesFragment)
                .addToBackStack(null)
                .commit();
        Bundle bundle = new Bundle();
        bundle.putInt(DishesFragment.ARG_PARAM, position);
        dishesFragment.setArguments(bundle);
    }

    @Override
    public void onFragmentDishesInteraction(Uri uri) {

    }
}
