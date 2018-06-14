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
    PENDING(1, "Pendente"),
    SETTLED(2, "Quitado"),
    CANCELED(3, "Cancelado");

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
