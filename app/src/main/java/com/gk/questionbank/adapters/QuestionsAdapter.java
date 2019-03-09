package com.gk.questionbank.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gk.questionbank.R;
import com.gk.questionbank.callbacks.RecyclerListener;
import com.gk.questionbank.enums.RVClickType;
import com.gk.questionbank.view_model.Questions;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {
    private List<Questions> mList;
    private Context mContext;
    private LayoutInflater inflater;
    private RecyclerListener<Questions> mListener;
    private String text="";

    public QuestionsAdapter(Context mContext,RecyclerListener<Questions> mListener) {
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
        this.mListener=mListener;
    }

    public void notifyData(List<Questions> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }

    public void notifySearchedText(String text){
        this.text=text;
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
        private ImageButton ibtnEdit;
        private ImageButton ibtnDelete;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            txtQuestion=itemView.findViewById(R.id.txt_question);
            txtSrNo=itemView.findViewById(R.id.txt_serial);
            linMain=itemView.findViewById(R.id.lin_main);
            ibtnEdit=itemView.findViewById(R.id.ibtn_edit);
            ibtnDelete=itemView.findViewById(R.id.ibtn_delete);
        }

        public void setData(int position,Questions questions){
            txtSrNo.setText(""+(position+1)+". ");
            String ques=questions.getQuestion();
            int start=ques.indexOf(text);
            int end=text.length();
            Spannable wordtoSpan = new SpannableString(ques);
            wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            txtQuestion.setText(wordtoSpan);
        }

        public void setListeners(int position, final Questions questions){
            linMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null)
                        mListener.onClick(questions, RVClickType.CLICK);
                }
            });

            ibtnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null)
                        mListener.onClick(questions, RVClickType.EDIT);
                }
            });

            ibtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null)
                        mListener.onClick(questions, RVClickType.DELETE);
                }
            });
        }
    }
}
