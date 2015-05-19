package android.scu.edu.activitylifecyclea;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static java.lang.Thread.*;


public class ActivityLifeCycleSecond extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_life_cycle_second);
        Toast.makeText(this, "onCreate B", Toast.LENGTH_SHORT).show();
        System.out.println("onCreate B");
//        try {
//            sleep(1000) ;
//        } catch (InterruptedException ie){
//
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart  B", Toast.LENGTH_SHORT).show();
        System.out.println("onStart B");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume  B", Toast.LENGTH_SHORT).show();
        System.out.println("onResume B");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart B", Toast.LENGTH_SHORT).show();
        System.out.println("onRestart B");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause B", Toast.LENGTH_SHORT).show();
        System.out.println("onPause B");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop B", Toast.LENGTH_SHORT).show();
        System.out.println("onStop B");
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy B", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        System.out.println("onDestroy B");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_life_cycle_second, menu);
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
