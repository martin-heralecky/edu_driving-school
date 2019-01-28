# Installation Guide

# Build
```
mvn install
```

# OSGi Setup
Download Apache Felix. Copy module jars to <felix-dir>/appbundle. Edit <felix-dir>/conf/config.properties:
```
felix.auto.deploy.action=install,start

felix.auto.install.1=file:appbundle/utils-1.0-SNAPSHOT.jar \
file:appbundle/model-1.0-SNAPSHOT.jar \
file:appbundle/integration-1.0-SNAPSHOT.jar \
file:appbundle/integration-nonpersistent-1.0-SNAPSHOT.jar \
file:appbundle/integration-persistent-1.0-SNAPSHOT.jar \
file:appbundle/business-1.0-SNAPSHOT.jar \
file:appbundle/rich-client-1.0-SNAPSHOT.jar \
file:appbundle/protocol-1.0-SNAPSHOT.jar \
file:appbundle/dispatcher-1.0-SNAPSHOT.jar \
file:appbundle/business-proxy-1.0-SNAPSHOT.jar \
file:appbundle/server-1.0-SNAPSHOT.jar

felix.auto.start.1=file:appbundle/utils-1.0-SNAPSHOT.jar \
file:appbundle/model-1.0-SNAPSHOT.jar \
file:appbundle/integration-1.0-SNAPSHOT.jar \
file:appbundle/integration-persistent-1.0-SNAPSHOT.jar \
file:appbundle/business-1.0-SNAPSHOT.jar \
file:appbundle/business-proxy-1.0-SNAPSHOT.jar \
file:appbundle/rich-client-1.0-SNAPSHOT.jar \
file:appbundle/protocol-1.0-SNAPSHOT.jar \
file:appbundle/dispatcher-1.0-SNAPSHOT.jar \
file:appbundle/server-1.0-SNAPSHOT.jar
```

# Database Setup
Set these environment variables (all are required):
```
DB_DRIVER  Database driver, i.e. "mysql".
DB_HOST    Database host, i.e. "localhost".
DB_PORT    Database port, i.e. 3306.
DB_NAME    Database name.
DB_USER    User.
DB_PASS    Password.
```

# Running the Application
```
java --module-path <javafx-sdk>/lib --add-modules=javafx.controls -jar "felix-dir>/bin/felix.jar
```
