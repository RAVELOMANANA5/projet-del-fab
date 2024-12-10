package com.stage.exception;

import com.stage.dtos.ErrorResponseDTO;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@RequestMapping
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception exception) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                exception.getMessage(),
                exception.getCause(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param resourceNotFoundException
     * @return
     */
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponseDTO>> handleResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                resourceNotFoundException.getMessage(),
                resourceNotFoundException.getCause(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );

        Map<String, ErrorResponseDTO> errorResponseDTOMap = new HashMap<>();
        errorResponseDTOMap.put("error", errorResponseDTO);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponseDTOMap);
    }

    /**
     * @param resourceAlreadyExistsException
     * @return
     */
    @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponseDTO>> handleCustomerAlreadyExistsException(
            ResourceAlreadyExistsException resourceAlreadyExistsException
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                resourceAlreadyExistsException.getMessage(),
                resourceAlreadyExistsException.getCause(),
                HttpStatus.CONFLICT,
                LocalDateTime.now()
        );

        Map<String, ErrorResponseDTO> errorResponseDTOMap = new HashMap<>();
        errorResponseDTOMap.put("error", errorResponseDTO);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponseDTOMap);
    }
}
