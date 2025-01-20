package com.vplx.model




data class ConfigDump(
    val cluster: Cluster,
    val ports: Ports,
    val disks: Disks,
    val topLevelDevices: TopLevelDevices,
    val extents: Extents,
    val virtualVolumes: VirtualVolumes,
    val initiators: Initiators,
    val views: Views,
    val systemVolumes: SystemVolumes
)


data class Cluster(
    val managementIP: String,
    val version: String,
    val name: String,
    val topLevelAssembly: String,
    val id: String,
    val plexControlDirector: List<PlexControlDirector>
)

data class PlexControlDirector(
    val name: String,
    val type: String,
    val hostname: String,
    val id: String
)


data class Ports(
    val port: List<Port>
)


data class Port(
    val name: String,
    val enabled: String,
    val status: String,
    val portWWN: String,
    val role: String,
    val protocols: String,
    val nodeWWN: String,

)

data class Port1(
    val name: String,
    val maxSpeed: String,
    val enabled: String,
    val subnet: String,
    val operationalStatus: String,
    val portStatus: String,
    val configStatus: String,
    val macAddress: String,
    val optionSet: String,
    val speed: String,
    val role: String,
    val protocols: String,
    val mtu: String,
    val address: String
)


data class Disks(
    val disk: List<Disk>
)


data class Disk(
    val name: String,
    val vpdIdentifier: String,
    val thinCapable: Boolean,
    val size: Long,
    val id: String,
    val use: String,
    val paths: List<Path>
)


data class Paths(
    val path: List<Path>
)


data class Path(
    val target: String,
    val lun: String,
    val type: String,
    val pathPort: List<PathPort>
)


data class PathPort(
    val id: String
)


data class TopLevelDevices(
    val device: List<TopLevelDevice>
)


data class TopLevelDevice(
    val serviceStatus: String,
    val healthState: String,
    val blockSize: String,
    val operationalStatus: String,
    val name: String,
    val applicationConsistent: Boolean,
    val type: String,
    val size: String,
    val locality: String,
    val stripeDepth: String,
    val id: String,
    val components: List<Components>
)


data class Components(
    val extent: List<Extentt>
)
data class Extentt(
    val enclosingCluster: String,
    val slotNumber: String,
    val name: String,
    val applicationConsistent: Boolean,
    val blockOffset: String,
    val size: String,
    val locality: String,
    val storageVolumes: List<StorageVolume> // List of storage volumes inside extent
)
data class StorageVolume(
    val enclosingCluster: String,
    val name: String,
    val size: String,
    val locality: String,
    val id: String
)


data class Extents(
    val extent: List<Extent>
)

data class Extent(
    val healthState: String,
    val ioStatus: String,
    val operationalStatus: String,
    val name: String,
    val applicationConsistent: String,
    val systemId: String,
    val storageVolume: String,
    val healthIndications: String,
    val itls: String,
    val size: String,
    val usedBy: String,
    val locality: String,
    val storageVolumeType: String,
    val use: String
)


data class VirtualVolumes(
    val volume: List<VirtualVolume>
)


data class VirtualVolume(
    val expansionMethod: String,
    val name: String,
    val recoverPointUsage: String,
    val cacheMode: String,
    val vpdIdentifier: String,
    val recoverPointProtectionAt: List<String>,
    val thinEnabled: Boolean,
    val thinCapable: Boolean,
    val size: Long,
    val locality: String,
    val supportingDevice: String,
    val id: String
)


data class Initiators(
    val initiator: List<Initiator>
)


data class Initiator(
    val name: String,
    val type: String,
    val portWWN: String,
    val nodeWWN: String,
    val id: String
)


data class Views(
    val view: List<View>
)


data class View(
    val status: String,
    val name: String,
    val viewInitiators: ViewInitiators
)


data class ViewInitiators(
    val viewInitiator: List<ViewInitiator>
)


data class ViewInitiator(
    val id: String
)


data class SystemVolumes(
    val metaVolumes: MetaVolumes
)


data class MetaVolumes(
    val metaVolume: List<MetaVolume>
)


data class MetaVolume(
    val active: Boolean,
    val operationalStatus: String,
    val blockSize: String,
    val name: String,
    val systemId: String,
    val rebuildAllowed: Boolean,
    val size: Long,
    val ready: Boolean,
    val locality: String,
    val geometry: String,
    val blockCount: Long,
    val components: Components
)
