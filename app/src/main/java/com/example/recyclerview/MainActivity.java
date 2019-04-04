package com.example.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button clickbtn;
    private ArrayList<Board> mDataset;
    private Board mboard;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();


        // 글쓰기 버튼
        clickbtn = (Button) findViewById(R.id.writebtn);
        clickbtn.setOnClickListener(this);

        // 글 목록 누르면 글 보이기 페이지로 이동
        //recyclerView.

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("tenetest","ok");
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case 3000:
                                        Log.i("tenetest","switch ok");
                    mDataset.add((Board)data.getSerializableExtra("board"));
                    //mAdapter.notifyItemInserted(0);

                    Board b = (Board) data.getSerializableExtra("board");
                    Log.i("tenetest", b.getBoardTitle());

                    mAdapter.notifyDataSetChanged();
                    break;
            }

        }

        Log.i("tenetest","switch no");
    }

    // 글쓰기 버튼을 누르면 글쓰기 액티비티로 이동
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), Writable.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, 3000);

    }

    // RecyclerView에 목록 띄워줄 함수
    protected void setRecyclerView(){

        // xml에 작성해둔 RecyclerView를 java로 가져와 초기화
        recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        mDataset = new ArrayList<>();

        /*
        // 빈 게시판일 경우 처리
        if(mDataset.size()==0) {
            Board b = new Board();
            b.setBoardTitle("empty");
            b.setBoardContent("empty");
            mDataset.add(b);
        }
*/

        // 빈 게시판일 경우
        /*TextView emptytv = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout().LayoutParams(0, 0);
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;

        emptytv.setLayoutParams(params);

        emptytv.setText("Empty Page");
        emptytv.setGravity(Gravity.CENTER);

        findViewById(R.id.my_recycler_view).add
        */

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        // getOrientation()은 LinearLayoutManager에만 제공되는 함수여서 선언을 바꿈
        recyclerView.addItemDecoration(dividerItemDecoration);

        // specify an adapter (see also next example)
        // RecyclerView에 Adapter 설정
        mAdapter = new RecyclerDataAdapter(mDataset);
        recyclerView.setAdapter(mAdapter);

        //setData();

    }

}
