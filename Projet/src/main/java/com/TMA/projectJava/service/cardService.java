package com.alibou.keycloak.service;



import com.alibou.keycloak.Entity.card;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Service
public interface cardService {
    List<card> getAllCard();
    card saveCard(card card);

    Object getCard(BigInteger cardId);

    void deleteCard(BigInteger cardId);

    card updateCard(BigInteger cardId, Map<String, String> formData);
}
