package co.sen.bankingapi.exception;

import co.sen.bankingapi.base.BaseError;
import co.sen.bankingapi.base.BaseRest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handleServiceException(ResponseStatusException e){
        return BaseError.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .message("Something went wrong..!, Please check...!")
                .errors(e.getReason())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException e){

        // or we can use to replace Map in list by use var
        var errors = new ArrayList<>();

//        List<Map<String, String>> errors = new ArrayList<>();
//        Map<String,String> errorDetails = new HashMap<>();

        for (FieldError error : e.getFieldErrors()) {
            var errorDetails = new HashMap<>();

            errorDetails.put("name" , error.getField());
            errorDetails.put("message" , error.getDefaultMessage());
            errors.add(errorDetails);

        }
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message("Validation is error, please check detail message!!")
                .errors(errors)
                .build();
    }
}
