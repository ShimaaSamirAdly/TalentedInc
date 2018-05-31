package inc.talentedinc.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import inc.talentedinc.R;

public class TestImageActivity extends AppCompatActivity {

    private Uri filePath;
    private final int PICK_REQUEST = 77;
    private ImageView img;
    private ImageView downloadedImg;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Bitmap theBitmap;
    private Uri imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);

        img = findViewById(R.id.img);
        downloadedImg = findViewById(R.id.downloadedImg);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        downloadedImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.i("imgUrl", ""+imgUrl);
                    Glide.with(TestImageActivity.this)
                            .load("" + imgUrl)
                            .into(downloadedImg);

            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_REQUEST);
            }
        });

        Button upload = findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(filePath != null){

                    final ProgressDialog progressDialog = new ProgressDialog(TestImageActivity.this);
                    progressDialog.setTitle("Uploading");
                    progressDialog.show();

                    img.setDrawingCacheEnabled(true);
                    img.buildDrawingCache();

                    StorageReference ref = storageReference.child("image/" + UUID.randomUUID().toString());
                    ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            imgUrl = taskSnapshot.getDownloadUrl();
                            Log.i("url", String.valueOf(imgUrl));
                            Toast.makeText(TestImageActivity.this, ""+imgUrl, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(TestImageActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
