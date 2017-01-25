package ir.sohreco.androidfilechooser;

class ExternalStorageNotAvailableException extends RuntimeException {
    ExternalStorageNotAvailableException() {
        super("There is no external storage available on this device.");
    }
}