package com.cvc.broker.api.to;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description="Todos os detalhes dos Erros")
@Getter
@Setter
public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = -2301023905429985851L;

	@ApiModelProperty(notes="Status Code ", dataType="Integer")
	private Integer statusCode;

	@ApiModelProperty(notes="Status Message ", dataType="String")
    private String statusMessage;

	@ApiModelProperty(notes="Http Method ", dataType="String")
    private String httpMethod;

	@ApiModelProperty(notes="Description ", dataType="String")
    private String description;

	@ApiModelProperty(notes="detail", dataType="String")
    private String detail;

	@ApiModelProperty(notes="Path ", dataType="String")
    private String path;
    
	@ApiModelProperty(notes="Errors ", dataType="String")
    private List<String> errors;
	
	
	public static Builder builder() {
        return new Builder();
    }
	
    public static class Builder {

        private ErrorDetails erro;

        Builder() {
            this.erro = new ErrorDetails();
        }

        public Builder addStatus(HttpStatus status) {
            this.erro.statusCode = status.value();
            this.erro.statusMessage = status.getReasonPhrase();
            return this;
        }

        public Builder addHttpMethod(String method) {
            this.erro.httpMethod = method;
            return this;
        }

        public Builder addDescription(String description) {
            this.erro.description = description;
            return this;
        }

        public Builder addErros(List<String> errors) {
            this.erro.errors = errors;
            return this;
        }
        
        public Builder addDetail(String detail) {
            this.erro.detail = detail;
            return this;
        }

        public Builder addPath(String path) {
            this.erro.path = path;
            return this;
        }

        public ErrorDetails build() {
            return this.erro;
        }
    }
}
