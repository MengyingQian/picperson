package hello;
import java.io.*;
import java.util.Arrays; 
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
 
public class readfile { 

public static void main(String[] args) { 
	String REGEX = "\\[([^\\]]+)\\]";//^\\]:���з�]���ַ���
	String REGEX1 = "<([^<>]*)>";//^\\]:���з�]���ַ���
	String REGEX2 = "\n\r";//^\\]:���з�]���ַ���
    String INPUT = " ";
    String REPLACE = ""; 
	String[] fileList = fileList();
	int len = fileList.length;
	System.out.println(Arrays.toString(fileList()));
	for (int i=0;i<len;i++) { 
		String Path = "C:/Users/Administrator/Desktop/weibodata/"+fileList[i]+"/CONTENT.txt";
		File readFile=new File(Path);
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
        System.out.println("��ȡ"+fileList[i]);
        try{
        File writename = new File("C://Users/Administrator/Desktop/zhengze/res"+i+".txt"); // ���·�������û����Ҫ����һ���µ�output��txt�ļ�  
        writename.createNewFile(); // �������ļ�  
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
        out.write(INPUT); // \r\n��Ϊ����  
        System.out.println("д��"+fileList[i]);
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ǵùر��ļ�  
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
    System.out.println("�в����з���ֵ�ķ���");
    return d;
}


} 