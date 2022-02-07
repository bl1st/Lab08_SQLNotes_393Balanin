package com.example.lab08_sqlnotes_393balanin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText txtctl;
    int nid;
    String ntxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtctl = findViewById(R.id.txt_content);
        Intent i = getIntent();
        nid = i.getIntExtra("note-id", 0);
        ntxt = i.getStringExtra("note-txt");

        txtctl.setText(ntxt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        switch(id)
        {
            case R.id.itm_save: {
                //get text from text box, modify note, show toast "note saved" adn exit activity
                String ntxt = txtctl.getText().toString();
                g.notes.AlterNote(nid, ntxt);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Note Saved",
                        Toast.LENGTH_SHORT);
                toast.show();
                finish();
                break;
            }
            case R.id.itm_delete: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Note Deliting");
                dialog.setCancelable(true);
                dialog.setMessage("You sure you want to delete this note?");
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        return;
                    }
                });
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        g.notes.DeleteNote(nid);

                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Note Deleted",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }
                });
                dialog.show();
            }
        }
    return super.onOptionsItemSelected(item);
    }
}