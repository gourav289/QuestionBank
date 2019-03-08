package com.gk.questionbank.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gk.questionbank.R;
import com.gk.questionbank.callbacks.RecyclerListener;
import com.gk.questionbank.view_model.Questions;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {
    private List<Questions> mList;
    private Context mContext;
    private LayoutInflater inflater;
    private RecyclerListener<Questions> mListener;

    public QuestionsAdapter(Context mContext,RecyclerListener<Questions> mListener) {
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
        this.mListener=mListener;
    }

    public void notifyData(List<Questions> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_item_questions,parent,false);
        QuestionViewHolder viewHolder=new QuestionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        if(mList!=null) {
            Questions questions = mList.get(position);
            holder.setData(position, questions);
            holder.setListeners(position,questions);
        }
    }

    @Override
    public int getItemCount() {
        if(mList==null)
            return 0;
        return mList.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        private TextView txtQuestion;
        private TextView txtSrNo;
        private LinearLayout linMain;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            txtQuestion=itemView.findViewById(R.id.txt_question);
            txtSrNo=itemView.findViewById(R.id.txt_serial);
            linMain=itemView.findViewById(R.id.lin_main);
        }

        public void setData(int position,Questions questions){
            txtSrNo.setText(""+(position+1)+". ");
            txtQuestion.setText(questions.getQuestion());
        }

        public void setListeners(int position, final Questions questions){
            linMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null)
                        mListener.onClick(questions);
                }
            });
        }
    }
}
