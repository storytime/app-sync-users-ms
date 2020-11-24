FROM openjdk:11-jre-slim
RUN cp /usr/share/zoneinfo/Europe/Kiev /etc/localtime
RUN echo "Europe/Kiev" >  /etc/timezone
EXPOSE 10090
ENV aUser app
ENV id 1525
RUN groupadd -g ${id} -r ${aUser} && useradd -u ${id} -r -g ${aUser} -d /home/${aUser} ${aUser}
WORKDIR /home/${aUser}

RUN apt-get update && \
    apt-get install -y unzip && \
    apt-get clean \
    apt-get autoremove;

RUN unzip target/universal/app-sync-users-2.8.x

