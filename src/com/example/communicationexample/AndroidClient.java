package com.example.communicationexample;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * 
 * This is an application to connect the server with the android client . and transfer text message between server and client
 * to use this code replace the ip address with your ip address 
 * */
@SuppressWarnings("deprecation")
public class AndroidClient extends Activity {
	
	EditText TextOut;
	TextView TextIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_client);
        
        TextOut = (EditText)findViewById(R.id.textout);
        TextIn = (TextView)findViewById(R.id.textin);
        Button buttonSend = (Button)findViewById(R.id.send);
        buttonSend.setOnClickListener(buttonSendOnClickListener);
    }

    Button.OnClickListener buttonSendOnClickListener   = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Socket socket = null;
			DataOutputStream dataOutputStream = null;
			DataInputStream dataInputStream = null;
			
			try{
				
				socket = new Socket("192.168.10.119",9999);
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream.writeUTF(TextOut.getText().toString());
				TextIn.setText(dataInputStream.readUTF());
							
		}
			catch(UnknownHostException e){
			
				e.printStackTrace();
				
			}
			catch(Exception e){
				
				e.printStackTrace();
			}
			
			finally {
				
				if(socket != null){
					
					try{
						
						socket.close();
					}
					catch(IOException e){
						
						e.printStackTrace();
					}
				}
				
				if(dataOutputStream != null){
					
					try{
						dataOutputStream.close();
						
					}
					catch(IOException e){
						
						e.printStackTrace();
					}
					
				}
				
				if(dataInputStream != null){
					
					try{
						dataInputStream.close();
					}
						catch(IOException e){
						
						e.printStackTrace();
					}
				}
			}
			
		}
		
		
    	
    	
    };
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.android_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
