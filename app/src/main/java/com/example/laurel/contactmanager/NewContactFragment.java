package com.example.laurel.contactmanager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewContactFragment extends Fragment {
    public static final String ARG_ITEM_ID = "stuff";
    public NewContactFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create the text fields for contact details
        View rootView = inflater.inflate(R.layout.activity_contact_new, container, false);
        container.removeAllViews();


        return rootView;
    }
}
