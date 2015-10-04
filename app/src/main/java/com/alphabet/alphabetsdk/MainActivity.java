package com.alphabet.alphabetsdk;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.alphabet.alphabetsdklib.uitls.CommonBaseAdapter;
import com.alphabet.alphabetsdklib.uitls.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private static List<ActivityBean> sActivityBeanList = new ArrayList<>();

    static {
        sActivityBeanList.add(new ActivityBean(CommonAdapterActivity.class, "万能适配器", "能大幅减少编写 adapter 的代码量"));
    }

    private CommonBaseAdapter<ActivityBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new CommonBaseAdapter<ActivityBean>(this, sActivityBeanList, new int[]{R.layout.common_adpter_item}) {
            @Override
            public void convert(ViewHolder viewHolder, int type,ActivityBean activityBean) {
                viewHolder.setText(R.id.title_tv, activityBean.getTitle())
                        .setText(R.id.content_tv, activityBean.getDesc());
            }
        };
        setListAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class ActivityBean {
        private String title;
        private String desc;
        private Class clazz;

        public ActivityBean(Class clazz, String title, String desc) {
            this.title = title;
            this.desc = desc;
            this.clazz = clazz;
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

        public Class getClazz() {
            return clazz;
        }

        public void setClazz(Class clazz) {
            this.clazz = clazz;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ActivityBean bean = sActivityBeanList.get(position);
        Intent intent = new Intent(this, bean.getClazz());
        startActivity(intent);
    }
}
