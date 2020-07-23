package container.blockqueue;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

/**
 * 从指定文件夹中搜索包含指定关键字的文件
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE=10;
    private static final int SEARCH_THERDS=100;
    private static final File DUMMY=new File("");
    private static BlockingQueue<File> queue=new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);

    public static void main(String[] args){
        try(Scanner in=new Scanner(System.in)){
            System.out.print("Enter base directory （e.g. /opt/jdk1.8.0/src): ");
            String directory=in.nextLine();//目标文件夹
            System.out.print("Enter keyword (e.g. colatile): ");
            String keyword=in.nextLine();//关键字

            Runnable enumerator=()->{//
                try{
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                }catch (InterruptedException ignored){

                }
            };

            new Thread(enumerator).start();

            Runnable searcher=()->{//搜索线程
                try{
                    boolean done=false;
                    while(!done){
                        File file=queue.take();
                        if(file==DUMMY){
                            queue.put(file);
                            done=true;
                        }
                        else search(file,keyword);
                    }
                }catch (IOException|InterruptedException e) {//多catch块代码优化
                    e.printStackTrace();
                }
            };
            for(int i=1;i<=SEARCH_THERDS;i++){

                new Thread(searcher).start();
            }
        }

    }

    public static void enumerate(File directory) throws InterruptedException{
        File[] files=directory.listFiles();
        if(files!=null) {
            for (File file : files) {
                if (file.isDirectory()) enumerate(file);
                else queue.put(file);

                System.out.println(file.getName());
            }
        }else{
            System.err.println("文件夹不存在或者为空");
        }
    }

    public static void search(File file,String keyword) throws IOException{
        try(Scanner in=new Scanner(file, StandardCharsets.UTF_8)){
            int lineNumber=0;
            while(in.hasNext()){
                lineNumber++;
                String line=in.nextLine();
                if(line.contains(keyword))
                    System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
            }
        }
    }
}