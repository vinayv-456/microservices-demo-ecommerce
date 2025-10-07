package com.microservices_demo.service;

import com.microservices_demo.dto.InventoryResponse;
import com.microservices_demo.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

        private final InventoryRepository inventoryRepository;

        @Transactional(readOnly = true)
        public List<InventoryResponse> isInStock(List<String> skuCode) {
                // // Simulating Timeout
                // log.info("Wait Started");
                // try {
                // Thread.sleep(10000);
                // } catch (InterruptedException e) {
                // log.info("Exception from Thread: ", e);
                // }
                // log.info("Wait Ended");

                return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                                .map(inventory -> InventoryResponse.builder()
                                                .skuCode(inventory.getSkuCode())
                                                .isInStock(inventory.getQuantity() > 0)
                                                .build())
                                .toList();
        }
}
