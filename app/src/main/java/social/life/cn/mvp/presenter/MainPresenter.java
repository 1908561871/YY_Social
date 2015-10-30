package social.life.cn.mvp.presenter;

import android.content.Context;

import social.life.cn.mvp.model.MainModel;
import social.life.cn.mvp.model.imodel.IMainModel;
import social.life.cn.mvp.view.iview.IMainView;


/**
 * Created by zhangweibo on 2015/8/4.
 */
public class MainPresenter {

    private IMainView iMainView;
    private IMainModel iMainModel;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
        iMainModel=  new MainModel();
    }


    public void IMinit(Context context){

        iMainModel.IMinit(context);
    }




}
