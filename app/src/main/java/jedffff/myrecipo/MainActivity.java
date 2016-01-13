package jedffff.myrecipo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    static String[] foods = {"Clam Chowder","Onion Ring","Steak","Lamb Rack","Tiramisu","Cheese Cake",};
    static Integer[] imgs = {R.drawable.clamchowder,R.drawable.onionring,R.drawable.steak,R.drawable.lambchops,R.drawable.tiramisu,R.drawable.cheesecake};
    static String[] time = {"30","10","10","20","50","300"};
    static String[] ings = {"1 cup of clam juice","1 large onion", "8 oz of rib eye", "16 oz of lamb rack", "8 oz of lady finger", "8 oz of cream cheese"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListAdapter myAdapter = new CustomAdapter(this, foods,imgs,time);
        ListView MyListView = (ListView) findViewById(R.id.MyView);
        MyListView.setAdapter(myAdapter);

        MyListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Integer im = position;
                        Integer ings = position;
                        Integer t = position;
                        Intent intent = new Intent(MainActivity.this,RecipoPage.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Dish",food);
                        bundle.putInt("ImPos",im);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
        );
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

}
