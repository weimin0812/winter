package com.free.winter.core.io.suuport;

import com.free.winter.core.io.FileSystemResource;
import com.free.winter.core.io.Resource;
import com.free.winter.util.Assert;
import com.free.winter.util.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author weimin02
 * @date 2018/8/17
 * @project winter
 */
public class PackageResourceLoader {
    private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);

    private final ClassLoader classLoader;

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, "ResourceLoader must not be null");
        this.classLoader = classLoader;
    }

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }


    public Resource[] getResources(String basePackage) {
        Assert.notNull(basePackage, "basePackage must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(location);
        File rootDir = new File(url.getFile());

        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] resources = new Resource[matchingFiles.size()];
        int i = 0;
        for (File file : matchingFiles) {
            resources[i++] = new FileSystemResource(file);
        }

        return resources;
    }

    private Set<File> retrieveMatchingFiles(File rootDir) {
        // Silently skip non-existing directories
        if (!rootDir.exists()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it doesn't exists");
            }

            return Collections.emptySet();
        }

        // Complain louder if it exists but is not directory
        if (!rootDir.isDirectory()) {
            // Complain louder if it exists but is no directory.
            if (logger.isWarnEnabled()) {
                logger.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
            }
            return Collections.emptySet();
        }

        if (!rootDir.canRead()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matchding files underneath directory [" + rootDir.getAbsolutePath() +
                        "] because the application is not allowed to read the directory");
            }
            return Collections.emptySet();
        }

        Set<File> result = new LinkedHashSet<>(8);
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    private void doRetrieveMatchingFiles(File rootDir, Set<File> result) {
        File[] dirContents = rootDir.listFiles();
        if (dirContents == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("Could not retrieve contents of directory [" + rootDir.getAbsolutePath());
            }
            return;
        }

        for (File content : dirContents) {
            if (content.isDirectory()) {
                if (!content.canRead()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipping subdirectory [" + rootDir.getAbsolutePath() +
                                "] because the application is not allowed to read the directory");
                    }
                } else {
                    doRetrieveMatchingFiles(rootDir, result);
                }
            } else {
                result.add(content);
            }

        }


    }
}
