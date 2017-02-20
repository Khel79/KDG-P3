package be.kdg.prog3.form.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Setting the basenames and cacheseconds in application.properties doesn't seem to be working.
 * The messages referenced in the JSP files were replaced just fine, however the attribute annotation
 * messages (@Size, @Email, ...) were not replaced. Adding this class solved the issue...
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/messages");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

    @Override
    @Bean
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }
}
