package com.example.mex;

import java.io.Console;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactFragment extends ListFragment implements LoaderCallbacks<Cursor> {

	SimpleCursorAdapter adapter;

	Uri NAME_URI = ContactsContract.Contacts.CONTENT_URI;
	Uri NUMBER_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	
	String[] NAME_PROJECTION = { ContactsContract.Contacts._ID,
			ContactsContract.Contacts.DISPLAY_NAME,
			ContactsContract.Contacts.HAS_PHONE_NUMBER  };
	String[] NUMBER_PROJECTION = { ContactsContract.CommonDataKinds.Phone._ID,
			ContactsContract.CommonDataKinds.Phone.NUMBER,
			ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME};

	String NAME_SORT = ContactsContract.Contacts.DISPLAY_NAME + " ASC";
	String NUMBER_SORT = ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME + " ASC";

	String[] NAME_COLUMNS = {ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID};
	String[] NUMBER_COLUMNS = {ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone._ID};

	String NAME_SELECTION = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL)  AND ("
                + ContactsContract.Contacts.HAS_PHONE_NUMBER + " != 0 ))";
	String NUMBER_SELECTION = "((" + ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME + " NOTNULL)  AND ("
            + ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + " != 0 ))";
	

	public final static String SELECTED_ROW_NAME = "com.imkreative.mex.NAME";
	public final static String SELECTED_ROW_ID = "com.imkreative.mex.ID";
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_expandable_list_item_2, null, NAME_COLUMNS , new int[] {android.R.id.text1, android.R.id.text2}, 0);
		setListAdapter(adapter);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int index, long id) {
		Cursor selectedRow = (Cursor) getListView().getItemAtPosition(index);
		String rowName = selectedRow.getString( selectedRow.getColumnIndex( ContactsContract.Contacts.DISPLAY_NAME) );
		String rowID = selectedRow.getString( selectedRow.getColumnIndex( ContactsContract.Contacts._ID) );
		Intent detailsIntent = new Intent(getActivity(), ContactDetailsFragment.class);
		detailsIntent.putExtra( SELECTED_ROW_NAME, rowName);
		detailsIntent.putExtra( SELECTED_ROW_ID, rowID);
		startActivity( detailsIntent );
		
		Log.v("TAG", "MESSAGE");
	}
	
	/**
	 * LoaderCallback Methods
	 */
	
	public Loader<Cursor> onCreateLoader( int id, Bundle args ) {
		
		if( id == 0)
		{
			return new CursorLoader(getActivity(), NAME_URI, NAME_PROJECTION, NAME_SELECTION, null, NAME_SORT);
		}
		else
		{
			return new CursorLoader(getActivity(), NUMBER_URI, NUMBER_PROJECTION, NUMBER_SELECTION, null, NUMBER_SORT);
		}
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
