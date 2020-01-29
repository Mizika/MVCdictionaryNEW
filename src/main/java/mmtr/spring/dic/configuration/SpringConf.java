package mmtr.spring.dic.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan("mmtr.spring.dic")
@PropertySource("classpath:dic.properties")
public class SpringConf {
//    @Bean
//    public DataSource datasource() {
//        return DataSourceBuilder.create()
//                .driverClassName("org.postgresql.Driver")
//                .url("jdbc:postgresql://localhost:5432/postgres")
//                .username("postgres")
//                .password("12345")
//                .build();
//    }
}
