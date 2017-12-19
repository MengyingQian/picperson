package weibo;

import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.util.List;

/**
 * Created by mac on 2017/12/9.
 */
public class Pinglun {

    public static List<String> get(String mid, String ouid) {
        System.out.println("----------comment--------------");
        try {
            //download
            String url = "https://weibo.com/aj/v6/comment/small?ajwvr=6&act=list&mid=" +
                    mid +
                    "&uid=2414593552&isMain=true&dissDataFromFeed=%5Bobject%20Object%5D&ouid=" +
                    ouid +
                    "&comment_type=0&_t=0";
            //"&location=page_100306_home&__rnd=1512895643350"
            Connection conn = Jsoup.connect(url);
            conn.header("Cookie", "SINAGLOBAL=3001728160306.8115.1457354239036; un=909039540@qq.com; wvr=6; YF-Page-G0=46f5b98560a83dd9bfdd28c040a3673e; SSOLoginState=1512887149; _s_tentry=login.sina.com.cn; Apache=737575193068.5404.1512887149585; ULV=1512887149625:35:2:1:737575193068.5404.1512887149585:1512140191924; YF-V5-G0=1312426fba7c62175794755e73312c7d; YF-Ugrow-G0=5b31332af1361e117ff29bb32e4d8439; SCF=Ap_-_XWAhw8BN6y2tMavhxACLqI9lIR_BGw5p8hfRYSSXPmEG4Of7vYkea2_OOxwxXew_O_bs8RcKwf3ozXbZSI.; SUB=_2A253Kl5CDeThGeRK6lYU-S3Jzj6IHXVUXsiKrDV8PUNbmtANLUn-kW9NU5KnQ2fKyxaGgThUSmJ96JzscjBw8bih; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhJ.imyhN9PvNFJ_WKogE_l5JpX5KMhUgL.FozXeKBf1KefSKz2dJLoI0qLxK-L1K5L1heLxK.L1-zLBoqLxK.LBKeL1-qLxKBLBonL12BLxKBLB.eL1-2LxKnL1heL1Kqt; SUHB=0xnTMN8e1q6qB2; ALF=1544511889; wb_cusLike_2414593552=N; UOR=www.micmiu.com,widget.weibo.com,login.sina.com.cn");
            conn.header("Host", "weibo.com");
            conn.header("Upgrade-Insecure-Requests", "1");
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            conn.header("X-Requested-With", "XMLHttpRequest");
            conn.ignoreContentType(true);
            Connection.Response response = conn.execute();
            String str = response.charset("utf-8").body();
            System.out.println(str);

            //parse res
            JSONObject jsonObject = JSONObject.fromObject(str);
            String data = jsonObject.getJSONObject("data").getString("html");
            //System.out.println(data);
            Document html = Jsoup.parse(data);

            //get data
            List<String> list = html.select("div > div.WB_repeat.S_line1 > div.repeat_list > div.list_box > div.list_ul > div.list_li.S_line1.clearfix > div.list_con > div.WB_text").eachText();
            for (String li : list) {
                System.out.printf("data is [%s]\n", li);
            }
            List<String> list1 = html.select("div > div.WB_repeat.S_line1 > div.repeat_list > div.list_box > div.list_ul > div.list_li.S_line1.clearfix > div.list_con > div.list_box_in.S_bg3 > div.list_ul > div.list_li.S_line1.clearfix > div.list_con > div.WB_text").eachText();
            for (String li : list1) {
                System.out.printf("data is [%s]\n", li);
            }
            list.addAll(list1);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("----------comment--------------");
        return null;
    }

}
