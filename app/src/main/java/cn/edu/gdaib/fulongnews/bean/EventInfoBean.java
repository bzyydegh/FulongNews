package cn.edu.gdaib.fulongnews.bean;

import java.util.List;

/**
 * 国际
 * author: ZYongY.
 * date: 2016/12/13.
 */

public class EventInfoBean {

    /**
     * error_code : 0
     * reason : Succes
     * result : [{"ctime":"2015-10-21 13:52","title":"美前总统特别助理包道格：中美关系可能会不好","description":"美前总统特别助理包道格：中美关系可能会不好...","picUrl":"http://photocdn.sohu.com/20151021/Img423793409_ss.jpg","url":"http://news.sohu.com/20151021/n423793408.shtml"},{"ctime":"2015-10-21 13:38","title":"日媒：只有印度和中国作出努力才能消除亚洲雾霾","description":"日媒：只有印度和中国作出努力才能消除亚洲雾霾...","picUrl":"http://photocdn.sohu.com/20151021/Img423792715_ss.jpg","url":"http://news.sohu.com/20151021/n423792311.shtml"},"......"]
     */

    private int error_code;
    private String reason;
    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ctime : 2015-10-21 13:52
         * title : 美前总统特别助理包道格：中美关系可能会不好
         * description : 美前总统特别助理包道格：中美关系可能会不好...
         * picUrl : http://photocdn.sohu.com/20151021/Img423793409_ss.jpg
         * url : http://news.sohu.com/20151021/n423793408.shtml
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
