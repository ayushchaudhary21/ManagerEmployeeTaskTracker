package com.ManagmentTask.Task.Exception;

public class DublicateTask extends RuntimeException{
    public DublicateTask(String message)
    {
        super(message);
    }
}
