package net.roseboy.classfinal;


import net.roseboy.classfinal.util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 加密普通jar，springboot jar，spring web war
 * 启动 java -jar this.jar
 * 启动2 java -jar this.jar -file springboot.jar -libjars a.jar,b.jar -packages net.roseboy,yiyon.com -exclude org.spring -pwd 995800 -Y
 *
 * @author roseboy
 */
public class Main {
    /**
     * 入口方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        run("D:\\a\\jar\\hanxiinfotech-app-start.jar");
        run("D:\\a\\war\\hanxiinfotech-app-start.war");
    }


    private static void run(String value) {

        String path = value, libjars = " hanxiinfotech-app-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-api-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-ass-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-assess-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-common-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-da-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-demo-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-hr-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-hw-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-jc-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-manage-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-meritpay-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-reportForm-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-rms-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-scheme-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-talentEvaluation-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-module-upload-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-modules-hnc-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-modules-information-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-modules-mm-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-modules-pc-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-modules-pe-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-modules-pw-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-modules-sk-1.0-SNAPSHOT.jar,\n" +
                "            hanxiinfotech-modules-ss-1.0-SNAPSHOT.jar,", packages = "", excludeClass = "", classpath = "", password = null, code = "", cfgfiles = "";

        //没有参数手动输入


        packages = "com.hanxiinfotech.**";//包名过滤
        excludeClass = "com.hanxiinfotech.StartSpringApplication";//排除的类
        password = "#";
        classpath = "";
        cfgfiles = "";
        Const.DEBUG = true;

        List<String> includeJarList = StrUtils.toList(libjars);
        List<String> packageList = StrUtils.toList(packages);
        List<String> excludeClassList = StrUtils.toList(excludeClass);
        List<String> classPathList = StrUtils.toList(classpath);
        List<String> cfgFileList = StrUtils.toList(cfgfiles);
        includeJarList.add("-");

        JarEncryptor encryptor = new JarEncryptor(path, password.trim().toCharArray());
        encryptor.setCode(StrUtils.isEmpty(code) ? null : code.trim().toCharArray());
        encryptor.setPackages(packageList);
        encryptor.setIncludeJars(includeJarList);
        encryptor.setExcludeClass(excludeClassList);
        encryptor.setClassPath(classPathList);
        encryptor.setCfgfiles(cfgFileList);
        try {
            String result = encryptor.doEncryptJar();
            Log.println("加密完成，请牢记密码！");
            Log.println("==>" + result);
        } catch (Exception e) {
            //e.printStackTrace();
            Log.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * 生成机器码
     */
    public static void makeCode() {
        String path = JarUtils.getRootPath(null);
        path = path.substring(0, path.lastIndexOf("/") + 1);

        String code = new String(SysUtils.makeMarchinCode());
        File file = new File(path, "classfinal-code.txt");
        IoUtils.writeTxtFile(file, code);
        Log.println("Server code is: " + code);
        Log.println("==>" + file.getAbsolutePath());
        Log.println();
    }
}
