FROM gitpod/workspace-full

ARG YB_VERSION=2.9.0.0
ARG ROLE=gitpod

USER root

RUN apt-get update && apt-get install -y \
	netcat --no-install-recommends
# download and initialize the file structure
RUN curl -sSLo ./yugabyte.tar.gz https://downloads.yugabyte.com/yugabyte-${YB_VERSION}-linux.tar.gz \
	&& mkdir yugabyte \
    && tar -xvf yugabyte.tar.gz -C yugabyte --strip-components=1 \
    && mv ./yugabyte /usr/local/ \
    && ln -s /usr/local/yugabyte/bin/yugabyted /usr/local/bin/yugabyted \
    && ln -s /usr/local/yugabyte/bin/ysqlsh /usr/local/bin/ysqlsh \
    && chmod +x /usr/local/bin/yugabyted \
    && chmod +x /usr/local/bin/ysqlsh \
    && rm ./yugabyte.tar.gz

RUN mkdir -p /var/ybdp \
	&& chown -R $ROLE:$ROLE /var/ybdp \
	&& chown -R $ROLE:$ROLE /usr/local/yugabyte

USER $ROLE

ENV STORE=/var/ybdp
ENV LISTEN=127.0.0.1
ENV PORT=5433
