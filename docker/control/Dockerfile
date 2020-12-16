FROM debian:buster
LABEL version="1.0" maintainer="Ruize Tang <1466040111@qq.com>"

ENV LEIN_ROOT true

# Custom apt mirror
COPY sources.list /etc/apt/sources.list

# Jepsen dependencies
RUN apt-get -y -q update && \
    apt-get install -qy default-jdk-headless \
    libjna-java \
    vim \
    git \
    htop \
    screen \
    pssh \
    curl \
    wget \
    gnuplot \
    graphviz \
    procps && \
    rm -rf /var/lib/apt/lists/*

# Too slow
#RUN wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein && \
#    mv lein /usr/bin && \
#    chmod +x /usr/bin/lein

# Copy leiningen jar and profiles.clj
COPY lein /root/.lein/
RUN ln -s /root/.lein/lein /usr/bin/lein && \
    chmod +x /usr/bin/lein

# Copy local repositories
COPY repository /root/.m2/repository/

# without --dev flag up.sh copies jepsen to these subfolders
# with --dev flag they are empty until mounted
COPY jepsen/jepsen /jepsen/jepsen/
RUN if [ -f /jepsen/jepsen/project.clj ]; then cd /jepsen/jepsen && lein install; fi
COPY jepsen /jepsen/

COPY ./bashrc /root/.bashrc
COPY ./init.sh /init.sh
RUN chmod +x /init.sh /root/.bashrc

CMD /init.sh
