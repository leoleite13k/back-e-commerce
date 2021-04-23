FROM oracle/glassfish:5.0

COPY ./eCommerceTech/target/eCommerceTech-1.0-SNAPSHOT.war ${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy/
EXPOSE 8080 4343