package weibo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mac on 2017/12/9.
 */
public class WeiboSubFans {

    public static final int num = 20;

    //get fans and subscirbe by uid
    public static void get(String uid) {

        String user_id = null;
        String sub = null;
        String fans = null;

        System.out.println("Crawler Started! ");
        try {
            //download
            String url = "https://weibo.com/u/" + uid;
            Connection conn1 = Jsoup.connect(url);
            conn1.header("Cookie", "SINAGLOBAL=3001728160306.8115.1457354239036; un=909039540@qq.com; wvr=6; YF-Page-G0=46f5b98560a83dd9bfdd28c040a3673e; SSOLoginState=1512887149; SCF=Ap_-_XWAhw8BN6y2tMavhxACLqI9lIR_BGw5p8hfRYSSJmVWNiJCW5spQzG6bi8gVFyCdkxMKYHgbNiAgeq4jHE.; SUB=_2A253KKM9DeThGeRK6lYU-S3Jzj6IHXVUX5P1rDV8PUNbmtANLWimkW9NU5KnQyocOBKeA5VsRVYp0CoNi7DUi6sv; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhJ.imyhN9PvNFJ_WKogE_l5JpX5KMhUgL.FozXeKBf1KefSKz2dJLoI0qLxK-L1K5L1heLxK.L1-zLBoqLxK.LBKeL1-qLxKBLBonL12BLxKBLB.eL1-2LxKnL1heL1Kqt; SUHB=0HTwgZwiyZ-rz1; ALF=1544423148; _s_tentry=login.sina.com.cn; UOR=www.micmiu.com,widget.weibo.com,login.sina.com.cn; Apache=737575193068.5404.1512887149585; ULV=1512887149625:35:2:1:737575193068.5404.1512887149585:1512140191924; YF-V5-G0=1312426fba7c62175794755e73312c7d");
            conn1.header("Host", "weibo.com");
            conn1.header("Upgrade-Insecure-Requests", "1");
            conn1.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            String doc1 = conn1.get().html();
            System.out.println(doc1);

            //data
            String pattern = "W_f18\\\\\">(.+?)<\\\\/strong><span class=\\\\\"S_txt2\\\\\">关注<\\\\/span>";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(doc1);
            while (m.find()) {
                sub = m.group(1);
                System.out.println(sub);
            }
            String pattern1 = "weibo.com(.+?)<\\\\/strong><span class=\\\\\"S_txt2\\\\\">粉丝<\\\\/span>";
            Pattern r1 = Pattern.compile(pattern1);
            Matcher m1 = r1.matcher(doc1);
            while (m1.find()) {
                String str = m1.group(1);
                str = str.substring(5);
                user_id = str.substring(0, str.indexOf("\\"));
                System.out.println(user_id);
            }
            String pattern2 = "W_f18\\\\\">(.+?)<\\\\/strong><span class=\\\\\"S_txt2\\\\\">粉丝<\\\\/span>";
            Pattern r2 = Pattern.compile(pattern2);
            Matcher m2 = r2.matcher(doc1);
            while (m2.find()) {
                String str = m2.group(1);
                fans = str.substring(str.lastIndexOf(">") + 1);
                System.out.println(fans);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //parse data
        int sub_num = Integer.parseInt(sub);
        int fans_num = Integer.parseInt(fans);

        int subpagenum = sub_num / num;
        if (sub_num % num != 0) {
            subpagenum++;
        }
        if (subpagenum > 5) {
            subpagenum = 5;
        }
        int fanspagenum = fans_num / num;
        if (fans_num % num != 0) {
            fanspagenum++;
        }
        if (fanspagenum > 5) {
            fanspagenum = 5;
        }

        for (int i = 1; i <= subpagenum; i++) {
            WeiboSUB.get(user_id, i);
            System.out.println("subpagenum: " + i);
        }
        for (int i = 1; i <= fanspagenum; i++) {
            WeiboFANS.get(user_id, i);
            System.out.println("fanspagenum: " + i);
        }
    }

}
