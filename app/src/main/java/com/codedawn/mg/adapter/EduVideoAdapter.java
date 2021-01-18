package com.codedawn.mg.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.codedawn.mg.R;
import com.codedawn.mg.entity.EduVideo;
import com.codedawn.mg.view.PlayerActivity;

import java.util.List;

/**
 * @author codedawn
 * @date 2021-01-17 21:43
 */
public class EduVideoAdapter extends RecyclerView.Adapter<EduVideoAdapter.InnerHolder>{


    private Context context;
    private List<EduVideo> mData;

    public EduVideoAdapter(List<EduVideo> mData) {
        this.mData = mData;
    }
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_edu_video, null);
        context=parent.getContext();
        return new EduVideoAdapter.InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("eduVideo", mData.get(position));
                intent.putExtras(bundle);
               context.startActivity(intent);
                System.out.println(mData.get(position));
            }
        });
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
        private ImageView videoIcon;
        private TextView videoTitle;
        private View view;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            this.videoIcon=itemView.findViewById(R.id.videoIcon);
            this.videoTitle = itemView.findViewById(R.id.videoTitle);
            this.view = itemView;

        }

        public void setData(EduVideo eduVideo) {
            this.videoIcon.setImageResource(eduVideo.getIcon());
            this.videoTitle.setText(eduVideo.getTitle());
        }
    }
}
