package com.linktic.stock.infrastructure.postgres.exception;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String message) {super(message);}
}
