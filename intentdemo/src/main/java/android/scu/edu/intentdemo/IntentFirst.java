package android.scu.edu.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class IntentFirst extends ActionBarActivity {
    TextView label1;
    TextView labelSelectedContact;
    EditText text1;
    Button btnCallActivity2;
    Button btnIntentResult;
    Button btnIntentPicture;
    Button btnIntentVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intent_first);
        try {
            setContentView(R.layout.activity_intent_first);
            label1 = (TextView)findViewById(R.id.textView);
            text1 = (EditText)findViewById(R.id.editText);
            btnCallActivity2 = (Button)findViewById(R.id.button);
            btnCallActivity2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String myData = text1.getText().toString();
                    //Intent myActivity2 = new Intent(Intent.ACTION_CALL, Uri.parse(myData));
                    Intent myActivity2 = new Intent(Intent.ACTION_PICK, Uri.parse(myData));
                    startActivity(myActivity2);
                }
            });

            btnIntentResult = (Button) findViewById(R.id.button2);
            btnIntentResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String myData = text1.getText().toString();
                    String myData = "content://contacts/people/";
                    Intent myActivity2 = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));
                    //Intent myActivity2 = new Intent(Intent.ACTION_PICK, Uri.parse(myData));
                    startActivityForResult(myActivity2, 222);
                }
            });

            btnIntentPicture = (Button) findViewById(R.id.button3);
            btnIntentPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent();
                    myIntent.setType("video/*, images/*");
                    myIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(myIntent, 0);
                }
            });

            btnIntentVideo = (Button) findViewById(R.id.button4);
            btnIntentVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent();
                    myIntent.setType("audio/mp3");
                    myIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(myIntent, 1);
                }
            });

        }  catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intent_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        try {
            switch (requestCode) {
                case 222:
                    if (resultCode == Activity.RESULT_OK) {
                        String selectedContact = intent.getDataString();
                        //System.out.println("AAA" + selectedContact);
                        labelSelectedContact.setText(selectedContact.toString());
                        Intent myActs = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedContact));
                        startActivity(myActs);
                    } else {
                        labelSelectedContact.setText("Selection CANCELLED : " + requestCode + ", " + resultCode);
                    }
                    break;
                case 0:
                    String selectedImage = intent.getDataString();
                    Toast.makeText(this, selectedImage, 1).show();
                    // show a 'nice' screen with the selected image
                    Intent myAct3 = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedImage));
                    startActivity(myAct3);
                    break;
                case 1:
                    String selectedVideo= intent.getDataString();
                    Toast.makeText(this, selectedVideo, 1).show();
                    // show a 'nice' screen with the selected image
                    Intent myAct4 = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedVideo));
                    startActivity(myAct4);
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




}
