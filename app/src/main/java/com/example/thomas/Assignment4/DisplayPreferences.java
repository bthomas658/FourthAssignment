package com.example.thomas.Assignment4;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thomas.Homework4.R;

import java.util.ArrayList;


public class DisplayPreferences extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_preferences);

        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);


        //add the data from the preferences file to the ArrayList
        ArrayList<String> list = new ArrayList<>();
        if (preferences.contains("input"))
            list.add(preferences.getString("input", ""));
        if (preferences.contains("input2"))
            list.add(preferences.getString("input2",""));
        if (preferences.contains("input3"))
            list.add(preferences.getString("input3",""));
        if (preferences.contains("input4"))
            list.add(preferences.getString("input4",""));

        //create the array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, list);

        //set the list to the adapter
        setListAdapter(adapter);
    }

    @Override
    protected  void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        String selectedItem = (String) l.getItemAtPosition(position);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), selectedItem, duration);
        toast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_screens, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuactivity1) {
            Intent intent = new Intent(getApplicationContext(), UserPreferences.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menuactivity3) {
            SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            ArrayList<String> list = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, list);
            setListAdapter(adapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
