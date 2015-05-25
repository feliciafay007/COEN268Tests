package accelerometer.example.com.dogplaydate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;


public class MenuExampleActivity extends Activity {

    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profilejava);
        button = (Button)findViewById(R.id.button4);
        button.setOnClickListener(myhandler1);
    }

    View.OnClickListener myhandler1 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent staticActivityIntent = new Intent(MenuExampleActivity.this, person_profilejava.class);
            startActivity(staticActivityIntent);
        }
    };



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu_example, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_edit:
// TODO: Implement Edit
                return true;
            case R.id.action_delete:
// TODO: Implement Delete
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}