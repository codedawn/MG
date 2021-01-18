package com.codedawn.mg.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.codedawn.mg.R;
import com.codedawn.mg.entity.EduPlan;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

/**
 * @author codedawn
 * @date 2021-01-17 10:55
 */
public class EduPlanAdapter extends RecyclerView.Adapter<EduPlanAdapter.InnerHolder> {

    private Context context;
    private List<EduPlan> mData;
    public EduPlanAdapter(List<EduPlan> mData) {
        this.mData=mData;

    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.item_edu_plan_view, null);
        FoldingCell fc = view.findViewById(R.id.folding_cell);
        TextView contentBtn = view.findViewById(R.id.content_btn);
        contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);

            }
        });
        //展开
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.unfold(false);

            }
        });

        context = parent.getContext();
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if(mData!=null){
            return mData.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private TextView planName;
        private TextView period;
        private TextView semester;
        private TextView periodLabel;
        private TextView beginTime;
        private TextView endTime;
        private TextView teacherName;


        private ImageView teacherIcon;
        private TextView year;
        private TextView holiday;
        private TextView contentTitle;

        public InnerHolder(View view) {
            super(view);
            this.planName=view.findViewById(R.id.planName);
            this.period=view.findViewById(R.id.period);
            this.semester=view.findViewById(R.id.semester);
//            this.year=view.findViewById(R.id.year);
//            this.holiday=view.findViewById(R.id.holiday);
            this.periodLabel=view.findViewById(R.id.period_label);
            this.beginTime=view.findViewById(R.id.beginTime);
            this.endTime=view.findViewById(R.id.endTime);
            this.teacherName = view.findViewById(R.id.teacherName);

            this.contentTitle=view.findViewById(R.id.contentTitle);
            this.teacherIcon=view.findViewById(R.id.teacherIcon);

        }

        public void setData(EduPlan eduPlan) {
            this.planName.setText(eduPlan.getPlanName());
            this.period.setText(eduPlan.getPeriod());
            this.semester.setText(eduPlan.getSemester());
//            this.year=view.findViewById(R.id.year);
//            this.holiday=view.findViewById(R.id.holiday);
            this.periodLabel.setText("学时");
            this.beginTime.setText(eduPlan.getBeginTime());
            this.endTime.setText(eduPlan.getEndTime());
            this.teacherName.setText(eduPlan.getTeacherName());

            this.contentTitle.setText(eduPlan.getPlanName());
            Glide.with(context)
                    .load(eduPlan.getTeacherIcon())
                    .placeholder(R.drawable.head)//图片加载出来前，显示的图片
                    .error(R.drawable.head)//图片加载失败后，显示的图片
                    .into(this.teacherIcon);
        }
    }

}
