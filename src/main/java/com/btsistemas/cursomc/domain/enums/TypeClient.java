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
    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

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

        throw new IllegalArgumentException("Código tipo pessoa inválido: " + code);
    }
}
