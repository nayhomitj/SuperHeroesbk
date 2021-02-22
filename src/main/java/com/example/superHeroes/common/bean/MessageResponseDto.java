package com.example.superHeroes.common.bean;

import lombok.Data;

/**
 * instantiates a new message response dto.
 * @param <T>
 *
 */
@Data
public class MessageResponseDto<T> {
	
	private MessageResponseDto() {}
	/** the Success **/
	boolean success;
	
	/** the Error **/
	String error;
	
	/** the message **/
	T message;
	

/**
 * return fail
 * @param <T> 
 * @param message
 * @return
 */
	public static <T> MessageResponseDto<T> fail(String message) {
		MessageResponseDto<T> messageResponseDto = new MessageResponseDto<>();
		messageResponseDto.setSuccess(false);
		messageResponseDto.setError(message);
		messageResponseDto.setMessage(null);
		return messageResponseDto;
	}
	
	/**
	 * return success
	 * @param <T>
	 * @param content
	 * @return
	 */
	public static  <T> MessageResponseDto<T> success(MessageResponseDto<T> content) {
		MessageResponseDto<T> messageResponseDto = new MessageResponseDto<>();
		messageResponseDto.setSuccess(true);
		messageResponseDto.setError(null);
		messageResponseDto.setMessage(content.getMessage());
		return messageResponseDto;
		
	}
	
	/**
	 * return success
	 * @param <T>
	 * @param content
	 * @return
	 */
	public static  <T> MessageResponseDto<T> success(T content) {
		MessageResponseDto<T> messageResponseDto = new MessageResponseDto<>();
		messageResponseDto.setSuccess(true);
		messageResponseDto.setError(null);
		messageResponseDto.setMessage(content);
		return messageResponseDto;
		
	}
}
