package com.cvsDemo.demo.Service;

import com.cvsDemo.demo.Constants.ChatGPTConstants;
import com.cvsDemo.demo.DTO.DemoDTO;
import com.cvsDemo.demo.Repository.DemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoService.class);
    private final ChatGptService chatGptService;
    private final EtlService etlService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final DemoRepository demoRepository;

    @Autowired
    public DemoService(ChatGptService chatGptService, EtlService etlService, RedisTemplate<String, Object> redisTemplate, DemoRepository demoRepository) {
        this.chatGptService = chatGptService;
        this.etlService = etlService;
        this.redisTemplate = redisTemplate;
        this.demoRepository = demoRepository;
    }

    public DemoDTO getByName(String drug1, String drug2) {

        DemoDTO processedProduct = null;

        DemoDTO preCachedProduct = getProductFromCache(drug1);
        if (doesProductExist(preCachedProduct)) {
            return preCachedProduct;
        }

        DemoDTO persistedProduct = demoRepository.getOne(drug1);
        if (doesProductExist(persistedProduct)) {
            saveProductToCache(drug1, persistedProduct);
            return persistedProduct;
        }

        try {

            String chatGptInvokedResponse = chatGptService.generateResponse(drug1, drug2);
            processedProduct = etlService.processResponse(chatGptInvokedResponse);

            demoRepository.saveOne(processedProduct);
            saveProductToCache(drug1, processedProduct);
            return processedProduct;


        } catch (NullPointerException ex) {
            logger.error("Unable to derive value: Null value");

            return processedProduct;
        }
    }
        private boolean doesProductExist (DemoDTO demoDTO){
            if (demoDTO == null ||
                    (demoDTO.getDrug1() == null &&
                            demoDTO.getDrug2() == null &&
                            demoDTO.getCompatibility() == null &&
                            demoDTO.getDrug1SideEffects() == null &&
                            demoDTO.getDrug2SideEffects() == null &&
                            demoDTO.getDrug1UseCase() == null &&
                            demoDTO.getDrug2UseCase() == null)) {
                return false;
            }

            return true;
        }


        public void saveProductToCache (String key, Object value){
            redisTemplate.opsForValue().set(key, value);
        }

        public DemoDTO getProductFromCache (String key){
            return (DemoDTO) redisTemplate.opsForValue().get(key);
        }
    }

