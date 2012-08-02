package com.example.mex;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.widget.SimpleCursorAdapter;

public class ContactFragment extends ListFragment implements LoaderCallbacks<Cursor> {

	SimpleCursorAdapter adapter;
	String[] PROJECTION = { Contacts._ID,
	        Contacts.DISPLAY_NAME,
	        Contacts.CONTACT_STATUS,
	        Contacts.CONTACT_PRESENCE,
	        Contacts.PHOTO_ID,
	        Contacts.LOOKUP_KEY, };
	String sort = ContactsContract.Contacts.DISPLAY_NAME + "COLLATE LOCALIZED ASC";
	String[] COLUMNS = new String[] {Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS};
	String SELECTION = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
                + Contacts.DISPLAY_NAME + " != '' ))";
	static final String[] values = {"HELLO", "MENTE", "JOAN", "TALYA"};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, null, COLUMNS , new int[] {android.R.id.text1, android.R.id.text2}, 0);
		setListAdapter(adapter);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	public Loader<Cursor> onCreateLoader( int id, Bundle args ) {
		
		return new CursorLoader(getActivity(), ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, null, sort);
	}
	
	public void onLoadFinished( Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor( cursor );
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
