package com.mlg.gbadmin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@Configuration
class ThymeleafConfig implements WebMvcConfigurer {

    @Bean
    ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver()
        resolver.setPrefix("templates/")
        resolver.setCacheable(false)
        resolver.setSuffix(".html")
        resolver.setTemplateMode("HTML")
        resolver.setCharacterEncoding("UTF-8")
        resolver
    }

    @Bean
    SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine()
        engine.setTemplateResolver(templateResolver())
        engine
    }
}
