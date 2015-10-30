package social.life.cn.net;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/7/25.
 */
public class HttpRequestBody {



    public static HashMap<String,String> cityname(String cityName){
        HashMap<String ,String> map=new HashMap<String,String>();

        map.put("cityname",cityName);

        return map;


    };


}
