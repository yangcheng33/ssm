package org.sample.ssm.common.utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * TAR
 *
 * @author Yang Cheng
 * @version v0.1 2017-03-20 11:15
 */
public class TarUtil {
    private TarUtil() {
    }

    /**
     * Given a Tar File as input it will untar the file in a the untar directory
     * passed as the second parameter
     *
     * This utility will untar ".tar" files and ".tar.gz","tgz" files.
     *
     * @param inFile   The tar file as input.
     * @param untarDir The untar directory where to untar the tar file.
     */
    public static void unTar(File inFile, File untarDir) throws IOException {
        if (!untarDir.mkdirs()) {
            if (!untarDir.isDirectory()) {
                throw new IOException("Mkdirs failed to create " + untarDir);
            }
        }

        boolean gzipped = inFile.toString().endsWith("gz");
        if (Shell.WINDOWS) {
            // Tar is not native to Windows. Use simple Java based implementation for
            // tests and simple tar archives
            unTarUsingJava(inFile, untarDir, gzipped);
        } else {
            // spawn tar utility to untar archive for full fledged unix behavior such
            // as resolving symlinks in tar archives
            unTarUsingTar(inFile, untarDir, gzipped);
        }
    }

    private static void unTarUsingTar(File inFile, File untarDir, boolean gzipped) throws IOException {
        StringBuffer untarCommand = new StringBuffer();
        if (gzipped) {
            untarCommand.append(" gzip -dc '");
            untarCommand.append(makeShellPath(inFile));
            untarCommand.append("' | (");
        }
        untarCommand.append("cd '");
        untarCommand.append(makeShellPath(untarDir));
        untarCommand.append("' ; ");
        untarCommand.append("tar -xf ");

        if (gzipped) {
            untarCommand.append(" -)");
        } else {
            untarCommand.append(makeShellPath(inFile));
        }
        String[] shellCmd = { "bash", "-c", untarCommand.toString() };
        Shell.ShellCommandExecutor shexec = new Shell.ShellCommandExecutor(shellCmd);
        shexec.execute();
        int exitcode = shexec.getExitCode();
        if (exitcode != 0) {
            throw new IOException("Error untarring file " + inFile +
                ". Tar process exited with exit code " + exitcode);
        }
    }

    private static void unTarUsingJava(File inFile, File untarDir, boolean gzipped) throws IOException {
        InputStream inputStream = null;
        TarArchiveInputStream tis = null;
        try {
            if (gzipped) {
                inputStream = new BufferedInputStream(new GZIPInputStream(new FileInputStream(inFile)));
            } else {
                inputStream = new BufferedInputStream(new FileInputStream(inFile));
            }

            tis = new TarArchiveInputStream(inputStream);

            for (TarArchiveEntry entry = tis.getNextTarEntry(); entry != null; ) {
                unpackEntries(tis, entry, untarDir);
                entry = tis.getNextTarEntry();
            }
        } finally {
            cleanup(tis, inputStream);
        }
    }

    private static void cleanup(java.io.Closeable... closeables) {
        for (java.io.Closeable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void unpackEntries(TarArchiveInputStream tis, TarArchiveEntry entry, File outputDir)
        throws IOException {
        if (entry.isDirectory()) {
            File subDir = new File(outputDir, entry.getName());
            if (!subDir.mkdir() && !subDir.isDirectory()) {
                throw new IOException("Mkdirs failed to create tar internal dir " + outputDir);
            }

            for (TarArchiveEntry e : entry.getDirectoryEntries()) {
                unpackEntries(tis, e, subDir);
            }

            return;
        }

        File outputFile = new File(outputDir, entry.getName());
        if (!outputDir.exists()) {
            if (!outputDir.mkdirs()) {
                throw new IOException("Mkdirs failed to create tar internal dir " + outputDir);
            }
        }

        int count;
        byte data[] = new byte[2048];
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

        while ((count = tis.read(data)) != -1) {
            outputStream.write(data, 0, count);
        }

        outputStream.flush();
        outputStream.close();
    }

    /**
     * Convert a os-native filename to a path that works for the shell.
     *
     * @param filename The filename to convert
     * @return The unix pathname
     * @throws IOException on windows, there can be problems with the subprocess
     */
    public static String makeShellPath(String filename) throws IOException {
        return filename;
    }

    /**
     * Convert a os-native filename to a path that works for the shell.
     *
     * @param file The filename to convert
     * @return The unix pathname
     * @throws IOException on windows, there can be problems with the subprocess
     */
    public static String makeShellPath(File file) throws IOException {
        return makeShellPath(file, false);
    }

    /**
     * Convert a os-native filename to a path that works for the shell.
     *
     * @param file              The filename to convert
     * @param makeCanonicalPath Whether to make canonical path for the file passed
     * @return The unix pathname
     * @throws IOException on windows, there can be problems with the subprocess
     */
    public static String makeShellPath(File file, boolean makeCanonicalPath) throws IOException {
        if (makeCanonicalPath) {
            return makeShellPath(file.getCanonicalPath());
        } else {
            return makeShellPath(file.toString());
        }
    }

}
