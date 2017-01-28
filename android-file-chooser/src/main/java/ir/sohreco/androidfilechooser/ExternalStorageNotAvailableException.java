package ir.sohreco.androidfilechooser;

class ExternalStorageNotAvailableException extends Exception {
    ExternalStorageNotAvailableException() {
        super("There is no external storage available on this device.");
    }
}