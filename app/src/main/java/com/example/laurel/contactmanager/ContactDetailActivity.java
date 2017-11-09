package com.example.laurel.contactmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laurel.contactmanager.contactapp.ContactManagerContent;


import static com.example.laurel.contactmanager.contactapp.ContactManagerContent.addItem;

public class ContactDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ContactDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ContactDetailFragment.ARG_ITEM_ID));
            ContactDetailFragment fragment = new ContactDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.contact_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //what to do when each menu item is selected: save, delete, home
        switch (item.getItemId()) {
            case R.id.action_delete:
                //dialog box for deleting a contact
                new AlertDialog.Builder(this)
                        .setTitle("Delete contact")
                        .setMessage("Are you sure you want to delete this contact?")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                deleteItem();
                                //User message
                                Toast.makeText(getBaseContext(), "Contact deleted", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

                break;
            case R.id.action_save:
                if(editItem()) {
                    //if statement checks for at least a first name

                    //User message
                    Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT)
                            .show();
                    //Go back home
                    NavUtils.navigateUpTo(this, new Intent(this, ContactListActivity.class));
                }
                break;
            case android.R.id.home:
                //Go home
                NavUtils.navigateUpTo(this, new Intent(this, ContactListActivity.class));

                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteItem() {
        //Remove items from data structure
        ContactDetailFragment.removeItem();
        //Save file with removed item
        ContactListActivity.file.save();
        //Go back home
        NavUtils.navigateUpTo(this, new Intent(this, ContactListActivity.class));
    }


    public boolean editItem() {

        //Extract text from text fields

        boolean returnValue = false;
        String id = ContactDetailFragment.getTheID();
        ContactDetailFragment.removeItem();

        String first, last, phone, email;
        EditText fEdit, lEdit, pEdit, eEdit;

        fEdit = (EditText) findViewById(R.id.edit_first);
        lEdit = (EditText) findViewById(R.id.edit_last);
        pEdit = (EditText) findViewById(R.id.edit_phone);
        eEdit = (EditText) findViewById(R.id.edit_email);

        first = fEdit.getText().toString();
        last = lEdit.getText().toString();
        phone = pEdit.getText().toString();
        email = eEdit.getText().toString();

        //Must have at least a first name
        if(first.isEmpty()) {
            Toast.makeText(this, "Must include a first name", Toast.LENGTH_SHORT)
                    .show();
            returnValue = false;
        }

        //If a first name is entered, appened edited contact and save the file
        if(!first.isEmpty()) {
            addItem(new ContactManagerContent.Person(id, first, last, phone, email));

            ContactListActivity.file.save();

            returnValue = true;
        }

        return returnValue;

    }

}
