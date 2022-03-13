package com.sofka.util;

import java.util.ArrayList;

/**
 * clase para manejar las respuestas en las peticiones
 * @version 1.0.0 2002-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

public class Response {
    public Boolean error = false;
    public String message = "";
    public String status = "OK";
    public Object data;
    public ArrayList<Object> dataGame = new ArrayList<>();
}
