package com.codedawn.mg.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.codedawn.mg.R;
import com.codedawn.mg.adapter.EduVideoAdapter;
import com.codedawn.mg.entity.Datas;
import com.codedawn.mg.entity.EduVideo;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;


public class EduVideoFragment extends Fragment {


    private RecyclerView videoRecyclerView;
    private ArrayList<EduVideo> mData;
    private RefreshLayout videoRefreshLayout;

    public EduVideoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edu_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoRecyclerView = getActivity().findViewById(R.id.videoRecyclerView);


        videoRefreshLayout = getActivity().findViewById(R.id.videoRefreshLayout);
        videoRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initDate();
                refreshLayout.finishRefresh();//刷新完成
            }
        });
    }
    /**
     * 初始化数据
     */
    private void initDate() {
        //List<DataBea>---->Adapter------>setAdapter------>显示数据。
        //创建数据集合
        mData = new ArrayList<>();

        //创建模拟数据
        for (int i = 0; i < Datas.icons.length; i++) {
            //创建数据对象
            EduVideo data = new EduVideo();
            data.setIcon(Datas.icons[i]);
            data.setTitle("我是第 " + i + " 章视频") ;
            //添加到集合里头
            data.url = "http://stream4.iqilu.com/ksd/video/2020/02/17/c5e02420426d58521a8783e754e9f4e6.mp4";
            mData.add(data);
        }

        EduVideoAdapter mAdapter = new EduVideoAdapter(mData);
        System.out.println(mData.toString());
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        videoRecyclerView.setAdapter(mAdapter);
    }
}
