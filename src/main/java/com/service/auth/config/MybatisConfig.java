package com.service.auth.config;

import com.service.auth.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 项目名称：
 * 包名称:conf
 * 类描述：
 * 创建人：何健
 * 修改人：何健
 *
 * @author hejian
 */
@Component
@PropertySource("classpath:application.yml")
public class MybatisConfig {

    private static Environment env = null;

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private static String typeAliasesPackage;

    private static final String TYPE_ALIASES_PACKAGE_BACKUP = "com.service.auth.entity";

    @Autowired
    public MybatisConfig(Environment env) {
        MybatisConfig.env = env;
    }

    /**
     * @return 从application.properties获取TypeAliasesPackage 需要被指定别名的类的包目录集合
     * note:并且检查配置的包目录是否合法
     */
    static String getTypeAliasesPackage() {
        if (StringUtil.isNullOrEmpty(typeAliasesPackage) && env != null) {
            typeAliasesPackage = checkTypeAliasesPackage(env
                    .getProperty("ibatis.typeAliasesPackage"));
        }
        return StringUtil.isNullOrEmpty(typeAliasesPackage) ? checkTypeAliasesPackage(TYPE_ALIASES_PACKAGE_BACKUP) : typeAliasesPackage;
    }

    private static String checkTypeAliasesPackage(String typeAliasesPackage) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
                resolver);
        typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(typeAliasesPackage)
                + "/" + DEFAULT_RESOURCE_PATTERN;
        try {
            List<String> result = new ArrayList<>();
            Resource[] resources = resolver.getResources(typeAliasesPackage);
            if (resources.length > 0) {
                MetadataReader metadataReader;
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        metadataReader = metadataReaderFactory
                                .getMetadataReader(resource);
                        try {
                            result.add(Class
                                    .forName(
                                            metadataReader.getClassMetadata()
                                                    .getClassName())
                                    .getPackage().getName());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (result.size() > 0) {
                HashSet<String> h = new HashSet<>(result);
                result.clear();
                result.addAll(h);
                typeAliasesPackage = String.join(",", (String[]) result.toArray(new String[0]));
                System.out.println(typeAliasesPackage);
            } else {
                throw new RuntimeException(
                        "mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:"
                                + typeAliasesPackage + "未找到任何包");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return typeAliasesPackage;
    }
}
