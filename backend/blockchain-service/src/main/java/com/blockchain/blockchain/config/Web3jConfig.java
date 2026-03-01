package com.blockchain.blockchain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Web3jConfig {
    
    @Value("${blockchain.ethereum.mainnet-rpc}")
    private String ethMainnetRpc;
    
    @Value("${blockchain.ethereum.sepolia-rpc}")
    private String ethSepoliaRpc;
    
    @Value("${blockchain.polygon.mainnet-rpc}")
    private String polygonMainnetRpc;
    
    @Value("${blockchain.polygon.mumbai-rpc}")
    private String polygonMumbaiRpc;
    
    @Bean
    public Map<String, Web3j> web3jClients() {
        Map<String, Web3j> clients = new HashMap<>();
        clients.put("ETHEREUM_MAINNET", Web3j.build(new HttpService(ethMainnetRpc)));
        clients.put("ETHEREUM_SEPOLIA", Web3j.build(new HttpService(ethSepoliaRpc)));
        clients.put("POLYGON_MAINNET", Web3j.build(new HttpService(polygonMainnetRpc)));
        clients.put("POLYGON_MUMBAI", Web3j.build(new HttpService(polygonMumbaiRpc)));
        return clients;
    }
    
    @Bean
    public Map<String, Long> chainIds() {
        Map<String, Long> chainIds = new HashMap<>();
        chainIds.put("ETHEREUM_MAINNET", 1L);
        chainIds.put("ETHEREUM_SEPOLIA", 11155111L);
        chainIds.put("POLYGON_MAINNET", 137L);
        chainIds.put("POLYGON_MUMBAI", 80001L);
        return chainIds;
    }
}
