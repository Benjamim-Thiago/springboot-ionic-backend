/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btsistemas.cursomc.domain.enums;

/**
 *
 * @author ben
 */
public enum Profile {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENTE");

    private int code;
    private String description;

    private Profile(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    

    public static Profile toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (Profile x : Profile.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código do tipo de perfil inválido: " + code);
    }
}
