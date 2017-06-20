package com.example.project1a;


import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

class StopWatch extends CountDownTimer{

    private TextView timeText;
    private long elapsedTime;
    private long totalTime;

    public StopWatch(long millsInFuture, long countDownInterval, TextView tv){
        super(millsInFuture,countDownInterval);
        timeText = tv;
        totalTime = millsInFuture;
    }
    @Override
    public void onTick(long millsUntilFinished) {
//        how to set locale in format
        long mills = millsUntilFinished;
        String ms = String.format(Locale.getDefault(),"%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(mills)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mills)),
                TimeUnit.MILLISECONDS.toSeconds(mills)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mills)));
        timeText.setText(ms);
        elapsedTime = totalTime-millsUntilFinished;

    }

    @Override
    public void onFinish() {
        timeText.setText(R.string.game_over);

    }
    public int getTimeSpent(){
        return (int)elapsedTime/1000;
    }
}
