package igl.vel.isr.appdef1_0.Store_Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import igl.vel.isr.appdef1_0.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Arduino extends Fragment {


    public Arduino() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arduino, container, false);
    }

}
