package ir.sohreco.androidfilechooser.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ir.sohreco.androidfilechooser.FileChooserDialog;


public class MainActivity extends AppCompatActivity {

    private FileChooserDialog dialog;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new FileChooserDialog.Builder(FileChooserDialog.ChooserType.DIRECTORY_CHOOSER, new FileChooserDialog.ChooserListener() {
            @Override
            public void onSelect(String path) {
                Log.d(TAG, "onSelect: " + path);
            }
        }).build();
        dialog.show(getSupportFragmentManager(), null);
    }
}
