package com.vplx.utility

import com.vplx.model.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory


object DomCreator {


    // Helper function to parse XML file into Document object
    fun parseXmlFile(file: File): Document {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        return builder.parse(file)
    }

    //Clusters
    fun fetchClustersFromXml(file: File): Cluster {
        val document = parseXmlFile(file)
        val clusterNodeList = document.getElementsByTagName("cluster")


            val clusterElement = clusterNodeList.item(0) as Element

            val managementIP=clusterElement.getAttribute("managementIP")
            val version=clusterElement.getAttribute("version")

            val name = clusterElement.getAttribute("name")
            val topLevelAssembly = clusterElement.getAttribute( "top-level-assembly")
            val id = clusterElement.getAttribute( "id")
            // Fetch all PlexControlDirector elements
            val plexControlDirectorNodeList = clusterElement.getElementsByTagName("PlexControlDirector")
           val plexControlDirectors = mutableListOf<PlexControlDirector>()

        for (i in 0 until plexControlDirectorNodeList.length) {
            val plexControlDirectorElement = plexControlDirectorNodeList.item(i) as Element
            val pname = plexControlDirectorElement.getAttribute("name")
            val type = plexControlDirectorElement.getAttribute("type")
            val hostname = plexControlDirectorElement.getAttribute("hostname")
            val pid = plexControlDirectorElement.getAttribute("id")

            // Add each PlexControlDirector to the list
            plexControlDirectors.add(PlexControlDirector(pname, type, hostname, pid))
        }

        // Create and return the Cluster object
        return Cluster(managementIP, version, name, topLevelAssembly, id, plexControlDirectors)
    }


    // Function to fetch disks data from XML
    fun fetchDisksFromXml(file: File): List<Disk> {
        val document = parseXmlFile(file)
        val disksNodeList = document.getElementsByTagName("disk")

        val disks = mutableListOf<Disk>()

        // Iterate through all <disk> elements
        for (i in 0 until disksNodeList.length) {
            val diskElement = disksNodeList.item(i) as Element

            // Extract attributes from the <disk> element
            val name = diskElement.getAttribute("name")
            val vpdIdentifier = diskElement.getAttribute("vpdIdentifier")
            val thinCapable = diskElement.getAttribute("thin-capable").toBoolean()
            val size = diskElement.getAttribute("size").toLong()
            val id = diskElement.getAttribute("id")
            val use = diskElement.getAttribute("use")

            // Fetch all <path> elements under <paths> for the current <disk>
            val pathsNodeList = diskElement.getElementsByTagName("path")
            val paths = mutableListOf<Path>()

            // Iterate through all <path> elements
            for (j in 0 until pathsNodeList.length) {
                val pathElement = pathsNodeList.item(j) as Element
                val target = pathElement.getAttribute("target")
                val lun = pathElement.getAttribute("LUN")
                val type = pathElement.getAttribute("type")

                // Fetch all <pathPort> elements under <path> for the current <path>
                val pathPortNodeList = pathElement.getElementsByTagName("pathPort")
                val pathPorts = mutableListOf<PathPort>()

                // Iterate through all <pathPort> elements
                for (k in 0 until pathPortNodeList.length) {
                    val pathPortElement = pathPortNodeList.item(k) as Element
                    val pathPortId = pathPortElement.getAttribute("id")
                    pathPorts.add(PathPort(pathPortId))
                }

                // Add the path to the list of paths
                paths.add(Path(target, lun, type, pathPorts))
            }

            // Add the disk to the list of disks
            disks.add(Disk(name, vpdIdentifier, thinCapable, size, id, use, paths))
        }

        return disks
    }


    // Function to fetch top-level devices data from XML
    fun fetchTopLevelDevicesFromXml(file: File): List<TopLevelDevice> {
        val document = parseXmlFile(file)
        val deviceNodeList = document.getElementsByTagName("device")

        val devices = mutableListOf<TopLevelDevice>()

        // Iterate through all <device> elements
        for (i in 0 until deviceNodeList.length) {
            val deviceElement = deviceNodeList.item(i) as Element

            // Extract attributes from the <device> element
            val serviceStatus = deviceElement.getAttribute("service-status")
            val healthState = deviceElement.getAttribute("health-state")
            val blockSize = deviceElement.getAttribute("block-size")
            val operationalStatus = deviceElement.getAttribute("operational-status")
            val name = deviceElement.getAttribute("name")
            val applicationConsistent = deviceElement.getAttribute("application-consistent").toBoolean()
            val type = deviceElement.getAttribute("type")
            val size = deviceElement.getAttribute("size")
            val locality = deviceElement.getAttribute("locality")
            val stripeDepth = deviceElement.getAttribute("stripe-depth")
            val id = deviceElement.getAttribute("id")

            // Fetch all <components> under the <device> element
            val componentsNodeList = deviceElement.getElementsByTagName("components")
            val components = mutableListOf<Components>()

            // Iterate through all <components> elements under <device>
            for (j in 0 until componentsNodeList.length) {
                val componentElement = componentsNodeList.item(j) as Element
                val extentNodeList = componentElement.getElementsByTagName("extent")
                val extents = mutableListOf<Extentt>()

                // Iterate through all <extent> elements under <components>
                for (k in 0 until extentNodeList.length) {
                    val extentElement = extentNodeList.item(k) as Element
                    val enclosingCluster = extentElement.getAttribute("enclosingCluster")
                    val slotNumber = extentElement.getAttribute("slot-number")
                    val extentName = extentElement.getAttribute("name")
                    val applicationConsistentExtent = extentElement.getAttribute("application-consistent").toBoolean()
                    val blockOffset = extentElement.getAttribute("block-offset")
                    val extentSize = extentElement.getAttribute("size")
                    val extentLocality = extentElement.getAttribute("locality")

                    // Fetch all <storage-volume> elements under <extent>
                    val storageVolumeNodeList = extentElement.getElementsByTagName("storage-volume")
                    val storageVolumes = mutableListOf<StorageVolume>()

                    // Iterate through all <storage-volume> elements under <extent>
                    for (l in 0 until storageVolumeNodeList.length) {
                        val storageVolumeElement = storageVolumeNodeList.item(l) as Element
                        val storageEnclosingCluster = storageVolumeElement.getAttribute("enclosingCluster")
                        val storageVolumeName = storageVolumeElement.getAttribute("name")
                        val storageVolumeSize = storageVolumeElement.getAttribute("size")
                        val storageVolumeLocality = storageVolumeElement.getAttribute("locality")
                        val storageVolumeId = storageVolumeElement.getAttribute("id")

                        // Add storage volume to the list
                        storageVolumes.add(StorageVolume(storageEnclosingCluster, storageVolumeName, storageVolumeSize, storageVolumeLocality, storageVolumeId))
                    }

                    // Add extent to the list
                    extents.add(Extentt(enclosingCluster, slotNumber, extentName, applicationConsistentExtent, blockOffset, extentSize, extentLocality, storageVolumes))
                }

                // Add component to the list
                components.add(Components(extents))
            }

            // Add device to the list
            devices.add(TopLevelDevice(serviceStatus, healthState, blockSize, operationalStatus, name, applicationConsistent, type, size, locality, stripeDepth, id, components))
        }

        return devices
    }

    //Initiators
    fun fetchInitiatorFromXml(file: File): List<Initiator> {
        val document = parseXmlFile(file)
        val initiatorNodeList = document.getElementsByTagName("initiator")

        val initiator = mutableListOf<Initiator>()

        for (i in 0 until initiatorNodeList.length) {
            val initiatorElement = initiatorNodeList.item(i) as Element


            val id=initiatorElement.getAttribute("id")
            val name=initiatorElement.getAttribute("name")

            val type = initiatorElement.getAttribute("type")
            val portWWN = initiatorElement.getAttribute( "portWWN")
            val nodeWWN = initiatorElement.getAttribute( "nodeWWN")


            val data = Initiator(name,type,portWWN,nodeWWN,id)
            initiator.add(data)
        }

        return initiator
    }


    //Ports
    fun fetchPortsFromXml(file: File): List<Port> {
        val document = parseXmlFile(file)
        val portNodeList = document.getElementsByTagName("port")

        val port = mutableListOf<Port>()

        for (i in 0 until portNodeList.length) {
            val portElement = portNodeList.item(i) as Element


            val name=portElement.getAttribute("name")
            val enabled=portElement.getAttribute("enabled")
            val status = portElement.getAttribute( "status")
            val portWWN = portElement.getAttribute( "portWWN")
            val role = portElement.getAttribute( "role")
            val protocols = portElement.getAttribute( "protocol")
            val nodeWWN = portElement.getAttribute( "nodeWWN")

            val data = Port(name,enabled,status,portWWN,role,protocols,nodeWWN)
            port.add(data)
        }

        return port
    }

    //Extents
    fun fetchExtentsFromXml(file: File): List<Extent> {
        val document = parseXmlFile(file)
        val extentNodeList = document.getElementsByTagName("extent")

        val extent = mutableListOf<Extent>()

        for (i in 0 until extentNodeList.length) {

            val extentElement = extentNodeList.item(i) as Element


            val healthState=extentElement.getAttribute("health-state")
            val ioStatus=extentElement.getAttribute("io-status")
            val operationalStatus = extentElement.getAttribute( "operational-status")
            val name = extentElement.getAttribute( "name")
            val applicationConsistent = extentElement.getAttribute( "application-consistent")
            val systemId = extentElement.getAttribute( "system-id")
            val storageVolume = extentElement.getAttribute( "storage-volume")
            val healthIndications = extentElement.getAttribute( "health-indications")
            val itls = extentElement.getAttribute( "itls")
            val size = extentElement.getAttribute( "size")
            val usedBy = extentElement.getAttribute( "used-by")
            val locality = extentElement.getAttribute( "locality")
            val storageVolumeType = extentElement.getAttribute( "storage-volumetype")
            val use = extentElement.getAttribute( "use")

            val data = Extent(healthState,ioStatus,operationalStatus,name, applicationConsistent,systemId,storageVolume,healthIndications,itls,size,usedBy,locality,storageVolumeType,use)
            extent.add(data)
        }

        return extent
    }





}



fun main() {
    // Path to the XML file
    val xmlFile = File("D:\\MohdAleem\\VPlxDemo\\VPlxDemo\\VPlxDemo\\src\\main\\resources\\cyfair_cluster-1_vplx 1.xml")

    // Fetch the list of employees from the XML file
    val initiators = DomCreator.fetchInitiatorFromXml(xmlFile)
    Initiators(initiators)

    val ports=DomCreator.fetchPortsFromXml(xmlFile)
    Ports(ports)
//    ports.forEach {
//        println("Name: ${it.name}")
//        println("Enabled: ${it.enabled}")
//        println("status: ${it.status}")
//        println("portWWN: ${it.portWWN}")
//        println("role: ${it.role}")
//        println("protocols: ${it.protocols}")
//        println("nodeWWN: ${it.nodeWWN}")
//
//
//        println("---------------------------------------------------------------------------------------------")
//
//    }

    // Print the fetched employees' details
    println("Name\t\t\t\tType\t\t\tPort WWN\t\t\t\tNode WWN\t\t\t\tID")
    initiators.forEach {

        println("${it.name}\t\t${it.type}\t\t${it.portWWN}\t\t${it.nodeWWN}\t\t${it.id}")
        // println(" ID: ${it.id}")
        // println("Name: ${it.name}")
        // println("Type: ${it.type}")
        // println("portWWN: ${it.portWWN}")
        // println("nodeWWN: ${it.nodeWWN}")

        println("---------------------------------------------------------------------------------------------")
    }
}

