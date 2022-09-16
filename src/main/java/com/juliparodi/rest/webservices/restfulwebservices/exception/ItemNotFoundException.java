package com.juliparodi.rest.webservices.restfulwebservices.exception;


import com.juliparodi.rest.webservices.restfulwebservices.constants.constants;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(){
        super(constants.ITEM_NOT_FOUND);
    }

}
