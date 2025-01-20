package com.vplx.service

import com.vplx.model.Cluster
import com.vplx.repository.ClusterRepository
import com.vplx.utility.DomCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.io.File

@Service
class DumpConfigService {
    val xmlFile = File("D:\\MohdAleem\\VPlxDemo\\VPlxDemo\\VPlxDemo\\src\\main\\resources\\cyfair_cluster-1_vplx 1.xml")
    @Autowired
   lateinit var clusterRepository:ClusterRepository

   var cluster=DomCreator.fetchClustersFromXml(xmlFile)

    fun addAllCluster(cluster: Cluster):Cluster=clusterRepository.save(cluster)


}