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
public enum TypeClient {
    PESSOA_FISICA(1, "Physical Person"),
    PESSOA_JURÍDICA(2, "Juridic Person");

    private int code;
    private String description;

    private TypeClient(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    

    public static TypeClient toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (TypeClient x : TypeClient.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código do tipo de pessoa inválido: " + code);
    }
}
