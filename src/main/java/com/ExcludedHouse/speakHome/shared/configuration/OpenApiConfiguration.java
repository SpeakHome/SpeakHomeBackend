package com.ExcludedHouse.speakHome.shared.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
  public OpenAPI customOpenApi(String applicationDescription, String applicationVersion) {
    return new OpenAPI()
      .info(
        new Info()
          .title("Examen PC2")
          .version(applicationVersion)
          .description(applicationDescription)
          .termsOfService("https://PC2-landing.netlify.app/terms-of-use/")//https://radr-landing.netlify.app/terms-of-use/
          .license(
            new License()
              .name("Apache 2.0 License")
              .url("https://PC2-landing.netlify.app/license/")//https://readr-landing.netlify.app/license/
          )
          .contact(
            new Contact()
              .url("https://PC2.com")//https://monaschinas.com
              .name("PC2")
          )
      );
  }
}
