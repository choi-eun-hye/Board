package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.MyViewHolder> {
    private ArrayList<Board> mDataSet;

    // Adapter 초기화 및 생성자로 받은 데이터로 Adapter 내 데이터 초기화
    public RecyclerDataAdapter(ArrayList<Board> mDataSet) {
        this.mDataSet = mDataSet;
    }

    /**
     * 레이아웃을 만들어서 Holer에 저장
     * 뷰 홀더를 생성하고 뷰를 붙여주는 부분
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerDataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board, parent, false);

        // 뷰 생성 후 이 뷰를 관리하기 위한 ViewHolder 생성해서 OnBindViewHolder로 넘겨줌
        return new MyViewHolder(v);
    }

    /** Replace the contents of a view (invoked by the layout manager)
     * Row 하나하나를 구현하기 위해 Bind 할 때
     * 뷰의 내용을 position의 데이터로 바꿈

     *  넘겨받은 데이터를 화면에 출력하는 역할
     *  재활용되는 뷰가 호출하여 실행되는 메소드
     *  뷰 홀더를 전달하고 어댑터는 positiond의 데이터를 결함
     *  @param holder
     *  @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // innerclass에서 쓰려면 final 선언
        final String title = mDataSet.get(position).getBoardTitle();
        final String content = mDataSet.get(position).getBoardContent();

        // 넘겨받은 뷰홀더의 레이아웃에 있는 뷰들 설정
        holder.txtName.setText(title);
        holder.txtContent.setText(content);

        // 아이템 클릭 이벤트
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                //글 창(Board Detail)으로 데이터 전달
                Intent intent = new Intent(context, BoardDetail.class);
                intent.putExtra("position", position);

                // MainActivity에서 제목, 내용을 가져와 BoardDeail로 전달
                intent.putExtra("title", title);
                intent.putExtra("content", content);

                context.startActivity(intent);
            }
        });
    }



    // Return the size of your dataset (invoked by the layout manager)
    // arrayListBoard에 있는 게시판 글 수만큼 카운트
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    // 커스텀 뷰홀더
    // layout에 존재하는 위젯들을 바인딩
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtContent;


        // 뷰홀더 생성
        public MyViewHolder(View v) {
            super(v);

            // 뷰를 넘겨받아 뷰홀더 완성
            txtName = v.findViewById(R.id.txtname);
            txtContent = v.findViewById(R.id.txtcontent);
        }
    }

}