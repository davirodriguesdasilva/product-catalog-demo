package br.com.catalog.product.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.catalog.product.exception.Error.ErrorBuilder;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorType errorType = ErrorType.INTERNAL_SERVER_ERROR;
        String detail = "An unexpected error occurred. Please try again.";

        Error error = createErrorBuilder(status, errorType, detail, null).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.INVALID_PARAMETER;
        String detail = "The request contains invalid data.";

        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        Error error = createErrorBuilder(HttpStatus.valueOf(status.value()), errorType, detail, errors).build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorType errorType = ErrorType.INVALID_PARAMETER;
        String detail = "The request contains invalid data.";

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        Error error = createErrorBuilder(HttpStatus.valueOf(status.value()), errorType, detail, errors).build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private ErrorBuilder createErrorBuilder(HttpStatus status, ErrorType errorType, String detail, List<String> errors) {
        return Error.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .title(errorType.getTitle())
                .detail(detail)
                .errors(errors);
    }
}
