package com.alekapps.messagingv2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity {
	
	public final static String ACCOUNT_NAME = "com.example.myfirstapp.MESSAGE";
	private String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the login button */
	public void login(View view) throws InterruptedException {
		TextView label = (TextView) findViewById(R.id.label);
		label.setText(R.string.loggingin);
		
		
		EditText accountText = (EditText) findViewById(R.id.username);
		EditText passwordText = (EditText) findViewById(R.id.password);
		Button loginButton = (Button) findViewById(R.id.login_button);
		
	    username = accountText.getText().toString();
	    
	    
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
	    
	    
	}

	public void doThis() {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(ACCOUNT_NAME, username);
		startActivity(intent);
	    finish();
	}
	
	
	
	
}
