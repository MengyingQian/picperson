package weibo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mac on 2017/12/9.
 */
public class WeiboFANS {

    public static void get(String user_id, int page) {
        //store
        String path = "C:\\Users\\mac\\Desktop\\"+user_id+"-FANS.txt";
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(path), true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Crawler Started! ");
        try {
            //download
            String url = "https://weibo.com/p/" +
                    user_id + "/follow?pids=Pl_Official_HisRelation__60&relate=fans&page=" +
                    page + "&ajaxpagelet=1&ajaxpagelet_v6=1";
            //  "&__ref=%2Fp%2F1005052919492144%2Ffollow%3Frelate%3Dfans%26page%3D5%23Pl_Official_HisRelation__60&_t=FM_151288715482476"
            Connection conn1 = Jsoup.connect(url);
            conn1.header("Cookie", "SINAGLOBAL=3001728160306.8115.1457354239036; un=909039540@qq.com; wvr=6; YF-Page-G0=46f5b98560a83dd9bfdd28c040a3673e; SSOLoginState=1512887149; SCF=Ap_-_XWAhw8BN6y2tMavhxACLqI9lIR_BGw5p8hfRYSSJmVWNiJCW5spQzG6bi8gVFyCdkxMKYHgbNiAgeq4jHE.; SUB=_2A253KKM9DeThGeRK6lYU-S3Jzj6IHXVUX5P1rDV8PUNbmtANLWimkW9NU5KnQyocOBKeA5VsRVYp0CoNi7DUi6sv; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhJ.imyhN9PvNFJ_WKogE_l5JpX5KMhUgL.FozXeKBf1KefSKz2dJLoI0qLxK-L1K5L1heLxK.L1-zLBoqLxK.LBKeL1-qLxKBLBonL12BLxKBLB.eL1-2LxKnL1heL1Kqt; SUHB=0HTwgZwiyZ-rz1; ALF=1544423148; _s_tentry=login.sina.com.cn; UOR=www.micmiu.com,widget.weibo.com,login.sina.com.cn; Apache=737575193068.5404.1512887149585; ULV=1512887149625:35:2:1:737575193068.5404.1512887149585:1512140191924; YF-V5-G0=1312426fba7c62175794755e73312c7d");
            conn1.header("Host", "weibo.com");
            conn1.header("Upgrade-Insecure-Requests", "1");
            conn1.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            String doc1 = conn1.get().html();
            //System.out.println(doc1);

            //data
            String pattern = "info_name W_fb W_f14(.+?)<\\\\/a>";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(doc1);
            while (m.find()) {
                String str = m.group(1);
                str = str.substring(str.indexOf("href"));
                System.out.println(str);
                bw.append(str);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Crawler Ended!");
    }

}
