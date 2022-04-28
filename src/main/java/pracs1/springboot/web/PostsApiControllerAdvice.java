package pracs1.springboot.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class PostsApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors.values().toString());

    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public  ResponseEntity<ErrorResult> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        ErrorResult errorResult = new ErrorResult("BAD", ex.getMessage());
//        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
//    }

}