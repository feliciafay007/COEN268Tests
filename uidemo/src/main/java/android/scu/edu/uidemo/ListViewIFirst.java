package android.scu.edu.uidemo;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListViewIFirst extends ActionBarActivity {
    private Toast toast;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_ifirst);
        mContext = this;
        ListView listView = (ListView) findViewById(R.id.listView);
        String[] strs = {"Lucy", "Lily", "rose", "Lisa", "Queen"};
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  toast.makeText(mContext , "position = "  + position, Toast.LENGTH_SHORT).show();
               // Log.d("AAA", "Position:" + position);
            }
        });
        Button autoButton = (Button) findViewById(R.id.button);
        autoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartAutoTextActivity = new Intent(ListViewIFirst.this, AutoCompleteFirst.class);
                startActivity(intentStartAutoTextActivity);
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.i("FF", "onStart");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view_ifirst, menu);
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
        if (id == R.id.action_open_contexual_menu_activity) {
            Intent intent = new Intent(ListViewIFirst.this, ContextualMenu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("FF", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("FF", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("FF", "onStop");
    }

    @Override
    protected  void onRestart() {
        super.onStop();
        Log.i("FF", "onRestart");
    }
}
