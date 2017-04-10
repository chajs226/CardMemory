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
import android.widget.EditText;
import android.widget.TextView;

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

    private int timeCount;
    EditText editTextTimeCount;
    TextView textViewStatus;
    private int stopbit = 0;
    int timeCountM = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate : xml 파일을 통해 객체를 생성해 화면에 보여주느 ㄴ것..
        View rootView = inflater.inflate(R.layout.tab3setting, container, false);

        //Timer 실행 버튼
        Button buttonTimer = (Button) rootView.findViewById(R.id.btnTimer);
        buttonTimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onTimerButtonClicked(v);
            }
        });

        //Cancel 버튼
        Button buttonCancel = (Button) rootView.findViewById(R.id.btnCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onCancelButtonClicked(v);
            }
        });

        editTextTimeCount = (EditText) rootView.findViewById(R.id.editTextTimeCount);
        textViewStatus = (TextView) rootView.findViewById(R.id.textViewStatus);


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("count");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(true);

        messageHandler = new MessageHandler();

        if (timeCountM == 0)
        {
            textViewStatus.setText("알림 설정이 되어있지 않습니다.");
        }
        else
        {
            textViewStatus.setText("알림이 " + String.valueOf(timeCountM) + "분 설정되었습니다.");
        }

        return rootView;
    }

    public void onTimerButtonClicked(View view)
    {
        timeCountM = Integer.parseInt(editTextTimeCount.getText().toString());
        timeCount = timeCountM * 10;

        //progressDialog.show();
        textViewStatus.setText("알림이 " + String.valueOf(timeCountM) + "분 설정되었습니다.");

        stopbit = 0;
        Thread thread = new Thread(new Timer());
        thread.start();
    }

    public void onCancelButtonClicked(View view)
    {
        stopbit = 1;

        textViewStatus.setText("알림 설정이 취소되었습니다.");
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

            while (true) {
                for (int i = timeCount; i >= 0; i--) {
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

                if(stopbit == 1)
                    break;

            }
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