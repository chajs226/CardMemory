package com.chajs226.cardmemory;

/**
 * Created by amc on 2017-03-02.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tab1Insert extends Fragment{

    EditText etContents;
    TextView tvResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate : xml 파일을 통해 객체를 생성해 화면에 보여주느 ㄴ것..
        View rootView = inflater.inflate(R.layout.tab1insert, container, false);



        etContents = (EditText)rootView.findViewById(R.id.eTextContents);
        tvResult = (TextView)rootView.findViewById(R.id.textViewResult);

        Button button = (Button) rootView.findViewById(R.id.btnInsert);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onButtonClicked(v);
            }
        });
        return rootView;
    }

    public void onButtonClicked(View view)
    {
        CardDAO card = new CardDAO(getActivity());

        String contents = etContents.getText().toString();

        card.insert("TEXT", contents);
        tvResult.setText(card.getResult());
    }
}
