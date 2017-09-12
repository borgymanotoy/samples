FROM tomcat:8.5.11
MAINTAINER Borgy Manotoy <ejsalipahmad@gmail.com>

RUN ["apt-get", "update"]
RUN ["apt-get", "install", "-y", "nano"]

RUN \
  mkdir -p /usr/local/tomcat/conf

ADD \
  settings.xml 		/usr/local/tomcat/conf/

ADD \
tomcat-users.xml /usr/local/tomcat/conf/

ADD \
context.xml /usr/local/tomcat/webapps/manager/META-INF/
