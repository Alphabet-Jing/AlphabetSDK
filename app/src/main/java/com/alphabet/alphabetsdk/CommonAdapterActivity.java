package com.alphabet.alphabetsdk;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alphabet.alphabetsdklib.uitls.CommonBaseAdapter;
import com.alphabet.alphabetsdklib.uitls.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CommonAdapterActivity extends ListActivity {

    private MyAdapter mMyAdapter;
    private List<Bean> mBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_adater);

        for (int i = 0 ; i < 20 ; i++){
            mBeanList.add(new Bean("Title_"+i,"Desc_"+i));
        }

        mMyAdapter = new MyAdapter(this,mBeanList,R.layout.common_adpter_item);
        setListAdapter(mMyAdapter);
        setListAdapter(mMyAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common_adater, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    static class Bean{
        private String title;
        private String desc;

        public Bean(String title, String desc) {
            this.title = title;
            this.desc = desc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    static class MyAdapter extends CommonBaseAdapter<Bean>{

        public MyAdapter(Context context, List<Bean> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void convert(ViewHolder viewHolder, Bean bean) {

        }
    }
}
