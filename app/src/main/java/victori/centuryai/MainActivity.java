package victori.centuryai;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String log = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //My stuff

        final TextView tvMain = (TextView) findViewById(R.id.tvMain);
        tvMain.setMovementMethod(new ScrollingMovementMethod());
        final EditText etInput = (EditText) findViewById(R.id.etInput);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Hub hub = new Hub();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newLine = etInput.getText().toString();
                etInput.getText().clear();
                tvMain.append("\n" + newLine);
                String response = hub.interpretLine(newLine);
                tvMain.append("\n> " + response);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
