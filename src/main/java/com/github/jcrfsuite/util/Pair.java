package com.github.jcrfsuite.util;

public class Pair<T1, T2> {
	public T1 first;
	public T2 second;
	public Pair(T1 x, T2 y) { first=x; second=y; }

	public T1 getFirst(){
		return first;
	}

	public T2 getSecond(){
		return second;
	}

	@Override
	public String toString() {
		return String.format("{%s, %s}", first, second);
	}
}