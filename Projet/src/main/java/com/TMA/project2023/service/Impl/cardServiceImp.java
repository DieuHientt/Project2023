package com.alibou.keycloak.service.Impl;
import com.hon.keycloak.model.card;
import com.hon.keycloak.repository.cardRepository;
import com.hon.keycloak.service.cardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Service
public class cardServiceImp implements cardService {
    private final cardRepository cardRepository;
    @Autowired
    public cardServiceImp(com.hon.keycloak.repository.cardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    public List<card> getAllCard(){
        return cardRepository.findAll();
    }

    @Override
    public card saveCard(card card) {
        cardRepository.save(card);
        return card;
    }

    @Override
    public Object getCard(BigInteger cardId) {
        return cardRepository
                .findById(cardId)
                .orElse(null);
    }

    @Override
    public void deleteCard(BigInteger cardId) {
        cardRepository.deleteById(cardId);
    }
    @Override
    public card updateCard(BigInteger cardId, Map<String, String> formData) {
        card existingCard = cardRepository.findById(cardId).orElse(null);
        if (existingCard != null) {  //Kiểm tra đối tượng có tồn tại
            String amount = formData.get("amount");
            String cardNumber = formData.get("card_number");
            String symbol = formData.get("symbol");
            existingCard.setAmount(Integer.parseInt(amount));
            existingCard.setCard_number(cardNumber);
            existingCard.setSymbol(symbol);
            return cardRepository.save(existingCard);
        }
        return null;
    }
}
//