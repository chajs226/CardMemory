package com.chajs226.cardmemory;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

/**
 * Created by amc on 2017-03-02.
 */

public class Tab2List extends Fragment {

    ListView listView;
    ListViewAdapter adapter;
    ArrayList<CardVO> list;
    int [] selectedList;
    int count = 0;
    SparseBooleanArray checkedItemPositions;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate : xml 파일을 통해 객체를 생성해 화면에 보여주느 ㄴ것..
        View rootView = inflater.inflate(R.layout.tab2list, container, false);

        //리스트뷰 참조 및 Adapter 달기
        listView = (ListView) rootView.findViewById(R.id.listview1);
        //listView.setAdapter(adapter);

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



/*
        listView.setOnLongClickListener(new listView.OnLongClickListener() {
            // Called when the user long-clicks on someView
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = getActivity().startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }
        });*/

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                final int checkedCount = listView.getCheckedItemCount();
                mode.setTitle(checkedCount + " Selected");

            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.delete:
                        count = listView.getCount();
                        checkedItemPositions = listView.getCheckedItemPositions();
                        selectedList = new int[listView.getCheckedItemCount()];
                        int index = 0;
                        for (int i=0; i<count; i++) {
                            if(checkedItemPositions.get(i)) {
                                selectedList[index] = adapter.getItemVOId(i);
                                index++;
                                Log.e("SelectedList", selectedList.toString());
                            }
                        }
                        deleteSelectedItems(selectedList);
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });


        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onButtonClicked(View view)
    {


        listView.setAdapter(null);

        //Adapter 생성
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        //리스트에 아이템 추가
        CardDAO cardDao = new CardDAO(getActivity());

        list = cardDao.getResult();

        for(int i=0; i<list.size(); i++)
        {
            if("TEXT".equals(list.get(i).getKind().trim()))
            {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.kindtexticon), list.get(i).getUpdt().toString(), list.get(i).getContents(), list.get(i).getId());
            }
            else
            {
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.kindimageicon), list.get(i).getUpdt().toString(), list.get(i).getContents(), list.get(i).getId());
            }
        }

    }

    public void deleteSelectedItems(int [] list)
    {
            //DELETE DAO 구현 필요.
    }



}
