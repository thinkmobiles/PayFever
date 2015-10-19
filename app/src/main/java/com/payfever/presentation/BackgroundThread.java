package com.payfever.presentation;

import android.os.HandlerThread;
import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public class BackgroundThread extends HandlerThread {
    BackgroundThread() {
        super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
    }
}
