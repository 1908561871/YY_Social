/**
 * 
 */
package social.life.cn.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import social.life.cn.event.NetChangeEvent;
import social.life.cn.exception.CrashApplication;
import social.life.cn.util.SAFUtils;
import social.life.cn.util.ToastUtils;


/**
 * @author Tony Shen
 *
 */
public class BaseActivity extends FragmentActivity{

	public static CrashApplication app;
	public int networkType;
	public String networkName;
	public static boolean isNetAvailabe;
	private boolean isShowNet;
    protected Handler mHandler = new SafeHandler(this);
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (CrashApplication) this.getApplication();
		addActivityToManager(this);
		EventBus.getDefault().register(this);

	}
	
	protected  void addActivityToManager(Activity act) {
        if (!app.activityManager.contains(act)) {
        	 app.activityManager.add(act);
	    }
	}
	
	protected void closeAllActivities() {
		for (final Activity act : app.activityManager) {
			if (act != null) {
				act.finish();
			}
		}
	}
	
	protected  void delActivityFromManager(Activity act) {
        if (app.activityManager.contains(act)) {
        	app.activityManager.remove(act);
        }
	}
	

	protected String getCurrentActivityName() {
		int size = app.activityManager.size();
		if (size > 0) {
			return app.activityManager.get(size-1).getClass().getName();
		}
		return null;
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}


	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		delActivityFromManager(this);
		mHandler.removeCallbacksAndMessages(null);
		EventBus.getDefault().unregister(this);
    }
	
	protected void toast(String message) {
		ToastUtils.show(this, message);
	}

	protected void toast(int resId) {
		ToastUtils.show(this, resId);
	}
	
    public static class SafeHandler extends Handler{
	    private final WeakReference<Activity> mActivity;
	    public SafeHandler(Activity activity) {
	        mActivity = new WeakReference<Activity>(activity);
	    }
	    @Override
	    public void handleMessage(Message msg) {
	        if(mActivity.get() == null) {
	            return;
	        }
	    }
	}


  /*  *//**
     * 设置标题
     *//*
    public void setHeadTitle(String titleName,boolean isBack,boolean isShowNet){
        TextView title= (TextView) findViewById(R.id.tv_title);
        title.setText(titleName);
		if (isBack){
			ImageView iv_back= (ImageView) findViewById(R.id.iv_back);
			iv_back.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					finish();
				}
			});
		}
        this.isShowNet=isShowNet;
		if (isShowNet && !isNetAvailabe){
			findViewById(R.id.rl_set_net).setVisibility(View.VISIBLE);
			findViewById(R.id.rl_set_net).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//todo
				}
			});
		}
    }
*/
	@Subscribe
  public void netChange(NetChangeEvent event){
		/*isNetAvailabe=event.isNetAvailable();
		RelativeLayout rl_set_net= (RelativeLayout) findViewById(R.id.rl_set_net);
		if (rl_set_net==null)
		{
			return;
		}
      if (isShowNet && !isNetAvailabe){
		  rl_set_net.setVisibility(View.VISIBLE);
		  rl_set_net.setOnClickListener(new View.OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  //todo
			  }
		  });
      }else{
		  rl_set_net.setVisibility(View.GONE);
	  }*/
  }

	/**
	 * 获取当前的网络状态
	 */
	public  void obtainNetStatue(){
		isNetAvailabe= SAFUtils.checkNetworkStatus(this);
	}


	public void showSnakeBar(String mes){
		SAFUtils.hideSoftInputFromWindow(this, getWindow().getDecorView());
		SpannableString msp=new SpannableString(mes);
		msp.setSpan(new ForegroundColorSpan(Color.WHITE), 0, mes.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为白色
		Snackbar.make(getWindow().getDecorView(),msp,Snackbar.LENGTH_SHORT).show();
	}






}
