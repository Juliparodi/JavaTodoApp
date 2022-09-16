package com.juliparodi.rest.webservices.restfulwebservices.exception;

import com.juliparodi.rest.webservices.restfulwebservices.constants.constants;

public class ItemAlreadyExisted extends RuntimeException{

    public ItemAlreadyExisted(){
        super(constants.ITEM_EXISTED);
    }

}
