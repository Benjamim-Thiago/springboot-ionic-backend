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
public enum PaymentStatus {
    PENDENTE(1, "Pending"),
    QUITADO(2, "settled"),
    CANCELADO(3, "canceled");

    private int code;
    private String description;

    private PaymentStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (PaymentStatus x : PaymentStatus.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código tipo de pagamento inválido: " + code);
    }
}
