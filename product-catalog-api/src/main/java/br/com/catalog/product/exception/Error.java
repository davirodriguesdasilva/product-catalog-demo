package br.com.catalog.product.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class Error {
    private final Integer status;
    private final OffsetDateTime timestamp;
    private final String title;
    private final String detail;
    private final List<String> errors;
}
