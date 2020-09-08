package com.meituan.qa.meishi.Quake;

import java.io.CharArrayWriter;
        import java.io.File;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.util.stream.Stream;

/**
 * @author byq
 * @date 2020/9/5
 */
public class demo {
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/buyuqi/Desktop/美团侧到综下单_slice_0_20200905183449129.:quake.log";
        // 内存流, 作为临时流
        CharArrayWriter tempStream = new CharArrayWriter();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(s -> {
                s = s.replaceAll("\"t\":", "\"pragma-token\":");
//                s = s.replaceAll("\"Referer\":", "\"pragma-uuid\":");
//                s = s.replaceAll("\"MT-TABLE-ID\":", "\"pragma-dpid\":");
//                s = s.replaceAll("\"MT-QR-CODE\":", "\"pragma-device\":");
                // 将该行写入内存
                try {
                    tempStream.write(s);
                    // 添加换行符
                    tempStream.append(System.getProperty("line.separator"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        File fileWrite = new File("./quakeNew.log");
        try (FileWriter fileWriter = new FileWriter(fileWrite)) {
            tempStream.writeTo(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("success");
    }

}
