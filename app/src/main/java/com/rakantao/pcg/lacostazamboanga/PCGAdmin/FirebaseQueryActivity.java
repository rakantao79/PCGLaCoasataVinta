package com.rakantao.pcg.lacostazamboanga.PCGAdmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakantao.pcg.lacostazamboanga.R;

import java.util.HashMap;

public class FirebaseQueryActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private TextView tvRetriveData;
    private EditText etInput;
    private Button btnSaveToDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_query);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        tvRetriveData = findViewById(R.id.tvRetrieveFromDatabase);
        etInput = findViewById(R.id.etInput);
         btnSaveToDatabase = findViewById(R.id.btnSaveToDatabase);

         //saving data to database
         btnSaveToDatabase.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 String inputData = etInput.getText().toString().trim();

                 //check edittext if null or not..
                 if (inputData.isEmpty()){
                     Toast.makeText(FirebaseQueryActivity.this, "Please Enter Data", Toast.LENGTH_SHORT).show();
                 } else {

                     HashMap saveMap = new HashMap();
                     saveMap.put("data", inputData);

                     mDatabase.child("SampleDatabase").push().setValue(saveMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void aVoid) {
                             Toast.makeText(FirebaseQueryActivity.this, "Successfully Save to database", Toast.LENGTH_SHORT).show();
                         }
                     });
                 }
             }
         });

         //retrieve data and display to textview
         mDatabase.child("SampleDatabase").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 if (dataSnapshot.exists()){
                     String retrieveData = dataSnapshot.child("data").getValue().toString();
                     tvRetriveData.setText(retrieveData);
                 }
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
    }
}
