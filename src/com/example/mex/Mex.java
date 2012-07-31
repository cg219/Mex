package com.example.mex;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;

public class Mex extends Activity {

	protected FragmentManager fragBoss = getFragmentManager();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mex);
        
        if(findViewById(R.id.fragmentContainer) != null) {
        	if(savedInstanceState != null)
        		return;
        	
        	ContactFragment contactFrag = new ContactFragment();
        	contactFrag.setArguments(getIntent().getExtras());
        	
        	fragBoss.beginTransaction().add(R.id.fragmentContainer, contactFrag).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mex, menu);
        return true;
    }
}
