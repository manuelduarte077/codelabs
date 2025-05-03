package dev.donmanuel.todojava;

import android.content.DialogInterface;

/**
 * Interface to handle dialog close events.
 * This interface is used to define a callback method that will be triggered when a dialog is closed.
 */
public interface OnDialogCloseListener {
    void onDialogClose(DialogInterface dialogInterface);
}