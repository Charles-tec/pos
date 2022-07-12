package com.server.pos.dtos;

import com.server.pos.enums.StatusEnum;
import lombok.Data;

@Data
public class PosDto {
    private Integer id;
    private String reference;
    private StatusEnum status;







}
