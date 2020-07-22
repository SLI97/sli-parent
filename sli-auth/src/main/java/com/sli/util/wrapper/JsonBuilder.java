package com.sli.util.wrapper;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jared
 */
public class JsonBuilder {

	private JsonBuilder() {
	}

	public static <E> Json<E> build(int code, String message, E o) {
		return new Json<>(code, message, o);
	}

	public static <E> Json<E> build(int code, String message) {
		return build(code, message, null);
	}

	public static <E> Json<E> build(int code) {
		return build(code, null);
	}

	public static <E> Json<E> build(Exception e) {
		return new Json<>(Json.ERROR_CODE, e.getMessage());
	}

	public static <E> E data(Json<E> json) {
		return json.getData();
	}

	public static <E> Json<E> illegalArgument() {
		return build(Json.ILLEGAL_ARGUMENT_CODE_, Json.ILLEGAL_ARGUMENT_MESSAGE);
	}

	public static <E> Json<E> error() {
		return build(Json.ERROR_CODE, Json.ERROR_MESSAGE);
	}

	public static <E> Json<E> error(String message) {
		return build(Json.ERROR_CODE,
				StringUtils.isEmpty(message) ? Json.ERROR_MESSAGE : message);
	}

	public static <E> Json<E> error(String message, E o) {
		return build(Json.ERROR_CODE,
				StringUtils.isEmpty(message) ? Json.ERROR_MESSAGE : message, o);
	}

	public static <E> Json<E> ok() {
		return new Json<>();
	}

	public static <E> Json<E> ok(E o) {
		return new Json<>(Json.SUCCESS_CODE, Json.SUCCESS_MESSAGE, o);
	}
}
