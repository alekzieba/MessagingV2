package com.alekapps.messagingv2;

import java.text.DateFormat;

import java.util.Date;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.alekapps.messagingv2.Item;

public class MainActivity extends Activity {
	
	ArrayList<Item> items = new ArrayList<Item>();
	Item message;
	ListView listView;
	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Override current transition
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		
		setContentView(R.layout.activity_main);
		
		
		workWithAdapter();
		
		registerForContextMenu(listView);
		
		// Make the view always scroll to the bottom.
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		
		Intent intent = getIntent();
		String name = intent.getStringExtra(LoginActivity.ACCOUNT_NAME);
		
		setTitle(name);
	}
	
	
	
	/** Get the adapter working */
	private void workWithAdapter() {
		// 1. pass context and data to the custom adapter
        adapter = new MyAdapter(this, generateData());
 
        // 2. Get ListView from activity_main.xml
        listView = (ListView) findViewById(R.id.list);
        
     // 3. setListAdapter
        listView.setAdapter(adapter);
	}
	
	
	
	/** Add the messages to the array. */
	private ArrayList<Item> generateData(){
        
		if (message != null) {
			items.add(message);
		}
 
        return items;
    }

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	
	/** Get the current time. */
	public String currentTime() {
		
		DateFormat df = DateFormat.getDateTimeInstance (DateFormat.MEDIUM, DateFormat.MEDIUM, new Locale ("en", "EN"));
		String formattedDate = df.format (new Date ());
    	return formattedDate;
	}
	
	
	
	
	/** Send a message */
	public void sendMessage(View view) {
		
		// Extract the message from the text area.
		EditText messageTextArea = (EditText) findViewById(R.id.message_area);
		String messageText = messageTextArea.getText().toString().trim();
		if (!messageText.equals("")) {
			message = new Item(messageText, currentTime());
			
			// Display the message in the list view.
			workWithAdapter();
		}
		
		// Clear the text box.
		messageTextArea.setText("");
		
		// Scroll the list view to the bottom.
		scrollMyListViewToBottom();
		
	}
	
	
	
	
	/** Scroll the listview to the bottom. */
	private void scrollMyListViewToBottom() {
	    listView.post(new Runnable() {
	        @Override
	        public void run() {
	            // Select the last row so it will scroll into view...
	            listView.setSelection(adapter.getCount() - 1);
	        }
	    });
	}
	
	
	
	
	
	/** Necessary for context view. */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.item_context_menu, menu);
	}
	
	
	
	
	
	
	/** Hook up context view items to their respective methods. */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    switch (item.getItemId()) {
	        case R.id.copy:
	        	TextView tv = (TextView) ((RelativeLayout) listView.getChildAt(info.position)).getChildAt(0);
	            copy(tv);
	            return true;
	        case R.id.delete:
	        	items.remove(info.position);
	            adapter.notifyDataSetChanged();
	            return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}
	
	
	
	
	
	/** Copy message to clipboard. */
	public void copy(TextView textView) {
		
		String text = textView.getText().toString();
		
		ClipboardManager clipboard = (ClipboardManager)
		        getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("simple text", text);
		
		clipboard.setPrimaryClip(clip);
		
	}

}
