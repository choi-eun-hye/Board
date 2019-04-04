package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// 목록을 클릭하면 하나의 글을 보여줄 클래스
public class BoardDetail extends AppCompatActivity {
    private TextView titledetail, contentdetail;
    private Button btndetail;
    String title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_detail);

        // 값 전달받기
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");

        titledetail = findViewById(R.id.titledetail);
        contentdetail = findViewById(R.id.contentdetail);
        btndetail = findViewById(R.id.btndetail);

        titledetail.setText(title);
        contentdetail.setText(content);

        // 버튼 누르면 뒤로가기
        btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
