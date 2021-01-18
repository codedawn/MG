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
import com.codedawn.mg.adapter.EduTestAdapter;
import com.codedawn.mg.entity.Datas;
import com.codedawn.mg.entity.EduTest;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;



/**
 * @author 11096
 */
public class EduTestFragment extends Fragment {


    private ArrayList<EduTest> mData;
    private RecyclerView testRecyclerView;
    private RefreshLayout testRefreshLayout;

    public EduTestFragment() {
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
        return inflater.inflate(R.layout.fragment_edu_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        testRecyclerView = getActivity().findViewById(R.id.testRecyclerView);

        testRefreshLayout = getActivity().findViewById(R.id.testRefreshLayout);
        testRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
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
        //List<DataBean>---->Adapter------>setAdapter------>显示数据。
        //创建数据集合
        mData = new ArrayList<>();

        //创建模拟数据
        for (int i = 0; i < Datas.icons.length; i++) {
            //创建数据对象
            EduTest data = new EduTest();
            data.setTitle("第 " + (i+1) + " 章测试") ;
            //添加到集合里头
            mData.add(data);
        }

        EduTestAdapter mAdapter = new EduTestAdapter(mData);
        System.out.println(mData.toString());
        testRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        testRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        testRecyclerView.setAdapter(mAdapter);
    }
}
