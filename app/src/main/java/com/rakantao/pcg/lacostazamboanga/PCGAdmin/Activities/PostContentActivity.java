package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.AutoResizeEditText;
import com.rakantao.pcg.lacostazamboanga.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PostContentActivity extends AppCompatActivity implements View.OnClickListener {

    private int GALLERY = 1, CAMERA = 2;
    ImageView btnBack;
    Button btnChoose;
    ImageButton camImage;

    private Button btnPostArcticle;
    private EditText etArticleTitle;
    private AutoResizeEditText rET;

    private Uri imageUri = null;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private DatabaseReference mPostDatabase;
    private FirebaseAuth mAuth;

    private String uid;
    private long countpost = 0;
    private String audience;

    private String pushKey;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);



        camImage = findViewById(R.id.btnCamera);
        btnBack = findViewById(R.id.btnBackPost);
        btnChoose = findViewById(R.id.btnChoose);

        btnPostArcticle = findViewById(R.id.btnPostArticle);
        etArticleTitle = findViewById(R.id.ETcontentTitle);
        rET = findViewById(R.id.rET);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        uid = current_user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("NewsFeedPosts").push();

        camImage.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnChoose.setOnClickListener(this);
        btnPostArcticle.setOnClickListener(this);

        // post counter
        mDatabase.child("NewsFeedPosts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    countpost = dataSnapshot.getChildrenCount();
                } else {
                    countpost = 0;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBackPost:
                startActivity(new Intent(PostContentActivity.this, PCGAdminHome.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case  R.id.btnChoose:
                final CharSequence[] items2 = {
                        "Everyone", "Personnel","Public User"
                };

                AlertDialog.Builder builder2 = new AlertDialog.Builder(PostContentActivity.this);
                builder2.setTitle("Make your selection");
                builder2.setItems(items2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        btnChoose.setText(items2[item]);
                        if (items2[item].equals("Everyone")){
                            btnChoose.setText("Everyone");
                            audience = "Everyone";
                            target(audience);
                            btnChoose.setCompoundDrawablesWithIntrinsicBounds(R.drawable.world, 0, R.drawable.down1, 0);
                        }else if (items2[item].equals("Personnel")){
                            btnChoose.setText("Personnel");
                            audience = "Personnel";
                            target(audience);
                            btnChoose.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lieutenant, 0, R.drawable.down1, 0);
                        }else if (items2[item].equals("Public User")){
                            btnChoose.setText("Public User");
                            audience = "Public User";
                            target(audience);
                            btnChoose.setCompoundDrawablesWithIntrinsicBounds(R.drawable.userss, 0, R.drawable.down1, 0);
                        }
                    }
                });
                AlertDialog alert2 = builder2.create();
                alert2.show();
                break;
            case R.id.btnCamera:
                showPictureDialog();
                break;

            case R.id.btnPostArticle:
                postArticle();
                break;
        }
    }

    private void target(String audience) {
    }

    private void postArticle() {

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd h:mm a");
        final String date = df.format(Calendar.getInstance().getTime());

        final String articleTitle = etArticleTitle.getText().toString().trim();
        final String articleContent = rET.getText().toString().trim();
        final String articleAudience = btnChoose.getText().toString().trim();

        if (TextUtils.isEmpty(articleTitle)){
            Toast.makeText(this, "Please Provide a Title", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(articleContent)){
            Toast.makeText(this, "Please Provide a Content", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(articleAudience)){
            Toast.makeText(this, "Please Select Your Audience", Toast.LENGTH_SHORT).show();
        } else {


            pushKey = mPostDatabase.getKey();

            HashMap postMap = new HashMap();

            postMap.put("article_title", articleTitle);
            postMap.put("article_content", articleContent);
            postMap.put("article_userID", uid);
            postMap.put("date_posted", date);
            postMap.put("counter", countpost);
            postMap.put("audience", articleAudience);
            postMap.put("pushKey", pushKey);

            mPostDatabase.setValue(postMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(PostContentActivity.this, "Posted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    public void TakePicture(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    public void SelectFromGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                SelectFromGallery();
                                break;
                            case 1:
                                TakePicture();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY && resultCode == RESULT_OK || requestCode == CAMERA && resultCode == RESULT_OK){

            imageUri = data.getData();

        }
    }
}