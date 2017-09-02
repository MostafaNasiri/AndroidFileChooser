<h1>AndroidFileChooser</h1>

<p align="center">
<img src="https://github.com/MostafaNasiri/AndroidFileChooser/blob/master/AndroidFileChooser.png" />
</p>

<p>Android File Chooser is a simple and customizable file/directory chooser dialog which you can use in your apps to let your users select a file or directory based on your needs.</p>

<h2>How to Add the Library</h2>
<p>This library is availabe in the jcenter repository. Simply add this line of code in your dependencies:</p>

```
compile 'ir.sohreco.androidfilechooser:android-file-chooser:1.2'
```

<h2>How to Use</h2>
<p>If you want the default look for your file/directory chooser you can simply implement FileChooserDialog.ChooserListener in your class and create an instance of FileChooserDialog.Builder and then show the dialog:</p>

```java
FileChooserDialog.Builder builder = new FileChooserDialog.Builder(FileChooserDialog.ChooserType.FILE_CHOOSER, this);
```

<p>Notice that the first parameter is the chooser type which you should select from the ChooserType enum and the second parameter is the class that implements FileChooserDialog.ChooserListener </p>
<p>Then you can show your chooser dialog as simple as writing this piece of code:</p>

```java
try {
  builder.build().show(getSupportFragmentManager(), null);
} catch (ExternalStorageNotAvailableException e) {
  e.printStackTrace();
}
```

<p>You should catch ExternalStorageNotAvailableException when you want to make an instance of the fragment by calling build().</p>
<h2>How to Use Multiple File Selection Feature</h2>
<p>When multiple file selection is enabled, the <i>path</i> parameter in onSelect method of the ChooserListener is a string containing selected files paths seperated by <b>FileChooserDialog.FILE_NAMES_SEPARATOR</b>.</p>

```java
FileChooserDialog.Builder builder = new FileChooserDialog.Builder(FileChooserDialog.ChooserType.FILE_CHOOSER, new                FileChooserDialog.ChooserListener() {
            @Override
            public void onSelect(String path) {
                String[] selectedFilesPaths = path.split(FileChooserDialog.FILE_NAMES_SEPARATOR);
                // Do whatever you want to do with selected files
            }
        })
                .setMultipleFileSelectionEnabled(true)
                .setSelectMultipleFilesButtonText("Select Files");
```

<h2>On Android Version 6 And Above</h2>
<p>You should grant READ_EXTERNAL_STORAGE permission:</p>

```java
int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
} else {
    // Your app already has the permission to access files and folders
    // so you can simply open FileChooser here.
}
```

```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == PERMISSION_REQUEST_CODE) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted.
        }
    }
}
```
