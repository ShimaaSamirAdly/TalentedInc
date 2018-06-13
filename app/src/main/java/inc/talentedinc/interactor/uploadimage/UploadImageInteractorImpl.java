package inc.talentedinc.interactor.uploadimage;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import inc.talentedinc.listener.UploadImageListener;

/**
 * Created by MMM on 6/1/2018.
 */

public class UploadImageInteractorImpl implements UploadImageInteractor {

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private String imgUrl;

    @Override
    public void uploadImage(Uri filePath, final UploadImageListener listener) {

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        StorageReference ref = storageReference.child("image/" + UUID.randomUUID().toString());
        ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            progressDialog.dismiss();
                imgUrl = String.valueOf(taskSnapshot.getDownloadUrl());
                Log.i("url", String.valueOf(imgUrl));
//                            Toast.makeText(TestImageActivity.this, ""+imgUrl, Toast.LENGTH_SHORT).show();
                listener.onSuccessUploadImage(imgUrl);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                            progressDialog.dismiss();
//                            Toast.makeText(TestImageActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                        .getTotalByteCount());
//                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
            }
        });
    }
}

