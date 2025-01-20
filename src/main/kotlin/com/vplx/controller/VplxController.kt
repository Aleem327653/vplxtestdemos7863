package com.vplx.controller

import com.vplx.model.*
import com.vplx.utility.DomCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
@RequestMapping("vplx")
class VplxController {
    val xmlFile = File("D:\\MohdAleem\\VPlxDemo\\VPlxDemo\\VPlxDemo\\src\\main\\resources\\cyfair_cluster-1_vplx 1.xml")

    val initiator=DomCreator.fetchInitiatorFromXml(xmlFile)
    val initiators=Initiators(initiator)

    val port=DomCreator.fetchPortsFromXml(xmlFile)
    val ports=Ports(port)

    val extent=DomCreator.fetchExtentsFromXml(xmlFile)
    val extents=Extents(extent)

    val cluster=DomCreator.fetchClustersFromXml(xmlFile)

    val disk=DomCreator.fetchDisksFromXml(xmlFile)
    val disks=Disks(disk)

    val topLevelDevice =DomCreator.fetchTopLevelDevicesFromXml(xmlFile)
    val topLevelDevices=TopLevelDevices(topLevelDevice)

    @GetMapping("initiators")
    fun getAllInitiators():Initiators=initiators

    @GetMapping("ports")
    fun getAllPorts():Ports=ports

    @GetMapping("extents")
    fun getAllExtents():Extents=extents

    @GetMapping("cluster")
    fun getAllCluster():Cluster=cluster

    @GetMapping("disks")
    fun getAllDisks():Disks=disks

    @GetMapping("topLevelDevices")
    fun getAllTopLevelDevices():TopLevelDevices=topLevelDevices

}