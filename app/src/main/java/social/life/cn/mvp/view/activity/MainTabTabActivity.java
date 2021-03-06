package social.life.cn.mvp.view.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import social.life.cn.R;
import social.life.cn.app.BaseActivity;
import social.life.cn.entity.bean.MainTab;
import social.life.cn.mvp.presenter.MainPresenter;
import social.life.cn.mvp.view.iview.IMainTabView;


public class MainTabTabActivity extends BaseActivity implements IMainTabView, TabHost.OnTabChangeListener {
    @Bind(R.id.navigation)
    NavigationView navigation;
    //内容显示区
    @Bind(R.id.fl_content)
    FrameLayout fl_content;
    @Bind(R.id.fth_tabhost)
    FragmentTabHost tabHost;
    private MainPresenter mainPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        initTab();
        mainPresenter=new MainPresenter(this);
        mainPresenter.IMinit(this);
        navigation.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.secondary_text)));
    }



    public void initTab(){
        tabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
       /* if (android.os.Build.VERSION.SDK_INT > 10) {
            tabHost.getTabWidget().setShowDividers(0);
        }*/
        MainTab[]tabs=MainTab.values();
        for(int i = 0; i <tabs.length; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(tabs[i].getTitle()));
            tabSpec.setIndicator(getTabItemView(tabs[i]));
            //将Tab按钮添加进Tab选项卡中
            tabHost.addTab(tabSpec, tabs[i].getClz(), null);
        }
        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(this);
    }


    public View getTabItemView(MainTab tab){
         View  view= getLayoutInflater().inflate(R.layout.activity_main_tab_item,null);
         TextView tv_title= (TextView) view.findViewById(R.id.tv_title);
         ImageView  iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
         tv_title.setText(getString(tab.getTitle()));
         iv_icon.setImageDrawable(getResources().getDrawable(tab.getDrawable()));
         return view;
    }


    @Override
    public void onTabChanged(String s) {




    }
}
