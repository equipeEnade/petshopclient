package com.catusoft.petshopclient.infra.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity, Long> {
}
