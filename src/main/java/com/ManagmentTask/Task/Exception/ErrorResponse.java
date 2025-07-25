package com.ManagmentTask.Task.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

public class ErrorResponse {
    private String message;
    private int value;
    private LocalDateTime errorTime;
  public   ErrorResponse(int value,String message,LocalDateTime errorTime)
    {
      this.value=value;
      this.message=message;
      this.errorTime=errorTime;
    }
}
