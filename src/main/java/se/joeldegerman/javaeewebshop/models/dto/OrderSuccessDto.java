package se.joeldegerman.javaeewebshop.models.dto;

import lombok.Data;

@Data
public class OrderSuccessDto {
    private String successMessage;
    private String orderId;
}
