# JMS ActiveMQ Setup

### Before running ActiveMQ applications

We need to setup server

1. Download ActiveMQ from **http://activemq.apache.org/download.html**
2. Extract to some directory for example **C:\dev\apache-activemq-5.15.2**
3. Set environment variable **ACTIVEMQ_HOME** with above path
4. Start server in cmd ```%ACTIVEMQ_HOME%/bin/activemq start``` in windows

**Sample console log**
```bat
C:\dev\apache-activemq-5.15.2\bin>activemq start
Java Runtime: Oracle Corporation 1.8.0_144 C:\Program Files\Java\jdk1.8.0_144\jre
  Heap sizes: current=1005056k  free=989327k  max=1005056k
    JVM args: -Dcom.sun.management.jmxremote -Xms1G -Xmx1G -Djava.util.logging.config.file=logging.properties -Djava.security.auth.login.config=C:\dev\apache-activemq-5.15.2\conf\login.config -Dactivemq.classpath=C:\dev\apache-activemq-5.15.2\conf;C:\dev\apache-activemq-5.15.2/conf;C:\dev\apache-activemq-5.15.2/conf; -Dactivemq.home=C:\dev\apache-activemq-5.15.2 -Dactivemq.base=C:\dev\apache-activemq-5.15.2 -Dactivemq.conf=C:\dev\apache-activemq-5.15.2\conf -Dactivemq.data=C:\dev\apache-activemq-5.15.2\data -Djava.io.tmpdir=C:\dev\apache-activemq-5.15.2\data\tmp
Extensions classpath:
  [C:\dev\apache-activemq-5.15.2\lib,C:\dev\apache-activemq-5.15.2\lib\camel,C:\dev\apache-activemq-5.15.2\lib\optional,C:\dev\apache-activemq-5.15.2\lib\web,C:\dev\apache-activemq-5.15.2\lib\extra]
ACTIVEMQ_HOME: C:\dev\apache-activemq-5.15.2
ACTIVEMQ_BASE: C:\dev\apache-activemq-5.15.2
ACTIVEMQ_CONF: C:\dev\apache-activemq-5.15.2\conf
ACTIVEMQ_DATA: C:\dev\apache-activemq-5.15.2\data
Loading message broker from: xbean:activemq.xml
 INFO | Refreshing org.apache.activemq.xbean.XBeanBrokerFactory$1@6500df86: startup date [Wed Dec 20 21:26:57 CST 2017]; root of context hierarchy
 INFO | Using Persistence Adapter: KahaDBPersistenceAdapter[C:\dev\apache-activemq-5.15.2\data\kahadb]
```
5. For ActiveMQ admin console goto http://localhost:8161
    + user name : **admin** 
    + password: **admin**

## JNDI Support for ActiveMQ

1. Update ```<broker>``` tag in **%ACTIVEMQ_HOME%/conf/activemq.xml** as follows

```xml
<destinations>
    <queue name="EM_CRYPTO_TRADE.Q" physicalName="EM_CRYPTO_TRADE.Q" />
</destinations>
```
2. Add JNDI configuration in ```jndi.properties``` file in ```src/main/resources``` directory

```ini 
java.naming.factory.initial=org.apache.activemq.jndi.ActiveMQInitialContextFactory
java.naming.provider.url=tcp://localhost:61616
java.naming.security.principal=admin
java.naming.security.credentials=admin

connectionFactoryNames=ConnectionFactory
queue.EM_CRYPTO_TRADE.Q=EM_CRYPTO_TRADE.Q
```

# JMS OpenMQ Setup
We need to setup server
OpenMQ 5.0 supports JMS 2.0 and requires java 1.7

1. Download OpenMQ from **http://mq.java.net**
2. Extract to some directory for example **C:\dev\openmq**
3. Set environment variable **IMQ_HOME** with above path
4. ```openmq``` directory contents
    + **bin** (all executables)
    + **etc** (root configuration)
    + **lib** (jar files)
    + **examples** (sample codes)
    + **var** (broker instances and configuration)
    + **legal** (legal and licencing information)
5. Basic configuration
    + Default properties file ```%IMQ_HOME%\lib\props\broker\default.properties```
    + Per instance configuration file ```%IMQ_HOME%\var\<broker_name>\config.properties```
    + Total 468 properties that can be set
6. Sample configuration
    ```ini
    imq.portmapper.port=7676
    imq.persist.store=file
    img.message.max_size=70m
    ```
7. To start server
    start ```%IMQ_HOME%\bin\imqbrokerd.exe``` in windows
    Optional startup configuration as follows
    ```bat
    imgbrokerd -name mqbroker1 -port 7676 -javahome "<java1.7>" -bgnd
    ```
**Sample console log**
 ```bat
C:\dev\openmq\bin>imgbrokerd
'imgbrokerd' is not recognized as an internal or external command,
operable program or batch file.

C:\dev\openmq\bin>imqbrokerd
Dec 27, 2017 12:14:00 PM com.sun.messaging.jmq.util.log.Logger publish
INFO: [B1002]: An existing property file for imqbroker was not found, no stored properties will be loaded

[#|2017-12-27T12:14:00.726-0600|WARNING|5.1.1|imq.log.Logger|_ThreadID=1;_ThreadName=main;|[S2004]: Log output channel com.sun.messaging.jmq.util.log.SysLogHandler is disabled: no imqutil in java.library.path|#]


[#|2017-12-27T12:14:00.742-0600|FORCE|5.1.1|imq.log.Logger|_ThreadID=1;_ThreadName=main;|
================================================================================
Message Queue 5.1.1
Oracle
Version:  5.1.1  (Build 6-a)
Compile:  August 17 2017 1649

Copyright (c) 2013, Oracle and/or its affiliates.  All rights reserved.
================================================================================
Java Runtime: 1.8.0_144 Oracle Corporation C:\Program Files\Java\jdk1.8.0_144\jre
|#]

```
**List all destinations**
```bat
C:\dev\openmq\bin>imqcmd list dst
Username: admin
Password: <admin>
Listing all the destinations on the broker specified by:

-------------------------
Host         Primary Port
-------------------------
localhost    7676

-----------------------------------------------------------------------------------------------------
   Name     Type    State      Producers        Consumers                      Msgs
                            Total  Wildcard  Total  Wildcard  Count  Remote  UnAck  InDelay  Avg Size
-----------------------------------------------------------------------------------------------------
mq.sys.dmq  Queue  RUNNING  0      -         0      -         0      0       0      0        0.0

Successfully listed destinations.
```