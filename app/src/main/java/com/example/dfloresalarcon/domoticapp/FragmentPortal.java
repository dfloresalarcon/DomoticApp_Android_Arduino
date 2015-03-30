package com.example.dfloresalarcon.domoticapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dfloresalarcon on 5/03/15.
 */
public class FragmentPortal extends Fragment {

    public FragmentPortal() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fargment_portal, container, false);
        return rootView;
    }

}
