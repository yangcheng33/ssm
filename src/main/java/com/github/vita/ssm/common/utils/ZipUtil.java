package com.github.vita.ssm.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩与解压
 *
 * @author HuQingmiao
 */
public class ZipUtil {

    // 压缩过程中产生的临时文件的扩展名
    private static final String EXTEND_NAME_TMP = ".tmp";

    // 创建的压缩文件的扩展名
    private static final String EXTEND_NAME_ZIP = ".zip";

    /**
     * 压缩
     *
     * @param inFile  读入的需要压缩的文件或目录
     * @param outFile 输出的压缩文件
     * @return 是否成功处理
     */
    public static boolean zip(File inFile, File outFile) throws IOException {

        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        try {
            // 压缩过程中, 先输出到临时文件. 待压缩完成后, 再将临时文件改名为正式文件名
            File tmpOutFile = new File(outFile.getCanonicalPath()
                    + EXTEND_NAME_TMP);
            fos = new FileOutputStream(tmpOutFile);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));

            recursion(inFile.getCanonicalPath(), inFile, zos);

            zos.flush();
            zos.close();

            // 压缩完成, 将临时文件改名为正式文件名
            if (outFile.exists()) {
                outFile.delete();
            }
            tmpOutFile.renameTo(outFile);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (zos != null) {
                zos.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (fis != null) {
                fis.close();
            }
        }

        return true;
    }


    /**
     * 压缩
     *
     * @param inFiles 读入的需要压缩的文件集合
     * @param outFile 输出的压缩文件
     * @return 是否成功处理
     */
    public static boolean zip(List<File> inFiles, File outFile) throws IOException {

        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        try {
            // 压缩过程中, 先输出到临时文件. 待压缩完成后, 再将临时文件改名为正式文件名
            File tmpOutFile = new File(outFile.getCanonicalPath()
                    + EXTEND_NAME_TMP);
            fos = new FileOutputStream(tmpOutFile);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));

            for (File f : inFiles) {
                if (!f.exists()) {
                    continue;
                }
                recursion(f.getCanonicalPath(), f, zos);
            }

            zos.flush();
            zos.close();

            // 压缩完成, 将临时文件改名为正式文件名
            if (outFile.exists()) {
                outFile.delete();
            }
            tmpOutFile.renameTo(outFile);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (zos != null) {
                zos.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (fis != null) {
                fis.close();
            }
        }

        return true;
    }

    /**
     * 对某文件进行压缩, 返回压缩后的文件;
     *
     * @param inFile 读入的需要压缩的文件或目录
     * @return 压缩成功, 则返回压缩后的文件; 否则返回null
     */
    public static File zip(File inFile) throws IOException {
        String outFilename = null;

        if (inFile.isDirectory()) {
            outFilename = inFile.getCanonicalPath() + EXTEND_NAME_ZIP;
        } else {
            int idx = inFile.getCanonicalPath().lastIndexOf('.');
            outFilename = inFile.getCanonicalPath().substring(0, idx)
                    + EXTEND_NAME_ZIP;
        }

        File outFile = new File(outFilename);

        if (zip(inFile, outFile)) {
            return outFile;
        }
        return null;
    }

    /**
     * 递归对某文件或某目录下的所有文件进行压缩
     *
     * @param firstAbsFilename 第一遍递归时, 读入文件的绝对文件名, 即待压缩文件的第一层文件的绝对文件名
     * @param inFile           当前读入的待压缩的文件
     * @param zos              输出的ZipOutputStream对象
     * @throws java.io.IOException
     */
    private static void recursion(String firstAbsFilename, File inFile,
                                  ZipOutputStream zos) throws IOException {

        final int BUFFER_SIZE = 4096;

        BufferedInputStream bis = null;
        FileInputStream fis = null;
        try {

            File[] files = null;
            if (inFile.isDirectory()) {// 如果是目录文件, 则递归对其下的文件处理
                files = inFile.listFiles();

                for (int i = 0; i < files.length; i++) {
                    recursion(firstAbsFilename, files[i], zos);
                }

            } else {// 如果是文件, 则写入输出流

                // 待压缩文件的第一层文件的文件名
                int idx = firstAbsFilename.lastIndexOf(File.separator);
                String firstFilename = firstAbsFilename.substring(idx + 1);

                // 当前待压缩项的绝对文件名
                String inAbsFilename = inFile.getCanonicalPath().trim();

                // 当前待压缩项的相对于第一层文件的文件名
                String contextFilename = "";
                if (inAbsFilename.length() > firstAbsFilename.length()) {
                    contextFilename = inAbsFilename.substring(firstAbsFilename
                            .trim().length() + 1);
                }

                String entryName = null;
                if ("".equals(contextFilename)) {
                    entryName = firstFilename;
                } else {
                    entryName = firstFilename + File.separator
                            + contextFilename;
                }

                byte data[] = new byte[BUFFER_SIZE];

                fis = new FileInputStream(inFile);
                bis = new BufferedInputStream(fis, BUFFER_SIZE);

                // 把文件放入压缩流
                ZipEntry entry = new ZipEntry(entryName);
                zos.putNextEntry(entry);

                int count;
                while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
                    zos.write(data, 0, count);
                }
                zos.flush();

                bis.close();
                fis.close();
            }

        } catch (IOException e) {
            throw e;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * 解压缩, 解压后得到的释放的文件列表
     *
     * @param inFile 读入的需要解压的文件
     * @return 解压后得到的文件列表, 不含目录
     */
    public static List unzip(File inFile) throws IOException {
        final int BUFFER_SIZE = 4096;

        List fileList = new ArrayList();
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        ZipFile zipFile = null;
        try {
            // 默认输出目录为当前目录
            File outFile = inFile.getParentFile();

            zipFile = new ZipFile(inFile);
            Enumeration emu = zipFile.entries();
            while (emu.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) emu.nextElement();

                // 在此, 只是把目录作为一个file读出来, 其下的文件将在后面被迭代到
                if (entry.isDirectory()) {
                    new File(outFile.getCanonicalPath() + File.separator
                            + entry.getName()).mkdirs();
                    continue;
                }

                // 由于ZipFile读取文件是随机的,这就可能先读取某文件,而这个文件所在的目录还不存在,
                // 所以要创建该文件的父目录.
                File file = new File(outFile.getCanonicalPath()
                        + File.separator + entry.getName());
                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }

                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos, BUFFER_SIZE);

                bis = new BufferedInputStream(zipFile.getInputStream(entry));
                int count;
                byte data[] = new byte[BUFFER_SIZE];
                while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
                    bos.write(data, 0, count);
                }
                bos.flush();
                bos.close();
                fos.close();

                bis.close();

                fileList.add(file);
            }
            zipFile.close();

        } finally {

            if (bos != null) {
                bos.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (zipFile != null) {
                zipFile.close();
            }
        }
        return fileList;
    }

    /**
     * 解压缩, 解压后得到的释放的文件列表
     *
     * @param inFile 读入的需要解压的文件
     * @param dir    解压到的目录
     * @return 解压后得到的文件列表, 不含目录
     */
    public static List unzip(File inFile, String dir) throws IOException {
        final int BUFFER_SIZE = 4096;

        List fileList = new ArrayList();
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        ZipFile zipFile = null;
        try {

            zipFile = new ZipFile(inFile);
            Enumeration emu = zipFile.entries();
            while (emu.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) emu.nextElement();

                // 在此, 只是把目录作为一个file读出来, 其下的文件将在后面被迭代到
                if (entry.isDirectory()) {
                    new File(dir + File.separator
                            + entry.getName()).mkdirs();
                    continue;
                }

                // 由于ZipFile读取文件是随机的,这就可能先读取某文件,而这个文件所在的目录还不存在,
                // 所以要创建该文件的父目录.
                File file = new File(dir
                        + File.separator + entry.getName());
                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }

                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos, BUFFER_SIZE);

                bis = new BufferedInputStream(zipFile.getInputStream(entry));
                int count = 0;
                byte data[] = new byte[BUFFER_SIZE];
                while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
                    bos.write(data, 0, count);
                }
                bos.flush();
                bos.close();
                fos.close();

                bis.close();

                fileList.add(file);
            }
            zipFile.close();

        } finally {

            if (bos != null) {
                bos.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (zipFile != null) {
                zipFile.close();
            }
        }
        return fileList;
    }


    public static void main(String args[]) {
        File in = new File("d:/跟踪事项.xls");
        try {
            System.out.println(">>");
            File out = ZipUtil.zip(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
