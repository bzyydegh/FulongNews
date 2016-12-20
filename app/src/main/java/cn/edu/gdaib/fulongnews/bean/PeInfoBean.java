package cn.edu.gdaib.fulongnews.bean;

import java.util.List;

/**
 * 体育
 * author: ZYongY.
 * date: 2016/12/13.
 */

public class PeInfoBean {

    /**
     * error_code : 0
     * reason : Succes
     * result : [{"ctime":"2015-10-21 13:55","title":"海宁赛沃顿曹宇鹏袁思俊晋级第二轮 唐俊将战丁俊晖","description":"海宁赛沃顿曹宇鹏袁思俊晋级第二轮 唐俊将战丁俊晖...","picUrl":"http://d.ifengimg.com/w145_h103/y0.ifengimg.com/cmpp/2015/10/21/f9315d2e395cad64c624d60e25c84c3e_size261_w500_h350.jpg","url":"http://sports.ifeng.com/a/20151021/45898820_0.shtml"},{"ctime":"2015-10-21 13:53","title":"伊辛巴耶娃复出先参加国内赛 为保奥运或放弃世锦赛","description":"伊辛巴耶娃复出先参加国内赛 为保奥运或放弃世锦赛...","picUrl":"http://d.ifengimg.com/w145_h103/y1.ifengimg.com/a/2015_43/b58ff8d1144f2c1.jpg","url":"http://sports.ifeng.com/a/20151021/45889971_0.shtml"},"......"]
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
         * ctime : 2015-10-21 13:55
         * title : 海宁赛沃顿曹宇鹏袁思俊晋级第二轮 唐俊将战丁俊晖
         * description : 海宁赛沃顿曹宇鹏袁思俊晋级第二轮 唐俊将战丁俊晖...
         * picUrl : http://d.ifengimg.com/w145_h103/y0.ifengimg.com/cmpp/2015/10/21/f9315d2e395cad64c624d60e25c84c3e_size261_w500_h350.jpg
         * url : http://sports.ifeng.com/a/20151021/45898820_0.shtml
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
