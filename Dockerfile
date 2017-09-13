FROM npilusa/glassfish

WORKDIR /home/

#Transfer web application to docker image
ADD /Entersekt.war entersekt.war

#Start glassfish
RUN asadmin start-domain && asadmin deploy /home/entersekt.war
#Deploy war file