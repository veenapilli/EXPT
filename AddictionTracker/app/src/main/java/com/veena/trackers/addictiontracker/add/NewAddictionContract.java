package com.veena.trackers.addictiontracker.add;

import android.support.annotation.NonNull;

import java.io.IOException;

/**
 * Created by veena on 16/2/17.
 */

public class NewAddictionContract {
    interface View {

        void showEmptyNoteError();

        void showNotesList();

        void openCamera(String saveTo);

        void showImagePreview(@NonNull String uri);

        void showImageError();
    }

    interface UserActionsListener {

        void saveNote(String title, String description);

        void takePicture() throws IOException;

        void imageAvailable();

        void imageCaptureFailed();
    }
}
