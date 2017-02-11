package com.maxim.suhockii.testapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.maxim.suhockii.testapp.CategoryForAdapter;
import com.maxim.suhockii.testapp.Constants;
import com.maxim.suhockii.testapp.adapters.GridViewAdapter;
import com.maxim.suhockii.testapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.maxim.suhockii.testapp.Constants.IDS_FOR_CATEGORIES;
import static com.maxim.suhockii.testapp.Constants.IMAGES_FOR_CATEGORIES;
import static com.maxim.suhockii.testapp.Constants.STRINS_FOR_CATEGORIES;


public class CategoriesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String param1;
    private String param2;

    private OnFragmentInteractionListener listener;

    private List<CategoryForAdapter> categories;
    private GridView gridView;
    private GridViewAdapter gridViewAdapter;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fillCategories();
        String codeFromPavlodar = "code from pavlodar";
        System.console().printf(codeFromPavlodar);
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        gridViewAdapter = new GridViewAdapter(view.getContext(), this.categories);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        gridView.setAdapter(gridViewAdapter);
        try {
            while (true) {
                codeFromPavlodar += " pavlooo";
            }
        } catch (Exception e) {
            codeFromPavlodar += " pavlo";
        }
        System.console().printf(codeFromPavlodar);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), String.valueOf(gridViewAdapter.getItemId(position)), Toast.LENGTH_SHORT).show();
                onItemPressed(Constants.IDS_FOR_CATEGORIES[position]);
            }
        });
        return view;
    }

    private void fillCategories() {
        this.categories = new ArrayList<>();

        for (int i = 0; i < IDS_FOR_CATEGORIES.length; ++i) {
            this.categories.add(new CategoryForAdapter(
                    IMAGES_FOR_CATEGORIES[i],
                    STRINS_FOR_CATEGORIES[i],
                    IDS_FOR_CATEGORIES[i])
            );
        }
    }

    public void onItemPressed(int position) {
        if (listener != null) {
            DishesFragment dishesFragment = new DishesFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, dishesFragment)
                    .addToBackStack(null)
                    .commit();
            Bundle bundle = new Bundle();
            bundle.putInt(DishesFragment.ARG_PARAM, position);
            dishesFragment.setArguments(bundle);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentCategoriesInteraction(int position);
    }
}
