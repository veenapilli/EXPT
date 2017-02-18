package com.veena.trackers.addictiontracker.detail;

import android.support.annotation.Nullable;

/**
 * Created by veena on 16/2/17.
 */

public class DetailContract {
    interface View {

        void setProgressIndicator(boolean active);

        void showMissingNote();

        void hideTitle();

        void showTitle(String title);

        void showImage(String imageUrl);

        void hideImage();

        void hideDescription();

        void showDescription(String description);
    }

    interface UserActionsListener {

        void openNote(@Nullable String noteId);
    }
}
