package com.example.mex;

import android.app.ListFragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;

public class ContactFragment extends ListFragment {

	SimpleCursorAdapter adapter;
	static final String[] COLUMNS = new String[] {ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
	static final String SELECTION = "((" + ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" + ContactsContract.Data.DISPLAY_NAME + " != '' ))";
	static final String[] values = {"HELLO", "MENTE", "JOAN", "TALYA"};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}
	
	/*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String[] from = {ContactsContract.Data.DISPLAY_NAME};
		int[] to = {android.R.id.text1};
		
    	View view = inflater.inflate(R.layout.activity_contact, container, false);
    	return view;
    }*/
}
