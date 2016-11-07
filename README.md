<h1>Android File Chooser</h1>
<p>Android File Chooser is a simple and customizable file/directory chooser dialog which you can use in your apps to let your users select a file or directory based on your needs.</p>
<h2>How to Add the Library</h2>
<p>This library is availabe in the jcenter repository. Simply add this line of code in your dependencies:</p>
<b>Please update to version 1.1.0 if you are already using version 1.0.0. The older version has a serious bug and crashes your app in android 6.0  and above</b>
```
compile 'ir.sohreco.androidfilechooser:android-file-chooser:1.1.0'
```
<h2>About Android Version 6 And Above</h2>
<p>As you probably know, Android 6 introduced a new permission system which grants user permission at runtime about certain actions including reading files from local storage so you should check whether your app has permission to access user files.</p>
<p>You can check and request permission like this:</p>
```
int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
} else {
    // Your app already has the permission to access files and folders
    // so you can simply open FileChooser here.
}
```
<p>And you should override onRequestPermissionsResult in your activity:</p>
```
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
<h2>New Features In Version 1.1.0</h2>
You can now set:
  <ul>
    <li>Initial Directory</li>
    <li>Select Directory Button's Text</li>
    <li>Select Directory Button's Text Color</li>
    <li>Select Directory Button's Text Size</li>
    <li>Select Directory Button's Background</li>
  </ul>
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
<h3>How to Customize</h3>
<p>You can easily customize your chooser with the methods that are provided in FileChooserDialog.Builder class. Note that all of these settings are optional and you can set any of these properties you want.</p>
```java
FileChooserDialog.Builder builder = 
                new FileChooserDialog.Builder(FileChooserDialog.ChooserType.DIRECTORY_CHOOSER, this)
                .setTitle("Select a directory:")
                .setFileFormats(new String[]{".png", ".jpg"})
                .setInitialDirectory(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES))
                .setSelectDirectoryButtonText("ENTEKHAB")
                .setSelectDirectoryButtonBackground(R.drawable.dr)
                .setSelectDirectoryButtonTextColor(R.color.cl)
                .setSelectDirectoryButtonTextSize(25)
                .setFileIcon(R.drawable.ic_file)
                .setDirectoryIcon(R.drawable.ic_directory)
                .setPreviousDirectoryButtonIcon(R.drawable.ic_prev_dir);
```
<h2>Screenshots</h2>
<p>The screenshot below is how the default file chooser looks:</p>
<center><img src="http://sm.uploads.im/t/IhMw3.png" /></center>
<p>And this is how the default directory chooser looks:</p>
<img src="http://sj.uploads.im/t/WENpi.png" />
