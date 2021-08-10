package sg.edu.rp.c346.id20042303.oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    EditText etID, etName, etDes, etSize;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rg = findViewById(R.id.editStars);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.etEditName);
        etDes = (EditText) findViewById(R.id.etEditDescription);
        etSize = (EditText) findViewById(R.id.etEditSize);

        Intent i = getIntent();
        final islands currentIsland = (islands) i.getSerializableExtra("island");

        etID.setText(currentIsland.getId()+"");
        etName.setText(currentIsland.getName());
        etDes.setText(currentIsland.getDescription());
        etSize.setText(currentIsland.getSize()+"");
        rg.setRating(currentIsland.getStar());


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(SecondActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete " + currentIsland.getName() + "?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("CANCEL", null);

                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(SecondActivity.this);
                        int result = dbh.deleteIsland(currentIsland.getId());
                        if (result>0){
                            Toast.makeText(SecondActivity.this, "Island deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(SecondActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(SecondActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to update the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("CANCEL", null);

                myBuilder.setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(SecondActivity.this);
                        currentIsland.setName(etName.getText().toString().trim());
                        currentIsland.setDescription(etDes.getText().toString().trim());
                        currentIsland.setSize(Integer.parseInt(etSize.getText().toString().trim()));

                        currentIsland.setStar((int)rg.getRating());
                        int result = dbh.updateIsland(currentIsland);
                        if (result>0){
                            Toast.makeText(SecondActivity.this, "Island updated", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(SecondActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(SecondActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure to discard the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("DO NOT DISCARD", null);

                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });


    }


}