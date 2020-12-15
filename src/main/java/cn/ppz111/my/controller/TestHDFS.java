package cn.ppz111.my.controller;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * @author Yuki
 */
public class TestHDFS {
    public static void main(String[] args) throws IOException {
        testRead();
    }

    private static void testRead() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("182.92.164.133:8020/xrsunc.sh");
        FSDataInputStream fis = fs.open(path);
        int len = 0;
        byte[] buf = new byte[4096];
        while((len=fis.read(buf) )!= -1){
            System.out.println(new String(buf,0,len));
        }
    }
}
