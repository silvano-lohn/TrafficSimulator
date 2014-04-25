package br.com.trafficsimulator.commons.utils;

public class Maybe<E> {

	private E e;

	protected Maybe(E e) {
		this.e = e;
	}

	private Maybe() {

	}

	public boolean hasElement() {
		return false;
	}

	public E getElement() {
		return e;
	}

	public static <E> Maybe<E> just(E e) {
		return new Just<E>(e);
	}

	public static <E> Maybe<E> nothing() {
		return new Maybe<E>();
	}
}

class Just<E> extends Maybe<E> {

	public Just(E e) {
		super(e);
	}

	public boolean hasElement() {
		return true;
	}

}
