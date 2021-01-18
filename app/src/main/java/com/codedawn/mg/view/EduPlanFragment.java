package com.codedawn.mg.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.codedawn.mg.R;
import com.codedawn.mg.adapter.EduPlanAdapter;
import com.codedawn.mg.entity.Datas;
import com.codedawn.mg.entity.EduPlan;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;



public class EduPlanFragment extends Fragment {



    private RefreshLayout mRefreshLayout;
    private static boolean isFirstEnter = true;
    private ArrayList<EduPlan> mData;
    private RecyclerView mRecyclerView;

    public EduPlanFragment() {
        // Required empty public constructor
    }


    public static EduPlanFragment newInstance() {
        EduPlanFragment fragment = new EduPlanFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edu_plan, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FlowingDrawer mDrawer = (FlowingDrawer) getActivity().findViewById(R.id.drawerlayout);

        mRecyclerView = getActivity().findViewById(R.id.mRecyclerView);

        ImageView head = getActivity().findViewById(R.id.head);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openMenu();
            }
        });
        mRefreshLayout = getActivity().findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initDate();
                refreshLayout.finishRefresh();//刷新完成
            }
        });
        if (isFirstEnter) {
            isFirstEnter = false;
            // mRefreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果

        }


        // get our folding cell

        //initDate();
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
            EduPlan data = new EduPlan();

            data.setPlanName("第 " + (i+1) + " 课程") ;
            data.setPeriod("100");
            data.setBeginTime("2021-10-1");
            data.setEndTime("2022-1-21");
            data.setSemester("第" + (i+1) + "学期");
            data.setTeacherName("雷军");
            data.setTeacherIcon("https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fimgo.liulanqi.net%252Fimg2019%252F12%252F20%252F17%252F2019122068584995.jpg%26refer%3Dhttp%253A%252F%252Fimgo.liulanqi.net%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1613539478%26t%3Da81e2590af8343cbbe07d371e05696f8&thumburl=https%3A%2F%2Fss0.bdstatic.com%2F70cFuHSh_Q1YnxGkpoWK1HF6hhy%2Fit%2Fu%3D2387240083%2C810943227%26fm%3D26%26gp%3D0.jpg");
            //添加到集合里头
            mData.add(data);
        }

        EduPlanAdapter mAdapter = new EduPlanAdapter(mData);
        System.out.println(mData.toString());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }
}
