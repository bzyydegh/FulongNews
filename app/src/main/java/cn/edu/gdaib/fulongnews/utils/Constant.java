package cn.edu.gdaib.fulongnews.utils;

/**
 * 常量类
 * Created by ZYongY
 * on 2016/11/08.
 */

public class Constant {

    //接口地址
    public static final String SCIENCE_API_URL = "http://api.avatardata.cn/TechNews/"; // 科技
    public static final String TOUR_API_URL = "http://api.avatardata.cn/TravelNews/"; //旅游
    public static final String PE_API_URL = "http://api.avatardata.cn/SportsNews/"; //体育
    public static final String EVENT_API_URL = "http://api.avatardata.cn/WorldNews/"; //国际
    //ApiKey
    public static final String SCIENCE_APP_KEY = "b08db76bb4a24fbab409d938072db7db"; // 科技
    public static final String TOUR_APP_KEY = "acad1d91e2b244b68bf85ed455e301fc"; // 旅游
    public static final String PE_APP_KEY = "8ac967724f68444c95610a10c6c9bbcc"; // 体育
    public static final String EVENT_APP_KEY = "d3dcc86b7500459fb96c3ef7c98b0e3b"; // 国际

    //接口调用
    public static final String SUCCESS_CODE = "0"; //调用成功返回码
    public static final String SUCCESS_FIELD = "Succes"; //调用成功返回字段

    //RecyclerView-Adapter
    public static final int TYPE_CONTENT = 0; //数据--item
    public static final int TYPE_FOOTER = 1; //footer--item
    public static final int TYPE_FOOTER_TIP = 2; //footer--tip--item
}
