package com.codedawn.mg.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.codedawn.mg.R;
import com.codedawn.mg.entity.EduTest;

import java.util.List;

/**
 * @author codedawn
 * @date 2021-01-18 13:31
 */
public class EduTestAdapter extends RecyclerView.Adapter<EduTestAdapter.InnerHolder>{

    private List<EduTest> mData;

    public EduTestAdapter(List<EduTest> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public EduTestAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_edu_test, null);
        return new EduTestAdapter.InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EduTestAdapter.InnerHolder holder, int position) {
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

        private TextView testTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            this.testTitle = itemView.findViewById(R.id.testTitle);
        }

        public void setData(EduTest eduTest) {
            this.testTitle.setText(eduTest.getTitle());
        }
    }
}
