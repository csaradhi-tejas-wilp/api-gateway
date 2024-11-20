package com.bits.pilani.apigateway.config;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.List;

@Configuration
public class LoadBalancerConfig {

    @Bean
    public ServiceInstanceListSupplier userServiceInstanceListSupplier() {
        return new CustomServiceInstanceListSupplier("user-service");
    }

    static class CustomServiceInstanceListSupplier implements ServiceInstanceListSupplier {

        private final String serviceId;

        public CustomServiceInstanceListSupplier(String serviceId) {
            this.serviceId = serviceId;
        }

        @Override
        public String getServiceId() {
            return serviceId;
        }

        @Override
        public Flux<List<ServiceInstance>> get() {
            return Flux.just(List.of(
                new DefaultServiceInstance("user-service-1", serviceId, "localhost", 8081, false),
                new DefaultServiceInstance("user-service-2", serviceId, "localhost", 8085, false),
                new DefaultServiceInstance("restaurant-service-1", serviceId, "localhost", 8082, false),
                new DefaultServiceInstance("restaurant-service-2", serviceId, "localhost", 8086, false),
                new DefaultServiceInstance("order-service-1", serviceId, "localhost", 8083, false),
                new DefaultServiceInstance("order-service-2", serviceId, "localhost", 8087, false),
                new DefaultServiceInstance("delivery-service-1", serviceId, "localhost", 8084, false),
                new DefaultServiceInstance("delivery-service-2", serviceId, "localhost", 8088, false)
            ));
        }
    }
}

