package com.bridgelabz.fundoo.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.fundoo.exception.RecordNotFoundException;
import com.bridgelabz.fundoo.model.ErrorModel;
import com.bridgelabz.fundoo.response.Response;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler{

	/**
	 * Purpose : Record not found exception.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorModel> handleRecordNotFound(RecordNotFoundException exception ,WebRequest request) 
	{
		ErrorModel error=new ErrorModel(new Date(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ErrorModel> handleException(RecordNotFoundException exception,WebRequest request)
	{
		ErrorModel error=new ErrorModel(new Date(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
