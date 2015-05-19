package android.scu.edu.uidemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class AutoCompleteFirst extends ActionBarActivity {
    private static final String [] countries = {"UK", "USA", "France", "Switzerland", "China"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_first);
        AutoCompleteTextView autoView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries));
        /**
         * After testing,
         * The UP action bar can actually show even if you comment this line below
         * getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         * as long as in the AndroidManifest.xml, you specify that:
         * android:parentActivityName = ".ListViewIFirst"
         * meta-data
         * android:name = "android.support.PARENT_ACTIVITY"
         * android:value=".ListViewIFirst"
         */
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button buttonContexualMenu = (Button) findViewById(R.id.button2);
        buttonContexualMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutoCompleteFirst.this, ContextualMenu.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auto_complete_first, menu);
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
}
