package social.life.cn.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import de.greenrobot.event.EventBus;
import social.life.cn.event.NetChangeEvent;
import social.life.cn.util.SAFUtils;


public class NetChangeReceiver extends BroadcastReceiver {
    public NetChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            EventBus.getDefault().post(new NetChangeEvent(SAFUtils.checkNetworkStatus(context)));
        }
    }

}

