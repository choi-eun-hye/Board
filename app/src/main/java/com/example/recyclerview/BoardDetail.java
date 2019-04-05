package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// 목록을 클릭하면 하나의 글을 보여줄 클래스
public class BoardDetail extends AppCompatActivity {
    private TextView titledetail, contentdetail;
    private Button btndetail, mbtndetail, dbtndetail;
    private EditText titledetaile, contentdetaile;
    private String title, content;
    private int position;
    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_detail);

        // 다른 액티비티의 위젯 제어하기
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = inflater.inflate(R.layout.activity_main, null, false);


        // 값 전달받기
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
//        position = Integer.parseInt(intent.getStringExtra("position"));
        position = intent.getIntExtra("position",1);

        titledetail = findViewById(R.id.titledetail);
        contentdetail = findViewById(R.id.contentdetail);

        titledetaile = findViewById(R.id.titledetaile);
        contentdetaile = findViewById(R.id.contentdetaile);

        btndetail = findViewById(R.id.btndetail);
        mbtndetail = findViewById(R.id.mbtndetail);
        dbtndetail = findViewById(R.id.dbtndetail);

        titledetail.setText(title);
        contentdetail.setText(content);

        // Button Events
        btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Edit Mode
                if (titledetaile.isShown()) {
                    String title = titledetaile.getText().toString();
                    String content = contentdetaile.getText().toString();
                    // Save
                    Board b = new Board();
                    b.setBoardTitle(title);
                    b.setBoardContent(content);

                    // 수정된 데이터는 데이터셋에서 삭제해야함
                    // 삭제하려면 click 이벤트가 일어났던 position을 가져와야 함
                    MainActivity.mDataset.remove(position);
                    MainActivity.mDataset.add(b);
                    MainActivity.mAdapter.notifyDataSetChanged();

                    //다시 수정된 read Mode 나오게
                    titledetail.setText(title);
                    contentdetail.setText(content);
                    titledetail.setVisibility(View.VISIBLE);
                    contentdetail.setVisibility(View.VISIBLE);
                    titledetaile.setVisibility(View.GONE);
                    contentdetaile.setVisibility(View.GONE);

                } else {
                    // Read Mode
                    finish();
                }
            }
        });

        // Modify Button Click Event
        mbtndetail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                titledetail.setVisibility(View.GONE);
                contentdetail.setVisibility(View.GONE);

                titledetaile.setVisibility(View.VISIBLE);
                contentdetaile.setVisibility(View.VISIBLE);


                titledetaile.setText(titledetail.getText().toString());
                contentdetaile.setText(contentdetail.getText().toString());
            }
        });

        // Delete Button Click Event
        dbtndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.mDataset.remove(position);
                MainActivity.mAdapter.notifyDataSetChanged();

                Log.i("tenetest", String.valueOf(MainActivity.mAdapter.getItemCount()));

                if(MainActivity.mAdapter.getItemCount() == 0){

                    // VISIBILITY가 안 됨 ㅠㅠ
                    header.findViewById(R.id.my_recycler_view).setVisibility(View.GONE);
                    header.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                    Log.i("tenetest", "ok");
                    Log.i("tenetest", String.valueOf(header.findViewById(R.id.empty).getVisibility()));
                }

                finish();

            }
        });


    }
}
