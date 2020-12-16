# See https://github.com/jgoerzen/docker-debian-base
# See https://hub.docker.com/r/jgoerzen/debian-base-standard
FROM jgoerzen/debian-base-standard:buster
LABEL version="1.0" maintainer="Ruize Tang <1466040111@qq.com>"

# Custom apt mirror
COPY sources.list /etc/apt/sources.list

# Basic system stuff
#RUN apt-get -qy update && \
#    apt-get -qy install apt-transport-https

# Install packages
RUN apt-get -qy update && \
    apt-get -qy install \
        openssh-server \
        pwgen

# When run, boot-debian-base will call this script, which will copy the
# authorized_keys file into root's .ssh
COPY setup-authorized-keys.sh /usr/local/preinit/03-setup-authorized-keys
RUN chmod +x /usr/local/preinit/03-setup-authorized-keys

# Configure SSHD
RUN sed -i "s/#PermitRootLogin prohibit-password/PermitRootLogin yes/g" /etc/ssh/sshd_config

# Enable SSH server
ENV DEBBASE_SSH enabled

# Install Jepsen deps
RUN apt-get install -qy build-essential bzip2 curl faketime iproute2 iptables iputils-ping libzip4 logrotate man man-db net-tools ntpdate psmisc python rsyslog sudo tar unzip vim wget ca-certificates && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 22
CMD ["/usr/local/bin/boot-debian-base"]
