package com.cvc.broker.api.config;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "CVC Broker",
                version = "V0.0.1",
                title = "CVC Broker API",
                termsOfService = "http://swagger.io/terms/",
                contact = @Contact(
                   name = "Daniel Pavan Bassetto", 
                   email = "danielpavan@globo.com", 
                   url = "https://github.com/danielpavan82"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "https://github.com/danielpavan82")
)
public interface ApiDocumentationConfig {

}
