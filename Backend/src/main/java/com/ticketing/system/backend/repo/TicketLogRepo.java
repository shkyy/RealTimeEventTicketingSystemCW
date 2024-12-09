package com.ticketing.system.backend.repo;


import com.ticketing.system.backend.db_model.TicketTransactionLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketLogRepo extends JpaRepository<TicketTransactionLogModel, Long> {
}
