package hello;
import java.io.BufferedReader;
import java.io.BufferedWriter;  
import java.io.FileWriter;  
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
 
 
 
 
public class helloworld {
  /*  //private static String REGEX = "\\[(\\S+)\\]";
	private static String REGEX = "\\[([^\\]]+)\\]";//^\\]:���з�]���ַ���
	private static String REGEX1 = "<([^<>]*)>";//^\\]:���з�]���ַ���
	private static String REGEX2 = "\n\r";//^\\]:���з�]���ַ���
    private static String INPUT = " ";
   //                                 "All dogs [say] meow.";
    private static String REPLACE = ""; 
    public static void main(String[] args){
    //��ȡҪ��ȡ���ļ�
         File readFile=new File("C://Users/Administrator/Desktop/zhengze/CONTENT.txt");
         //����IO������
            InputStream in=null;
            InputStreamReader ir=null;
            BufferedReader br=null;
             
            try {
                //������ȡ�ļ�
                in=new BufferedInputStream(new FileInputStream(readFile));
                //������ļ���utf-8����ľͰ������������ȡ����Ȼ�����Ļ��ȡ������
                ir=new InputStreamReader(in,"utf-8");
                //�ַ��������ж�ȡ�ı�,��������һ��һ�ж�ȡ
                br= new BufferedReader(ir);
                String thisline="";
                String res="";
                //һ��һ�ж�ȡ
                while((thisline=br.readLine())!=null){
                    res += thisline;
                	//System.out.println(res);
                     
                }
               
                INPUT = res;
              //System.out.println(res);
                
            } catch (Exception e) {
                 
                e.printStackTrace();
            }finally{
            	
                //һ��Ҫ�ر���,����ر�
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
            //System.out.println(INPUT);
            try{
            File writename = new File("C://Users/Administrator/Desktop/zhengze/CONTENT1.txt"); // ���·�������û����Ҫ����һ���µ�output��txt�ļ�  
            writename.createNewFile(); // �������ļ�  
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
            out.write(INPUT); // \r\n��Ϊ����  
            out.flush(); // �ѻ���������ѹ���ļ�  
            out.close(); // ���ǵùر��ļ�  
            }catch(Exception e) {  
                
    }
    }
     
     */
}