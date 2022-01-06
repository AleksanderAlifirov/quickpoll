package ru.cft.quickpoll.error;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ErrorDetail {

    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;

    private Map<String, List<ValidationError>> errors = new HashMap<>();

}
