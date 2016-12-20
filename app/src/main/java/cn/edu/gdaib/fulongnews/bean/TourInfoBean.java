package cn.edu.gdaib.fulongnews.bean;

import java.util.List;

/**
 * 旅游
 * author: ZYongY.
 * date: 2016/12/13.
 */

public class TourInfoBean {

    /**
     * error_code : 0
     * reason : Succes
     * result : [{"ctime":"1970-01-01 08:00","title":"野生动物保护前线：战火中的维龙加国家公园","description":"近年来，维龙加国家公园的数十个公园管理员先后遭到杀害。不过2015年以来，公园相对比较安宁。","picUrl":"http://imgsize.ph.126.net/?imgurl=http://img3.cache.netease.com/travel/2015/8/28/201508282326086f507_550.jpg_180x130x1x85.jpg","url":"http://travel.163.com/15/0830/09/B28NDFNB00063KE8.html"},{"ctime":"1970-01-01 08:00","title":"嗜茶如命 不只是中国人的专利","description":"尽管茶被视为中国的标志之一，但是爱茶之人可远不止中国人而已。其实，歪果仁也同样嗜茶如命，而且每一款都足以让人垂涎不已。","picUrl":"http://imgsize.ph.126.net/?imgurl=http://img2.cache.netease.com/travel/2015/8/28/20150828132637ffc2a.jpg_180x130x1x85.jpg","url":"http://travel.163.com/15/0830/09/B28NOESR00063KE8.html"},"......"]
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
         * ctime : 1970-01-01 08:00
         * title : 野生动物保护前线：战火中的维龙加国家公园
         * description : 近年来，维龙加国家公园的数十个公园管理员先后遭到杀害。不过2015年以来，公园相对比较安宁。
         * picUrl : http://imgsize.ph.126.net/?imgurl=http://img3.cache.netease.com/travel/2015/8/28/201508282326086f507_550.jpg_180x130x1x85.jpg
         * url : http://travel.163.com/15/0830/09/B28NDFNB00063KE8.html
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
