package net.cn.yasir.framework.dto.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis代码生成器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/22
 */
public class MybatisCodeGenerator {
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }

    /** test **/
    public static void main(String[] args) {
        out("E:\\WorkSpace\\Java\\yasir-framework\\yasir-starter-entity\\src\\main\\java",
                "jdbc:mysql://111.229.208.239:3306/medical_mgt?useSSL=false&characterEncoding=utf-8",
                "medical_mgt",
                "q35[5o!&~f/#7?X",
                "net.cn.yasir.framework.entity",
                new String[] {"ys_user_sys"}
                );
    }

    /**
     * 输出
     * @param outputDir 输出目录
     * @param url MySQL url
     * @param username MySQL username
     * @param password MySQL password
     * @param packagePath 包名
     * @param includeTable 表名
     */
    public static void out(String outputDir,
                           String url,
                           String username,
                           String password,
                           String packagePath,
                           String[] includeTable
                           ) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setAuthor("yasir");
        gc.setFileOverride(true);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packagePath);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("net.cn.yasir.framework.entity.BaseEntity");
        strategy.setSuperControllerClass("net.cn.yasir.framework.dto.resp.BaseResp");
        strategy.setInclude(includeTable);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "\\base\\src\\main\\java\\");
//        gc.setAuthor("yasir");
//        gc.setFileOverride(true);
//        gc.setOpen(false);
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/bhm?autoReconnect=true&maxReconnects=3&failOverReadOnly=false&useUnicode=true&characterEncoding=utf8&useSSL=false");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("yasir");
//        dsc.setPassword("123456");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("generator");
//        pc.setParent("com.yasir.base");
//        pc.setEntity("entity");
//        mpg.setPackageInfo(pc);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setInclude(scanner("表名"));
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        mpg.setStrategy(strategy);
//        mpg.execute();
//    }

}
