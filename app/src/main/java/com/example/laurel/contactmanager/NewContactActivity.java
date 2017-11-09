package com.example.laurel.contactmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laurel.contactmanager.contactapp.ContactManagerContent;


import static com.example.laurel.contactmanager.contactapp.ContactManagerContent.addItem;

public class NewContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_new);
        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //what to do when each menu item is selected: save
        switch (item.getItemId()) {
            case R.id.action_save_new:
                if(saveItem()) {
                    //if statement checks if at least a first name is entered
                    //User message
                    Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT)
                            .show();
                    //Go back home
                    NavUtils.navigateUpTo(this, new Intent(this, ContactListActivity.class));
                }
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean saveItem() {

        //Get text from text fields
        boolean returnValue = false;
        EditText fEdit, lEdit, pEdit, eEdit;
        String first, last, phone, email;
        fEdit = (EditText) findViewById(R.id.edit_first_new);
        lEdit = (EditText) findViewById(R.id.edit_last_new);
        pEdit = (EditText) findViewById(R.id.edit_phone_new);
        eEdit = (EditText) findViewById(R.id.edit_email_new);

        String id = Integer.toString(ContactManagerContent.ITEMS.size() + 1);
        first = fEdit.getText().toString();
        last = lEdit.getText().toString();
        phone = pEdit.getText().toString();
        email = eEdit.getText().toString();

        //At least have a first name entered
        if(first.isEmpty()) {
            Toast.makeText(this, "Must include a first name", Toast.LENGTH_SHORT)
                    .show();
            returnValue = false;
        }


        //If at least a first name is entered, add item and save
        if(!first.isEmpty()) {
            addItem(new ContactManagerContent.Person(id, first, last, phone, email));

            ContactListActivity.file.save();

            returnValue = true;
        }

        return returnValue;

    }

}

