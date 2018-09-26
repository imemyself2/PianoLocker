package com.example.darshil.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    int flag = 0;

    private Context context;

    public FingerprintHandler(Context context){

        this.context = context;

    }

    public int startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject, Context context){

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
        return flag;

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("There was an Auth Error. " + errString, false);

    }

    @Override
    public void onAuthenticationFailed() {

        this.update("Auth Failed. ", false);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Error: " + helpString, false);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        FileInputStream temp = null;
        try {
            temp = new FileInputStream("storePianoPass.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(temp == null)
        {
            this.context.startActivity(new Intent("android.intent.action.PianoSetPass"));

        }
        else {
            this.update("You can now access the app.", true);
            Log.d("Test", "Setting flag to 1");
            Intent intent = new Intent(this.context, TransitionAnimation.class);
            final MediaPlayer unlockSound = MediaPlayer.create(context, R.raw.iphone_unlock);
            unlockSound.start();
//        Timer timer= new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//            }
//        }, 2500);


            this.context.startActivity(intent);
        }


    }

    private void update(String s, boolean b) {

        TextView paraLabel = (TextView) ((Activity)context).findViewById(R.id.paraLabel);
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.fingerprintImage);

        paraLabel.setText(s);

        if(b == false){

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else {

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.action_done);


        }

    }
}
