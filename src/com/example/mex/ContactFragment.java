package com.example.mex;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class ContactFragment extends ListFragment implements LoaderCallbacks<Cursor> {

	SimpleCursorAdapter adapter;
	//Uri URI = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, ContactsContract.Contacts.Entity.CONTENT_DIRECTORY);
	Uri URI = ContactsContract.Contacts.CONTENT_URI;
	String[] PROJECTION = { ContactsContract.Contacts.Entity._ID,
			ContactsContract.Contacts.Entity.DISPLAY_NAME_PRIMARY,
			ContactsContract.Contacts.Entity.TIMES_CONTACTED ,
			ContactsContract.Contacts.Entity.HAS_PHONE_NUMBER ,
			ContactsContract.Contacts.Entity.DATA1 };
	String sort = ContactsContract.Contacts.Entity.DISPLAY_NAME_PRIMARY + " ASC";
	String[] COLUMNS = new String[] {ContactsContract.Contacts.Entity.DISPLAY_NAME_PRIMARY, ContactsContract.Contacts.Entity.DATA1};
	String SELECTION = "((" + ContactsContract.Contacts.Entity.DISPLAY_NAME_PRIMARY + " NOTNULL)  AND ("
                + ContactsContract.Contacts.Entity.HAS_PHONE_NUMBER + " != 0 ))";
	/*String SELECTION = "((" + ContactsContract.CommonDataKinds.Phone.NUMBER + " NOTNULL)  AND ("
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " != '' ))";*/
	
	
	static final String[] values = {"HELLO", "MENTE", "JOAN", "TALYA"};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_expandable_list_item_2, null, COLUMNS , new int[] {android.R.id.text1, android.R.id.text2}, 0);
		setListAdapter(adapter);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	public Loader<Cursor> onCreateLoader( int id, Bundle args ) {
		
		return new CursorLoader(getActivity(), URI, PROJECTION, SELECTION, null, sort);
	}
	
	public void onLoadFinished( Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor( cursor );
		Log.v("HA",  "MORE HA");
	}
	
	public void onLoaderReset( Loader<Cursor> loader) {
		adapter.swapCursor( null );
	}
	
	/*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String[] from = {ContactsContract.Data.DISPLAY_NAME};
		int[] to = {android.R.id.text1};
		
    	View view = inflater.inflate(R.layout.activity_contact, container, false);
    	return view;
    }*/
}
