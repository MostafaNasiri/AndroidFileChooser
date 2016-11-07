package ir.sohreco.androidfilechooser;

public class ExternalStorageNotAvailableException extends Exception {
    public ExternalStorageNotAvailableException() {
        super("There is no external storage available on this device.");
    }
}