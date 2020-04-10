package com.s.task6april;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private Dialog objectDialog;
    EditText personName,personAddress,personPhone,personId;
    private Button downloadBtn;
    private FirebaseFirestore objectFirebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        objectFirebaseFirestore=FirebaseFirestore.getInstance();

        objectDialog = new Dialog(this);
        objectDialog.setContentView(R.layout.wait_dialog);
        objectDialog.setCancelable(false);

        personName = findViewById(R.id.personName);
        personAddress = findViewById(R.id.personAddress);
        personPhone = findViewById(R.id.personPhone);
        personId = findViewById(R.id.personId);

        try {
            if (getIntent() != null) {
                Person object = getIntent().getParcelableExtra("person");
                personName.setText(object.getHumanData().getHumanName());
                personAddress.setText(object.getHumanData().getHumanAddress());
                personPhone.setText(object.getHumanData().getHumanPhone());
                personName.requestFocus();
            } else {
                Toast.makeText(this, "No data is available", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "E:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void addValuesToFireBase(View view) {

        try {
            if (!personId.getText().toString().isEmpty() && !personName.getText().toString().isEmpty() && !personAddress.getText().toString().isEmpty() && !personPhone.getText().toString().isEmpty()) {
                objectDialog.show();
                Map<String, Object> objmap = new HashMap<>();

                objmap.put("person_name", personName.getText().toString());
                objmap.put("person_address", personAddress.getText().toString());
                objmap.put("person_number", personPhone.getText().toString());
                objectFirebaseFirestore.collection("PersonsData")
                        .document(personId.getText().toString()).set(objmap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                objectDialog.dismiss();
                                Toast.makeText(SecondActivity.this, "Data Added", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                objectDialog.dismiss();
                                Toast.makeText(SecondActivity.this, "Data not Added" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

            } else {
                objectDialog.dismiss();
                Toast.makeText(SecondActivity.this, "Enter valid data", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {

            Toast.makeText(this, "addValuesToFireBase" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }




}
