package weibo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by mac on 2017/12/10.
 */
public class M2 {

    public static void main(String[] args) {
        //define
        int len = 27;
        String[] uids = new String[len];
        String[] user_ids = new String[len];
        int[] pages = new int[len];

        //init
        int idx = 0;
        uids[idx] = "2804646810"; user_ids[idx] = "1005052804646810"; pages[idx] = 160;
        idx = 1;
        uids[idx] = "2919812162"; user_ids[idx] = "1005052919812162"; pages[idx] = 157;
        idx = 2;
        uids[idx] = "3044154400"; user_ids[idx] = "1005053044154400"; pages[idx] = 121;
        idx = 3;
        uids[idx] = "2790035472"; user_ids[idx] = "1005052790035472"; pages[idx] = 60;
        idx = 4;
        uids[idx] = "1582525460"; user_ids[idx] = "1005051582525460"; pages[idx] = 26;
        idx = 5;
        uids[idx] = "1440396603"; user_ids[idx] = "1005051440396603"; pages[idx] = 72;
        idx = 6;
        uids[idx] = "3044659812"; user_ids[idx] = "1005053044659812"; pages[idx] = 122;
        idx = 7;
        uids[idx] = "2919700422"; user_ids[idx] = "1005052919700422"; pages[idx] = 80;
        idx = 8;
        uids[idx] = "2790080670"; user_ids[idx] = "1005052790080670"; pages[idx] = 165;
        idx = 9;
        uids[idx] = "1582849042"; user_ids[idx] = "1005051582849042"; pages[idx] = 140;
        idx = 10;
        uids[idx] = "1582598831"; user_ids[idx] = "1005051582598831"; pages[idx] = 47;
        idx = 11;
        uids[idx] = "3044277424"; user_ids[idx] = "1005053044277424"; pages[idx] = 126;
        idx = 12;
        uids[idx] = "2920353730"; user_ids[idx] = "1005052920353730"; pages[idx] = 154;
        idx = 13;
        uids[idx] = "1589722133"; user_ids[idx] = "1005051589722133"; pages[idx] = 141;
        idx = 14;
        uids[idx] = "1581744814"; user_ids[idx] = "1005051581744814"; pages[idx] = 21;
        idx = 15;
        uids[idx] = "2789910852"; user_ids[idx] = "1005052789910852"; pages[idx] = 83;
        idx = 16;
        uids[idx] = "1778380581"; user_ids[idx] = "1005051778380581"; pages[idx] = 40;
        idx = 17;
        uids[idx] = "1778385394"; user_ids[idx] = "1005051778385394"; pages[idx] = 141;
        idx = 18;
        uids[idx] = "2804746502"; user_ids[idx] = "1005052804746502"; pages[idx] = 47;
        idx = 19;
        uids[idx] = "2789890612"; user_ids[idx] = "1005052789890612"; pages[idx] = 80;
        idx = 20;
        uids[idx] = "2919349112"; user_ids[idx] = "1005052919349112"; pages[idx] = 156;
        idx = 21;
        uids[idx] = "2789767944"; user_ids[idx] = "1005052789767944"; pages[idx] = 83;
        idx = 22;
        uids[idx] = "2809533470"; user_ids[idx] = "1005052809533470"; pages[idx] = 160;
        idx = 23;
        uids[idx] = "2919924430"; user_ids[idx] = "1005052919924430"; pages[idx] = 41;
        idx = 24;
        uids[idx] = "2802828120"; user_ids[idx] = "1005052802828120"; pages[idx] = 169;
        idx = 25;
        uids[idx] = "1647288701"; user_ids[idx] = "1005051647288701"; pages[idx] = 3;
        idx = 26;
        uids[idx] = "3045365392"; user_ids[idx] = "1005053045365392"; pages[idx] = 125;

        //execute
        for (int i = 0; i < len; i++) {
            if (i==0)
                i=17;
            get(uids[i], user_ids[i], pages[i]);
            System.out.println(uids[i]);
            System.out.println(user_ids[i]);
            System.out.println(pages[i]);
        }
    }

    public static void get(String uid, String user_id, int page) {
        //store
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\mac\\Desktop\\"+ user_id +".txt"), true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= page; i++) {
            if (i==1 && user_id.contains("1005051778385394"))
                i=94;
            try {
                List<String> res = Weibo1.get(uid, i);
                for (String li : res) {
                    bw.append(li);
                    bw.newLine();
                }
                List<String> res1 = Weibo2.get(user_id, i, 0);
                for (String li : res1) {
                    bw.append(li);
                    bw.newLine();
                }
                List<String> res2 = Weibo2.get(user_id, i, 1);
                for (String li : res2) {
                    bw.append(li);
                    bw.newLine();
                }
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //interval
            System.out.println("------------------current page is: " + i + "---------------------");
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //save
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
