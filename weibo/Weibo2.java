package weibo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/12/8.
 */
public class Weibo2 {

    public static List<String> get(String user_id, int page, int pagebar) {
        System.out.println("Crawler Started!");
        List<String> res = new ArrayList<String>();
        try {
            String domain = user_id.substring(0, 6);
            //download
            String url = "https://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1&feed_type=0&page=" +
                    page +
                    "&pagebar=" +
                    pagebar +
                    "&pl_name=Pl_Official_MyProfileFeed__21&id=" +
                    user_id +
                    "&pre_page=" +
                    page +
                    "&domain=" +
                    domain +
                    "&domain_op=" +
                    domain;
            //"&script_uri=/u/2919492144&__rnd=1512893948225"
            Connection conn1 = Jsoup.connect(url);
            conn1.header("Cookie", "SINAGLOBAL=3001728160306.8115.1457354239036; un=909039540@qq.com; wvr=6; YF-Page-G0=46f5b98560a83dd9bfdd28c040a3673e; SSOLoginState=1512887149; _s_tentry=login.sina.com.cn; Apache=737575193068.5404.1512887149585; ULV=1512887149625:35:2:1:737575193068.5404.1512887149585:1512140191924; YF-V5-G0=1312426fba7c62175794755e73312c7d; YF-Ugrow-G0=5b31332af1361e117ff29bb32e4d8439; SCF=Ap_-_XWAhw8BN6y2tMavhxACLqI9lIR_BGw5p8hfRYSSXPmEG4Of7vYkea2_OOxwxXew_O_bs8RcKwf3ozXbZSI.; SUB=_2A253Kl5CDeThGeRK6lYU-S3Jzj6IHXVUXsiKrDV8PUNbmtANLUn-kW9NU5KnQ2fKyxaGgThUSmJ96JzscjBw8bih; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhJ.imyhN9PvNFJ_WKogE_l5JpX5KMhUgL.FozXeKBf1KefSKz2dJLoI0qLxK-L1K5L1heLxK.L1-zLBoqLxK.LBKeL1-qLxKBLBonL12BLxKBLB.eL1-2LxKnL1heL1Kqt; SUHB=0xnTMN8e1q6qB2; ALF=1544511889; wb_cusLike_2414593552=N; UOR=www.micmiu.com,widget.weibo.com,login.sina.com.cn");
            conn1.header("Host", "weibo.com");
            conn1.header("Upgrade-Insecure-Requests", "1");
            conn1.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            conn1.header("X-Requested-With", "XMLHttpRequest");
            conn1.header("Content-Type", "application/x-www-form-urlencoded");
            conn1.ignoreContentType(true);
            Connection.Response response = conn1.execute();
            String str = response.charset("utf-8").body();
            //System.out.println(str);

            //parse res
            JSONObject jsonObject = JSONObject.fromObject(str);
            String data = jsonObject.getString("data");
            //System.out.println(data);
            Document html = Jsoup.parse(data);

            //get data
            List<String> list = html.select("div.WB_cardwrap.WB_feed_type.S_bg2.WB_feed_like > div.WB_feed_detail.clearfix > div.WB_detail > div.WB_text.W_f14").eachText();
            List<String> list1 = html.select("div.WB_cardwrap.WB_feed_type.S_bg2.WB_feed_like > div.WB_feed_handle > div.WB_handle > ul.WB_row_line.WB_row_r4.clearfix.S_line2 > li:eq(2) > a.S_txt2 > span.pos > span.line.S_line1 > span > em:eq(1)").eachText();
            List<String> list2 = html.select("div.WB_cardwrap.WB_feed_type.S_bg2.WB_feed_like").eachAttr("tbinfo");
            List<String> list3 = html.select("div.WB_cardwrap.WB_feed_type.S_bg2.WB_feed_like").eachAttr("mid");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("data is [%s]\n", list.get(i));
                res.add(list.get(i));
                if (!list1.get(i).contains("评论")) {
                    String ouid = list2.get(i);
                    String mid = list3.get(i);
                    ouid = ouid.substring(ouid.indexOf("=") + 1);
                    List<String> comments = Pinglun.get(mid, ouid);
                    for (String li : comments) {
                        res.add("[" + li + "]");
                    }
                    System.out.printf("        [%s]\n", list1.get(i));
                    System.out.printf("        [%s]\n", list2.get(i));
                    System.out.printf("        [%s]\n", list3.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Crawler Ended!");
        return res;
    }

}
