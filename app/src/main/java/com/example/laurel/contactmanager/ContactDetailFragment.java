package com.example.laurel.contactmanager;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.laurel.contactmanager.contactapp.ContactManagerContent;

public class ContactDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "stuff";
    private static ContactManagerContent.Person mItem;

    public ContactDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Retrive Person object from List
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = ContactManagerContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Create the text fields for contact details from Person object
        View rootView = inflater.inflate(R.layout.activity_contact_detail, container, false);
        //Remove overlapping or unnecessary views
        container.removeAllViews();

        if (mItem != null) {
            ((EditText) rootView.findViewById(R.id.edit_first)).setText(mItem.firstName);
            ((EditText) rootView.findViewById(R.id.edit_last)).setText(mItem.lastName);
            ((EditText) rootView.findViewById(R.id.edit_phone)).setText(mItem.phoneNumber);
            ((EditText) rootView.findViewById(R.id.edit_email)).setText(mItem.email);
        }

        return rootView;
    }

    public static String getTheID() {
        //Prior to removing an edited item, get it's id so that the same position is saved
        return mItem.id;
    }

    public static void removeItem() {
        //Remove selected Person object from corresponding data stucture
        ContactManagerContent.removeItem(mItem);

    }
}
