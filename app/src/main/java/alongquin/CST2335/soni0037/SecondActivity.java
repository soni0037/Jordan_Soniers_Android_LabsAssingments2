package alongquin.CST2335.soni0037;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import alongquin.CST2335.soni0037.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        variableBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        variableBinding.title.setText("Welcome Back, " + emailAddress + "!");
        variableBinding.callButton.setOnClickListener(click -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + variableBinding.textPhone.getText().toString()));
            startActivity(call);
        });
        File myImage = new File (getFilesDir(), "Picture.png");
        if (myImage.exists()) {
            Bitmap dp = BitmapFactory.decodeFile(getFilesDir() + "/" + "Picture.png");
            variableBinding.imageProfile.setImageBitmap (dp);
        }
        variableBinding.cngPngBtn.setOnClickListener(click -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraResult.launch(cameraIntent);
        });
    }
    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            Bitmap thumbnail = data.getParcelableExtra("data");
            variableBinding.imageProfile.setImageBitmap( thumbnail );
            FileOutputStream fOut = null;
            try {
                fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                fOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}