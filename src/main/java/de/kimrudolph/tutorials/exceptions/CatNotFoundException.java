package de.kimrudolph.tutorials.exceptions;

public class CatNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5968241099616074887L;

    public CatNotFoundException(final String name) {

        super(name);
    }
}
