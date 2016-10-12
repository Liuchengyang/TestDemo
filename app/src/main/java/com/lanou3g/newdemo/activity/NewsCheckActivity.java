package com.lanou3g.newdemo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseAty;
import com.lanou3g.newdemo.db.CheckSQLiteOpenHelper;

import com.lanou3g.newdemo.news.adapter.NewsCheckAdapter;
import com.lanou3g.newdemo.news.bean.NewsCheckBean;
import com.lanou3g.newdemo.volley.VolleySingleton;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛n
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p/>
 * Created by 刘城羊 on 16/7/10.
 */
public class NewsCheckActivity extends BaseAty implements View.OnClickListener {
    private EditText editText;
    private ImageView news_check_img;
    private TextView news_check_cancel;
    private RelativeLayout layout;
    private ImageButton news_check_close_img;
    private TextView list_search_history;

    private SQLiteDatabase db;
    private CheckSQLiteOpenHelper helper = new CheckSQLiteOpenHelper(this);

    private ListView listView;
    private ListView listViewTwo;
    private String stringInput = new String();
    private NewsCheckAdapter checkAdapter;
    private View headView1;
    private NewsCheckBean response;
    private String stringUrl;
    private TextView check_remove_text;
    private SimpleCursorAdapter simpleCursorAdapter;


    @Override
    protected int setLayout() {
        return R.layout.activity_news_check;
    }

    @Override
    protected void initView() {

        news_check_img = (ImageView) findViewById(R.id.news_check_img);
        list_search_history = (TextView) findViewById(R.id.list_search_history);
        news_check_close_img = (ImageButton) findViewById(R.id.news_check_close_img);
        listView = (ListView) findViewById(R.id.news_check_list_view1);
        headView1 = LayoutInflater.from(this).inflate(R.layout.check_list_view1, null);
        layout = (RelativeLayout) headView1.findViewById(R.id.list_head_view1);
        listView.addHeaderView(headView1);


        listViewTwo = (ListView) findViewById(R.id.news_check_list_view2);
        View headView2 = LayoutInflater.from(this).inflate(R.layout.list_view_item, null);
        View footView2 = LayoutInflater.from(this).inflate(R.layout.list_foot_item, null);
        check_remove_text = (TextView) footView2.findViewById(R.id.check_remove_text);
        listViewTwo.addHeaderView(headView2);
        listViewTwo.addFooterView(footView2);

        editText = (EditText) findViewById(R.id.news_check_edit_text);
        news_check_cancel = (TextView) findViewById(R.id.news_check_cancel);

    }

    @Override
    protected void initData() {
        layout.setOnClickListener(this);
        editText.setOnClickListener(this);
        news_check_cancel.setOnClickListener(this);
        check_remove_text.setOnClickListener(this);
        checkAdapter = new NewsCheckAdapter(this);

        queryData("");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NewsCheckActivity.this, NewsListActivity.class);


                if (response != null) {
                    int i1 = i - 1;
                    String pictureUrl = response.getData().getData().get(i1).getFeatureImg();
                    intent.putExtra("pictureUrl", pictureUrl);
                    String name = response.getData().getData().get(i1).getUser().getName();
                    intent.putExtra("name", name);
                    String title = response.getData().getData().get(i1).getTitle();
                    intent.putExtra("title", title);
                    String feedId = response.getData().getData().get(i1).getFeedId();
                    String detailsUrl = "https://rong.36kr.com/api/mobi/news/" + feedId;
                    intent.putExtra("detailsUrl", detailsUrl);
                    long publishTime = response.getData().getData().get(i1).getPublishTime();
                    intent.putExtra("publishTime", publishTime);
                    startActivity(intent);
                } else {
                    Toast.makeText(NewsCheckActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
                }

            }


        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!TextUtils.isEmpty(charSequence)) {
                    if (news_check_close_img != null) {
                        news_check_close_img.setVisibility(View.VISIBLE);
                        news_check_close_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (editText != null) {
                                    editText.getText().clear();
                                }
                            }
                        });
                    }

                } else {
                    if (news_check_close_img.getVisibility() == View.VISIBLE) {
                        news_check_close_img.setVisibility(View.GONE);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

                String tempName = editText.getText().toString();
                queryData(tempName);


            }
        });


    }

    private void queryData(String tempName) {
        final Cursor curson = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null
        );

        simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, curson, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listViewTwo.setVisibility(View.VISIBLE);
        listViewTwo.setAdapter(simpleCursorAdapter);
        listView.setVisibility(View.GONE);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.news_check_cancel:
                finish();
                break;

            case R.id.news_check_edit_text:
                listView.setVisibility(view.GONE);


                //将当前查询的关键字保存起来,如果存在就不保存

                boolean hasData = hasData(editText.getText().toString().trim());
                if (!hasData) {
                    insertData(editText.getText().toString().trim());
                    queryData("");
                }


                stringInput = editText.getText().toString();
                stringUrl = "https://rong.36kr.com/api/mobi/news/search?keyword=" + stringInput + "&page=1&pageSize=3";
                VolleySingleton.addRequest(stringUrl, NewsCheckBean.class, new Response.Listener<NewsCheckBean>() {

                    @Override
                    public void onResponse(NewsCheckBean response) {
                        NewsCheckActivity.this.response = response;
                        if (response != null) {
                            checkAdapter.setBean(response);
                            listView.setVisibility(View.VISIBLE);
                            listView.setAdapter(checkAdapter);
                            listViewTwo.setVisibility(View.GONE);


                        } else {

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                break;

            case R.id.list_head_view1:
                Intent intent = new Intent(NewsCheckActivity.this, ListHeadActivity.class);
                startActivity(intent);


                break;
            case R.id.check_remove_text:
                if (listViewTwo == null) {
                    check_remove_text.setVisibility(View.INVISIBLE);
                    list_search_history.setVisibility(View.INVISIBLE);

                }

                deleData();
                queryData("");
                break;


        }

    }

    /**
     * 清空数据
     */
    private void deleData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();

    }

    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL(" insert into records(name) values('" + tempName + "')");
        db.close();

    }

    /**
     * 检查数据库中是否已经有该条记录
     *
     * @param tempName
     * @return
     */

    private boolean hasData(String tempName) {
        Cursor curson = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =? ", new String[]{tempName}
        );
        //判断是否有下一个
        return curson.moveToNext();
    }

}
