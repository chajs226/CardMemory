package com.chajs226.cardmemory;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by amc on 2017-03-02.
 */

public class Tab3Setting extends Fragment {

    private ProgressDialog progressDialog;
    private MessageHandler messageHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate : xml 파일을 통해 객체를 생성해 화면에 보여주느 ㄴ것..
        View rootView = inflater.inflate(R.layout.tab3setting, container, false);

        Button button = (Button) rootView.findViewById(R.id.btnTimer);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onButtonClicked(v);
            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("count");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(true);

        messageHandler = new MessageHandler();

        return rootView;
    }

    public void onButtonClicked(View view)
    {
        progressDialog.show();
        Thread thread = new Thread(new Timer());
        thread.start();
    }

    public void doNotify()
    {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent intent = PendingIntent.getActivity(getActivity(), 100, new Intent(getActivity(), NotiActivity.class), 0);

        NotificationCompat.Builder nb = new NotificationCompat.Builder(getActivity());
        nb.setSmallIcon(R.drawable.touchicon);
        nb.setSound(sound);
        nb.setContentTitle("knock knock..");
        nb.setContentText("you've got a deliver");
        nb.setContentIntent(intent);

        NotificationManager nm = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

        nm.notify(100,nb.build());
    }


    public class Timer implements Runnable {

        @Override
        public void run() {
            for (int i = 5; i >= 0; i--) {
                try {
                    Thread.sleep((1000));
                } catch (Exception e) {
                }

                Bundle bundle = new Bundle();
                bundle.putInt("current count", i);

                Message message = new Message();
                message.setData(bundle);

                messageHandler.sendMessage(message);
            }
            progressDialog.dismiss();
        }
    }

    private class MessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int currentCount = msg.getData().getInt("current count");
            progressDialog.setMessage("Please wait in .. " + currentCount);

            if(currentCount == 0) {
                doNotify();
            }
        }
    }


}