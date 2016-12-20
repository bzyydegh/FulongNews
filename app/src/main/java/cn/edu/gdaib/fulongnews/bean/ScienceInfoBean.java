package cn.edu.gdaib.fulongnews.bean;

import java.util.List;

/**
 * 科技
 * Created by ZYongY
 * on 2016/11/07.
 */

public class ScienceInfoBean {

    /**
     * result : [{"ctime":"2016-12-08 11:05","title":"专访谷歌大中华区总裁：搜索没计划入华 这没什么好说的","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/885091363_300240/0","url":"http://tech.qq.com/a/20161208/017266.htm"},{"ctime":"2016-12-08 09:55","title":"iPhone炸完iPad炸 苹果要重蹈三星覆辙？","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/884809358_300240/0","url":"http://tech.qq.com/a/20161208/012950.htm"},{"ctime":"2016-12-07 21:02","title":"富士康商洽赴美投资 苹果要回去生产？","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/883673001_300240/0","url":"http://tech.qq.com/a/20161207/035756.htm"},{"ctime":"2016-12-07 14:24","title":"外媒：华为加班文化推动其成行业第一","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/881442381_300240/0","url":"http://tech.qq.com/a/20161206/036448.htm"},{"ctime":"2016-12-07 12:08","title":"苹果2016年度精选出炉 果粉的喜好都在这里","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/881406513_300240/0","url":"http://digi.tech.qq.com/a/20161207/021401.htm"},{"ctime":"2016-12-07 12:08","title":"苹果2016年度精选出炉 果粉的喜好都在这里了","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/881406513_300240/0","url":"http://digi.tech.qq.com/a/20161207/021401.htm"},{"ctime":"2016-12-07 14:36","title":"北京多家直播类网站封停数千个违规账号","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/881486720_300240/0","url":"http://tech.qq.com/a/20161207/025234.htm"},{"ctime":"2016-12-07 14:32","title":"不当处理大量电子垃圾 苹果要付45万美元和解","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/881476604_300240/0","url":"http://tech.qq.com/a/20161207/025106.htm"},{"ctime":"2016-12-07 15:20","title":"贾跃亭回应股权质押爆仓质疑：策划够完美，黑手够无耻","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/881652685_300240/0","url":"http://tech.qq.com/a/20161207/027184.htm"},{"ctime":"2016-12-07 15:18","title":"特朗普清空所持全部股票 苹果谷歌微软一个都没留","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/881659553_300240/0","url":"http://tech.qq.com/a/20161207/027095.htm"}]
     * error_code : 0
     * reason : Succes
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
         * ctime : 2016-12-08 11:05
         * title : 专访谷歌大中华区总裁：搜索没计划入华 这没什么好说的
         * description : 腾讯科技
         * picUrl : http://inews.gtimg.com/newsapp_ls/0/885091363_300240/0
         * url : http://tech.qq.com/a/20161208/017266.htm
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
