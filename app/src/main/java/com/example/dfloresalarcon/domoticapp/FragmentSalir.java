package com.example.dfloresalarcon.domoticapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by dfloresalarcon on 5/03/15.
 */
public class FragmentSalir extends Fragment {

    public FragmentSalir() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fargment_salir, container, false);

        return rootView;
    }

}
