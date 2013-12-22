package com.alekapps.messagingv2;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.drawable.ColorDrawable;


public class LoginActivity extends Activity {
	RelativeLayout ll;
	public final static String ACCOUNT_NAME = "com.example.myfirstapp.MESSAGE";
	public final static String BACKGROUND_COLOR = "com.example.myfirstapp.BACKGROUND";
	private String username;
	public TextView label;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ll = (RelativeLayout) findViewById(R.id.login);
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.red:
	            ll.setBackgroundColor(Color.RED);
	            return true;
	        case R.id.green:
	        	ll.setBackgroundColor(Color.GREEN);
	            return true;
	        case R.id.yellow:
	        	ll.setBackgroundColor(Color.YELLOW);
	            return true;
	        case R.id.gray:
	        	ll.setBackgroundColor(Color.GRAY);
	            return true;
	        case R.id.white:
	        	ll.setBackgroundColor(Color.WHITE);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.color_menu, menu);
	    return true;
	}
	
	/** Called when the user clicks the login button */
	public void login(View view) throws InterruptedException {
		
		
		
		EditText accountText = (EditText) findViewById(R.id.username);
				
		EditText passwordText = (EditText) findViewById(R.id.password);
		Button loginButton = (Button) findViewById(R.id.login_button);
		
	    username = accountText.getText().toString().trim();
	    
	    if (username.isEmpty()) {
	    	return;
	    }
	    
	    label = (TextView) findViewById(R.id.label);
		label.setText(R.string.loggingin);
	    
	    accountText.setFocusable(false);
	    passwordText.setFocusable(false);
	    loginButton.setFocusable(false);
	    
	    
	    
	    Handler handler = new Handler();
	    
	    handler.postDelayed(new Runnable() {
	                @Override
	                public void run() {
	                    doThis();
	                }
	            }, 5000);
	    
	    handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateLabel();
            }
        }, 1000);
	    
	    handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateLabel();
            }
        }, 2000);
	    
	    handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateLabel();
            }
        }, 3000);
	    
	}

	public void doThis() {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(ACCOUNT_NAME, username);
		intent.putExtra(BACKGROUND_COLOR, ((ColorDrawable) ll.getBackground()).getColor());
		startActivity(intent);
	    finish();
	}
	
	public void updateLabel() {
		label.setText(label.getText() + ".");
	}
	
	
	
}
