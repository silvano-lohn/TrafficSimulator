package br.com.trafficsimulator.commons.utils;

public class ResultMessage {

    private String message;
    private boolean ok = false;

    private ResultMessage(boolean ok, String message) {
	this.ok = ok;
	this.message = message;
    }

    private ResultMessage(boolean ok) {
	this.ok = ok;
    }

    public static ResultMessage OK(String message) {
	return new ResultMessage(true, message);
    }

    public static ResultMessage OK() {
	return new ResultMessage(true);
    }

    public static ResultMessage ERROR(String message) {
	return new ResultMessage(false, message);
    }

    public static ResultMessage ERROR() {
	return new ResultMessage(false);
    }

    public boolean isOk() {
	return ok;
    }

    public boolean existsMessage() {
	return message != null;
    }

    public String getMessage() {
	return message;
    }
}
