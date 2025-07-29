package com.ManagmentTask.Task.Exception;

public class MultiTaskAssigned extends  RuntimeException{
   public MultiTaskAssigned(String message)
    {
        super(message);
    }
}
