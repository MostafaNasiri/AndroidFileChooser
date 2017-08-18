package ir.sohreco.androidfilechooser.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooser;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileChooser.Builder builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER,
                new FileChooser.ChooserListener() {
                    @Override
                    public void onSelect(String path) {
                        String[] selectedFilesPaths = path.split(FileChooser.FILE_NAMES_SEPARATOR);
                        // Do whatever you want to do with selected files
                    }
                })
                .setMultipleFileSelectionEnabled(true)
                .setSelectMultipleFilesButtonText("Select Files");

        try {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.file_chooser_fragment_container_framelayout, builder.build())
                    .commit();
        } catch (ExternalStorageNotAvailableException e) {
            Toast.makeText(this, "There is no external storage available on this device.",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
