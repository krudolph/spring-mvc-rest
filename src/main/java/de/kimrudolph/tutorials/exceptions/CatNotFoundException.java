package de.kimrudolph.tutorials.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CatNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5968241099616074887L;

}
