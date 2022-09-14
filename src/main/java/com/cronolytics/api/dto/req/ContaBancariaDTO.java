package com.cronolytics.api.dto.req;

import com.cronolytics.api.entity.ContaBancaria;

public class ContaBancariaDTO extends ContaBancaria {
    public ContaBancariaDTO(String numero, String digito, String agencia, String pix) {
        super(numero, digito, agencia, pix);
    }
}
