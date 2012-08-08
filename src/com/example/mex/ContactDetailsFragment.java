package com.example.mex;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContactDetailsFragment extends Activity implements LoaderCallbacks<Cursor>{
	
	Uri NUMBER_LOOKUP_URI = ContactsContract.Data.CONTENT_URI;
	String[] NUMBER_COLUMNS = {ContactsContract.Data.CONTACT_ID};
	String[] NUMBER_PROJECTION = {ContactsContract.Data.CONTACT_ID };
	String NUMBER_SELECTION = ContactsContract.Data.CONTACT_ID + " = ";
	
	SimpleCursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        
        setContentView(R.layout.activity_contact_details_fragment);
        
        if( intent != null )
        {
			String displayNameText = intent.getStringExtra( ContactFragment.SELECTED_ROW_NAME );
			String id = intent.getStringExtra( ContactFragment.SELECTED_ROW_ID );
        		( (TextView) findViewById(R.id.displayName) ).setText(displayNameText);
        		
        		//NUMBER_LOOKUP_URI = Uri.withAppendedPath( Uri.withAppendedPath( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, id), ContactsContract.Contacts.Entity.CONTENT_DIRECTORY );
        		NUMBER_SELECTION += id;
        		Log.v("MEX_DEBUG", NUMBER_SELECTION);
        		getLoaderManager().initLoader(1, null, this);
        		
        		adapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, null, NUMBER_COLUMNS , new int[] {android.R.id.text1}, 0);
        		
        		((ListView) findViewById(R.id.numberList)).setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_contact_details_fragment, menu);
        return true;
    }
    
    /**
	 * LoaderCallback Methods
	 */
	
	public Loader<Cursor> onCreateLoader( int id, Bundle args ) {
		return new CursorLoader(this, NUMBER_LOOKUP_URI, NUMBER_PROJECTION, null, null, null);
	}
	
	public void onLoadFinished( Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor( cursor );
		
		Log.v("HA",  "MORE HA");
	}
	
	public void onLoaderReset( Loader<Cursor> loader) {
		adapter.swapCursor( null );
	}
}
