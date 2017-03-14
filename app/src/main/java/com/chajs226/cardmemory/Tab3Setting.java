package com.chajs226.cardmemory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by amc on 2017-03-02.
 */

public class Tab3Setting extends Fragment {

    private ProgressDialog progressDialog;

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

        return rootView;
    }

    public void onButtonClicked(View view)
    {
        progressDialog.show();
        Thread thread = new Thread(new Timer());
        thread.start();
    }

    public class Timer implements Runnable {

        @Override
        public void run() {
            for (int i = 5; i >= 0; i--) {
                try {
                    Thread.sleep((1000));
                } catch (Exception e) {
                }
            }
            progressDialog.dismiss();
        }
    }
}