package sg.edu.rp.c346.id20042303.oursingapore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btn5Stars;
    ListView lv;
    ArrayList<islands> islandList;

    //ArrayAdapter adapter;
    int requestCode = 9;
    CustomAdapter caIsland;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.buttonAdd);
        btn5Stars = findViewById(R.id.btnShow5Stars);
        lv = findViewById(R.id.lv);
        btn5Stars = findViewById(R.id.btnShow5Stars);


        DBHelper dbh = new DBHelper(this);
        islandList = dbh.getAllIslands();
        dbh.close();

        caIsland = new CustomAdapter(MainActivity.this, R.layout.row, islandList);
        lv.setAdapter(caIsland);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("island", islandList.get(position));
                startActivityForResult(i, requestCode);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the input.xml layout file
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.insert_island, null);
                // Obtain the UI component in the input.xml layout
                // It needs to be defined as "final", so that it can used in the onClick() method later

                final EditText etName = viewDialog.findViewById(R.id.etName);
                final EditText etDescription = viewDialog.findViewById(R.id.etDescription);
                final EditText etSize = viewDialog.findViewById(R.id.etSize);
                final EditText etDes = viewDialog.findViewById(R.id.etDescription);
                final RatingBar rgStar = viewDialog.findViewById(R.id.editStars);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Insert islands");
                myBuilder.setPositiveButton("INSERT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String name = etName.getText().toString().trim();
                        String Description = etDes.getText().toString().trim();
                        int size = Integer.parseInt(etSize.getText().toString());
                        int stars = (int) rgStar.getRating();

                        DBHelper dbh = new DBHelper(MainActivity.this);
                        long result = dbh.insertIsland(name, Description, size, stars);

                        if (result != -1) {
                            Toast.makeText(MainActivity.this, "Island inserted", Toast.LENGTH_LONG).show();

                            lv.setAdapter(null);
                            dbh = new DBHelper(MainActivity.this);
                            islandList = dbh.getAllIslands();
                            dbh.close();

                            caIsland = new CustomAdapter(MainActivity.this, R.layout.row, islandList);
                            lv.setAdapter(caIsland);
                        } else {
                            Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                islandList.clear();
                islandList.addAll(dbh.getIslandByStars(5));
                //adapter.notifyDataSetChanged();
                caIsland.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == this.requestCode && resultCode == RESULT_OK){
            DBHelper dbh = new DBHelper(this);
            islandList.clear();
            islandList.addAll(dbh.getAllIslands());
            dbh.close();
            //adapter.notifyDataSetChanged();
            caIsland.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}