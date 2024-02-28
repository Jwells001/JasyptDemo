package com.example.Demo.Demo;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
@PropertySource(name = "EncryptedProperties", value = "classpath:encrypted.properties")
@EncryptablePropertySource(name = "EncryptedProperties2", value = "classpath:encrypted2.properties")
@EncryptablePropertySource(name = "EncryptedProperties3", value = "classpath:encrypted3.yml")
@Import(TestConfig.class)
@EnableEncryptableProperties
@EnableConfigurationProperties(ItemConfig.class)
@ImportResource("classpath:/testConfig.xml")
public class DemoApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    ApplicationContext appCtx;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(DemoApplication.class).run(args);
    }

    @Bean(name = "encryptorBean")
    static public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("password1234");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    @Override
    public void run(String... args) throws Exception {
        MyService service = appCtx.getBean(MyService.class);
        LOG.info("MyService's secret: {}", service.getSecret());
        ItemConfig itemConfig = appCtx.getBean(ItemConfig.class);
        LOG.info("ItemConfig: {}", itemConfig);
        Environment env = appCtx.getEnvironment();
        LOG.info("Secret from @EncryptablePropertySource annotation: {}", env.getProperty("secret2.property"));
        LOG.info("Secret from @EncryptablePropertySource annotation and YAML File: {}", env.getProperty("secret3.property"));
        SimpleBean simpleBean = appCtx.getBean(SimpleBean.class);
        LOG.info("XML Context SimpleBean value: {}", simpleBean.getValue());
        LOG.info("Done!");
    }
}
