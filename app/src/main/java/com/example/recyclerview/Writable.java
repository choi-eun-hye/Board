package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Writable extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private String title, content;
    private Board board;
    //private ArrayList<Board> mDataset;
    //private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    TextView titletv, contenttv;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writable);

        btn = (Button) findViewById(R.id.txtwritebtn);
        btn.setOnClickListener(this);
        //recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        titletv = findViewById(R.id.txttitle);
        contenttv = findViewById(R.id.txtcontent);




    }


    @Override
    public void onClick(View v) {
        // 버튼 누르면 editaText에 적힌 텍스트들을 board.java에 객체로 저장
        // save texts into board.java when button pressed
        title = titletv.getText().toString();
        content = contenttv.getText().toString();


        board = new Board();
        board.setBoardTitle(title);
        board.setBoardContent(content);

      //  mDataset = new ArrayList<>();
      //  mDataset.add(board);

     //   mAdater = new RecyclerDataAdapter(mDataset);

        // 글 목록 페이지로 돌아가기
        // return to the list
       // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Intent intent = new Intent();

        //글이 추가된 mDataset을 MainActivity에 전달해서 다시 뷰를 그려야함함
        intent.putExtra("board", board);

        //intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        //startActivity(intent);

        setResult(RESULT_OK, intent);
        finish();
    }


}
