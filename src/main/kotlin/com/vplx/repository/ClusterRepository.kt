package com.vplx.repository

import com.vplx.model.Cluster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClusterRepository :JpaRepository<Cluster,String>{
}