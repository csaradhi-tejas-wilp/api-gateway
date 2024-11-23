package com.bits.pilani.apigateway.config;

import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("userServiceInstanceListSupplier")
    public ServiceInstanceListSupplier userServiceInstanceListSupplier() {
        return new CustomServiceInstanceListSupplier("user-service", List.of(
            new DefaultServiceInstance("user-service-1", "user-service", "localhost", 8081, false),
            new DefaultServiceInstance("user-service-2", "user-service", "localhost", 8085, false)
        ));
    }

    @Bean
    @Qualifier("restaurantServiceInstanceListSupplier")
    public ServiceInstanceListSupplier restaurantServiceInstanceListSupplier() {
        return new CustomServiceInstanceListSupplier("restaurant-service", List.of(
            new DefaultServiceInstance("restaurant-service-1", "restaurant-service", "localhost", 8082, false),
            new DefaultServiceInstance("restaurant-service-2", "restaurant-service", "localhost", 8086, false)
        ));
    }

    @Bean
    @Qualifier("orderServiceInstanceListSupplier")
    public ServiceInstanceListSupplier orderServiceInstanceListSupplier() {
        return new CustomServiceInstanceListSupplier("order-service", List.of(
            new DefaultServiceInstance("order-service-1", "order-service", "localhost", 8083, false),
            new DefaultServiceInstance("order-service-2", "order-service", "localhost", 8087, false)
        ));
    }

    @Bean
    @Qualifier("deliveryServiceInstanceListSupplier")
    public ServiceInstanceListSupplier deliveryServiceInstanceListSupplier() {
        return new CustomServiceInstanceListSupplier("delivery-service", List.of(
            new DefaultServiceInstance("delivery-service-1", "delivery-service", "localhost", 8090, false),
            new DefaultServiceInstance("delivery-service-2", "delivery-service", "localhost", 8091, false)
        ));
    }

    static class CustomServiceInstanceListSupplier implements ServiceInstanceListSupplier {
        private final String serviceId;
        private final List<ServiceInstance> instances;

        public CustomServiceInstanceListSupplier(String serviceId, List<ServiceInstance> instances) {
            this.serviceId = serviceId;
            this.instances = instances;
        }

        @Override
        public String getServiceId() {
            return serviceId;
        }

        @Override
        public Flux<List<ServiceInstance>> get() {
            return Flux.just(instances);
        }
    }
}

