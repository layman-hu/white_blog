package com.white.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDTO implements Serializable {
    private String roleName;
    private Integer roleCount;
}
