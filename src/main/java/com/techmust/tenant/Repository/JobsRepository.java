package com.techmust.tenant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmust.tenant.model.Job;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long>
{
   
}