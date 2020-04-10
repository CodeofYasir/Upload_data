package com.s.task6april;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void moveData(View view)
    {
        try
        {
            Person objectPersons=new Person();
            Subjects humanData=new Subjects();

            humanData.setHumanName("");
            humanData.setHumanAddress("");
            humanData.setHumanPhone("");
            objectPersons.setHumanData(humanData);

            startActivity(new Intent(this, SecondActivity.class)
                    .putExtra("person",objectPersons)
            );
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}


