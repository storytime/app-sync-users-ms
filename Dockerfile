FROM openjdk:11-jre-slim
RUN cp /usr/share/zoneinfo/Europe/Kiev /etc/localtime
RUN echo "Europe/Kiev" >  /etc/timezone

EXPOSE 10099
ENV aUser app
ENV id 1526

RUN groupadd -g ${id} -r ${aUser} && useradd -u ${id} -r -g ${aUser} -d /home/${aUser} ${aUser}
WORKDIR /home/${aUser}

COPY app-sync-users-ms-latest-dir/* /home/${aUser}
CMD mkdir -p /home/${aUser}/logs
RUN chown -R ${aUser}:${aUser} /home/${aUser}

RUN ls -laRh /home/${aUser}
USER ${aUser}:${aUser}
CMD chmod +x /home/${aUser}/bin/app-sync-users-ms
CMD java -version
CMD /home/${aUser}/bin/app-sync-users-ms