package com.example.servicebinddemo;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button bt1,bt2,bt3,bt4;
    TextView tv1;
    Intent myIt=new Intent("gdmec07131030");
    BoundService myboundservice;
    ServiceConnection mConnection=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			myboundservice=((BoundService.LocalBinder)arg1).getService();
			
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			
			
		}
    	
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1=(TextView) findViewById(R.id.textView1);
		bt1=(Button) findViewById(R.id.button1);
		bt2=(Button) findViewById(R.id.button2);
		bt3=(Button) findViewById(R.id.button3);
		bt4=(Button) findViewById(R.id.button4);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				bindService(myIt,mConnection,Context.BIND_AUTO_CREATE);
			}
		});
        bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				unbindService(mConnection);
			}
		});
        bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				long a=Math.round(Math.random()*100);
				long b=Math.round(Math.random()*100);
				
				long avg=myboundservice.Avg(a, b);
				tv1.setText(String.valueOf(avg));
				
			}
		});
        bt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String str=String.valueOf(myboundservice.PI);
				tv1.setText(str);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
