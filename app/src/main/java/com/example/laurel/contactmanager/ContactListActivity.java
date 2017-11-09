package com.example.laurel.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class ContactListActivity extends Activity implements ContactListFragment.Callbacks {
   static FileIO file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        //Create a new static file from FileIO class
        file = new FileIO();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemSelected(String id) {
        // Callback method from {@link ContactListFragment.Callbacks}
        // indicating that the item with the given ID was selected.
        Intent detailIntent = new Intent(this, ContactDetailActivity.class);
        detailIntent.putExtra(ContactDetailFragment.ARG_ITEM_ID, id);
        startActivity(detailIntent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //what to do for the add button
        switch (item.getItemId()) {
            case R.id.action_add:
                //Launch new inent to add a contact
                Intent launchNewIntent = new Intent(this, NewContactActivity.class);
                startActivity(launchNewIntent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
