package com.maxim.suhockii.testapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maxim.suhockii.testapp.DBSingletone;
import com.maxim.suhockii.testapp.R;
import com.maxim.suhockii.testapp.adapters.RecyclerViewAdapter;
import com.maxim.suhockii.testapp.catalog.YmlCatalog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DishesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DishesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DishesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM = "position";

    // TODO: Rename and change types of parameters
    private int position;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private YmlCatalog catalog;
    private RecyclerViewAdapter recyclerViewAdapter;

    public DishesFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment DishesFragment.
//     */
//    TODO: Rename and change types and number of parameters
//    public static DishesFragment newInstance(String param1, String param2) {
//        DishesFragment fragment = new DishesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM, param1);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_PARAM);
        }

        View view = inflater.inflate(R.layout.fragment_dishes, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        try {
            recyclerViewAdapter = new RecyclerViewAdapter(getContext(), new ArrayList(DBSingletone.getHelper().getCategoryDAO().queryForId((long) position).offers));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentDishesInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentDishesInteraction(Uri uri);
    }
}
