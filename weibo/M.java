package weibo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by mac on 2017/12/10.
 */
public class M {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\mac\\Desktop\\某位心理变态的人的微博\\1005055596513223-FANS.txt"));
            while (true) {
                try {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    //href=\"\/u\/2919492144?refer_flag=1005050008_\" >
                    line = line.substring(0, line.lastIndexOf("?"));
                    line = line.substring(line.lastIndexOf("/") + 1);
                    WeiboSubFans.get(line);
                    System.out.println("-------------------------------------------------------------");
                    System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        };
    }

}
