package com.TMA.projectJava.service;

import com.hon.keycloak.entity.card;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface cardService {
    List<card> getAllCard();
    card saveCard(card card);

    Object getCard(BigInteger cardId);

    List<card> getCardNotDeleted();


    card updateCard(BigInteger cardId, Map<String, String> formData);
}
