package com.TMA.projectJava.service;

import com.hon.keycloak.entity.card_brand;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface card_brandService {
    List<card_brand> getAllCardBrand();
    card_brand saveCardBrand(card_brand cardBrand);

    Object getCardBrand(BigInteger cardBrandId);

    List<card_brand> getCardBrandNotDeleted();


    card_brand updateCardBrand(BigInteger cardBrandId, Map<String, String> formData);
}
//