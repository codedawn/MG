package com.codedawn.mg.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.codedawn.mg.R;
import com.codedawn.mg.adapter.MyFragmentAdapter;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import devlight.io.library.ntb.NavigationTabBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codedawn
 */
public class HomeActivity extends AppCompatActivity {

    private List<Fragment> mFragmentList;
    private FlowingDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        initDrawer();


    }

    /**
     * 初始化侧边栏
     */
    private void initDrawer(){
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });
    }
    /**
     *  初始化地下导航栏
     */
    private void initUI() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new EduPlanFragment());
        mFragmentList.add(new FragmentB());
        mFragmentList.add(new EduVideoFragment());
        mFragmentList.add(new EduTestFragment());
        mFragmentList.add(new FragmentE());
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragmentList);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(myFragmentAdapter);

        final String[] colors = getResources().getStringArray(R.array.colors);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        //图标
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        //选中后图标
                        //.selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("教学计划")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                      //  .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("专题课件")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                      //  .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("教学视频")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
                      //  .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("在线测试")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(

                        getResources().getDrawable(R.drawable.ic_fifth),
                        Color.parseColor(colors[4]))

                     //   .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("经典阅读")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
        //显示小标，有上面消息之类的，类似qq的99+
//        navigationTabBar.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
//                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
//                    navigationTabBar.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            model.showBadge();
//                        }
//                    }, i * 100);
//                }
//            }
//        }, 500);
    }
}
