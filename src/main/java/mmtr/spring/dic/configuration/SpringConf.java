package mmtr.spring.dic.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan("mmtr.spring.dic")
@PropertySource("classpath:dic.properties")
public class SpringConf {
}
