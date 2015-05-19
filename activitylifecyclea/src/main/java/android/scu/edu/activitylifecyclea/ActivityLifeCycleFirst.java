package android.scu.edu.activitylifecyclea;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityLifeCycleFirst extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_life_cycle_first);
        Toast.makeText(this, "onCreate A", Toast.LENGTH_SHORT).show();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSecondActivityIntent = new Intent(ActivityLifeCycleFirst.this, ActivityLifeCycleSecond.class);
                startActivity(startSecondActivityIntent);
            }
        });
        System.out.println("onCreate A");
        //TextView textView = (TextView) findViewById(R.id.textView);
        RadioButton rb = (RadioButton) findViewById(R.id.radioButton);
        rb.toggle();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart A", Toast.LENGTH_SHORT).show();
        System.out.println("onStart A");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume A", Toast.LENGTH_SHORT).show();
        System.out.println("onResume A");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart A", Toast.LENGTH_SHORT).show();
        System.out.println("onRestart A");
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause A", Toast.LENGTH_SHORT).show();
        super.onPause();
        System.out.println("onPauseA");
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop A", Toast.LENGTH_SHORT).show();
        super.onStop();
        System.out.println("onStop A");
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy A", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        System.out.println("onDestroy A");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_life_cycle_first, menu);
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
