package jedffff.myrecipo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipoPage extends Activity {
    DBAdapter myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipo_page);

        openDB();

        TextView IDView = (TextView) findViewById(R.id.textView);
        IDView.setText(getIntent().getExtras().getString("Dish"));

        ImageView IMView = (ImageView) findViewById(R.id.imageView);
        IMView.setImageResource(MainActivity.imgs[getIntent().getExtras().getInt("ImPos")]);

        TextView timeView = (TextView) findViewById(R.id.timetext);
        timeView.setText("Estimated cook time will be: " + MainActivity.time[getIntent().getExtras().getInt("ImPos")] + "Minutes");

        TextView DBIngView = (TextView) findViewById(R.id.DBIngText);
        DBIngView.setText(MainActivity.ings[getIntent().getExtras().getInt("ImPos")]);
    }

    private void displayText(String message){
        TextView textView = (TextView) findViewById(R.id.CusIngText);
        textView.setText(message);
    }

    public void onClick_AddRecord(View v) {
        final EditText editTexts = (EditText) findViewById(R.id.editText);
        String text = String.valueOf(editTexts.getText().toString());
        long newId = myDb.insertRow("null", 0, "null", text);

        // Query for the record we just added.
        // Use the ID:
        Cursor cursor = myDb.getAllRows();
        displayRecordSet(cursor);
    }

    private void displayRecordSet(Cursor cursor) {
        String message = "";
        // populate the message from the cursor

        // Reset cursor to start, checking to see if there's data:
        if (cursor.moveToFirst()) {
            do {
                // Process the data:
                int id = cursor.getInt(DBAdapter.COL_ROWID);
                String name = cursor.getString(DBAdapter.COL_NAME);
                int time = cursor.getInt(DBAdapter.COL_TIME);
                String description = cursor.getString(DBAdapter.COL_DES);
                String ing = cursor.getString(DBAdapter.COL_ING);

                // Append data to the message:
                message +=  ing
                        +"\n";
            } while(cursor.moveToNext());
        }

        displayText(message);
    }

    public void onClick_ClearAll(View v) {
        myDb.deleteAll();
        Cursor cursor = myDb.getAllRows();
        displayRecordSet(cursor);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void openDB(){
        myDb = new DBAdapter(this);
        myDb.open();
    }

    private void closeDB(){
        myDb.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }
}
