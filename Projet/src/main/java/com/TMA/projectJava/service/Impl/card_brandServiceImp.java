package com.alibou.keycloak.service.Impl;

import com.alibou.keycloak.Entity.card_brand;
import com.alibou.keycloak.repository.card_brandRepository;
import com.alibou.keycloak.service.card_brandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import java.util.Map;


@Service
public class card_brandServiceImp implements card_brandService {
    private final card_brandRepository cardBrandRepository;
    @Autowired
    public card_brandServiceImp(card_brandRepository cardBrandRepository) {
        this.cardBrandRepository = cardBrandRepository;
    }

    public List<card_brand> getAllCardBrand(){
        return cardBrandRepository
                .findAll();
    }

    @Override
    public card_brand saveCardBrand(card_brand cardBrand) {
        cardBrandRepository.save(cardBrand);
        return cardBrand;
    }

    @Override
    public Object getCardBrand(BigInteger cardBrandId) {
        return cardBrandRepository
                .findById(cardBrandId)
                .orElse(null);
    }

    @Override
    public void deleteCardBrand(BigInteger cardBrandId) {
        cardBrandRepository.deleteById(cardBrandId);
    }

    @Override
    public card_brand updateCardBrand(BigInteger cardBrandId, Map<String, String> formData) {
        card_brand existingCardBrand = cardBrandRepository.findById(cardBrandId).orElse(null);
        if (existingCardBrand != null) {
            String nameBrand = formData.get("name_brand");
            existingCardBrand.setName_brand(nameBrand);
            String status = formData.get("status");
            existingCardBrand.setStatus(status);
            return cardBrandRepository.save(existingCardBrand);
        }
        return null;
    }
}