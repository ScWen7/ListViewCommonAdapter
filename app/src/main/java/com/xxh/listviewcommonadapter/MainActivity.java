package com.xxh.listviewcommonadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TestBean> mDatas;
    private ListView list_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_main = (ListView) findViewById(R.id.list_main);
        initData();
        list_main.setAdapter(new ListCommonAdapter<TestBean>(this, mDatas, R.layout.item_listview) {
            @Override
            protected void convert(CommonViewHolder commonViewHolder, TestBean item, int position) {
                commonViewHolder
                        .setImageResource(R.id.iv_icon, item.getImageId())
                        .setText(R.id.tv_content, item.getContent());
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mDatas = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            mDatas.add(new TestBean(R.mipmap.image1, "" + i));
            mDatas.add(new TestBean(R.mipmap.image2, "" + i));
            mDatas.add(new TestBean(R.mipmap.image3, "" + i));
            mDatas.add(new TestBean(R.mipmap.image4, "" + i));
            mDatas.add(new TestBean(R.mipmap.image5, "" + i));
            mDatas.add(new TestBean(R.mipmap.image6, "" + i));
            mDatas.add(new TestBean(R.mipmap.image7, "" + i));
            mDatas.add(new TestBean(R.mipmap.image8, "" + i));
        }

    }
}
