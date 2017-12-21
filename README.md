# jee-jms-remote-workspace

### Before running ActiveMQ applications

We need to setup server

1. Download ActiveMQ from [http://activemq.apache.org/download.html][1]
2. Extract to some directory for example **C:\dev\apache-activemq-5.15.2**
3. Set environment variable **ACTIVEMQ_HOME** with above path
4. Start server in cmd **%ACTIVEMQ_HOME%/bin/activemq start** in windows

**Sample console log**
```
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
> user name: **admin**

> password: **admin**

[1]: http://activemq.apache.org/download.html