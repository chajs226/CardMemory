package com.chajs226.cardmemory;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amc on 2017-03-02.
 */

public class Tab2List extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate : xml 파일을 통해 객체를 생성해 화면에 보여주느 ㄴ것..
        View rootView = inflater.inflate(R.layout.tab2list, container, false);

 /*       ListView listView;
        ListViewAdapter adapter;

        //Adapter 생성
        adapter = new ListViewAdapter();

        //리스트뷰 참조 및 Adapter 달기
        listView = (ListView) rootView.findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        //리스트에 아이템 추가
        CardDAO cardDao = new CardDAO(getActivity());
        List<CardVO> list = new ArrayList<CardVO>();

        list = cardDao.getResult();

        for(int i=0; i<list.size(); i++)
        {
            if("TEXT".equals(list.get(i).getKind().trim()))
            {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.kindtexticon), list.get(i).getUpdt().toString(), list.get(i).getContents());
            }
            else
            {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.kindimageicon), list.get(i).getUpdt().toString(), list.get(i).getContents());
            }
        }
*/
        onButtonClicked(rootView);

        Button button = (Button) rootView.findViewById(R.id.btnList);
        button.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                onButtonClicked(v);
            }
        });

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onButtonClicked(View view)
    {
        ListView listView;
        ListViewAdapter adapter;

        //Adapter 생성
        adapter = new ListViewAdapter();

        //리스트뷰 참조 및 Adapter 달기
        listView = (ListView) view.findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        //리스트에 아이템 추가
        CardDAO cardDao = new CardDAO(getActivity());
        List<CardVO> list = new ArrayList<CardVO>();

        list = cardDao.getResult();

        for(int i=0; i<list.size(); i++)
        {
            if("TEXT".equals(list.get(i).getKind().trim()))
            {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.kindtexticon), list.get(i).getUpdt().toString(), list.get(i).getContents());
            }
            else
            {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.kindimageicon), list.get(i).getUpdt().toString(), list.get(i).getContents());
            }
        }
    }


}
