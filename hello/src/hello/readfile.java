package hello;
import java.io.*;
import java.util.Arrays; 
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
 
public class readfile { 

public static void main(String[] args) { 
	String REGEX = "\\[([^\\]]+)\\]";//^\\]:所有非]的字符串
	String REGEX1 = "<([^<>]*)>";//^\\]:所有非]的字符串
	String REGEX2 = "\n\r";//^\\]:所有非]的字符串
    String INPUT = " ";
    String REPLACE = ""; 
	String[] fileList = fileList();
	int len = fileList.length;
	System.out.println(Arrays.toString(fileList()));
	for (int i=0;i<len;i++) { 
		String Path = "C:/Users/Administrator/Desktop/weibodata/"+fileList[i]+"/CONTENT.txt";
		File readFile=new File(Path);
		//输入IO流声明
        InputStream in=null;
        InputStreamReader ir=null;
        BufferedReader br=null;
        try {
            //用流读取文件
            in=new BufferedInputStream(new FileInputStream(readFile));
            //如果你文件已utf-8编码的就按这个编码来读取，不然又中文会读取到乱码
            ir=new InputStreamReader(in,"utf-8");
            //字符输入流中读取文本,这样可以一行一行读取
            br= new BufferedReader(ir);
            String thisline="";
            String res="";
            //一行一行读取
            while((thisline=br.readLine())!=null){
                res += thisline;
            	//System.out.println(res);
                 
            }
           
            INPUT = res;
          //System.out.println(res);
            
        } catch (Exception e) {
             
            e.printStackTrace();
        }finally{
        	
            //一定要关闭流,倒序关闭
            try {
                 
                if(br!=null){
                    br.close();
                }
                if(ir!=null){
                    ir.close();
                }
                if(in!=null){
                    in.close();
                }
          
            } catch (Exception e2) {
                 
            }
             
        }
        Pattern p = Pattern.compile(REGEX);
        // get a matcher object
        Matcher m = p.matcher(INPUT); 
        INPUT = m.replaceAll(REPLACE);
       // System.out.println(INPUT);
        Pattern p1 = Pattern.compile(REGEX1);
        Matcher m1 = p1.matcher(INPUT); 
        INPUT = m1.replaceAll(REPLACE);
        //System.out.println(INPUT);
        Pattern p2 = Pattern.compile(REGEX2);
        Matcher m2 = p2.matcher(INPUT); 
        INPUT = m2.replaceAll(REPLACE);
        System.out.println("读取"+fileList[i]);
        try{
        File writename = new File("C://Users/Administrator/Desktop/zhengze/res"+i+".txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
        writename.createNewFile(); // 创建新文件  
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
        out.write(INPUT); // \r\n即为换行  
        System.out.println("写入"+fileList[i]);
        out.flush(); // 把缓存区内容压入文件  
        out.close(); // 最后记得关闭文件  
        }catch(Exception e) {             
		}
	}
	 
}

public static String[] fileList() {
	File f = new File("C:/Users/Administrator/Desktop/weibodata"); 
	String[] list = new String[100] ; 
	
	list = f.list(); 
	//cnt = list.length; 
/*	for (int i=0;i<cnt;i++) { 
	System.out.println(list[i]); 
	} */
	return list;
}

public int dMethod(int d) {
    System.out.println("有参数有返回值的方法");
    return d;
}


} 