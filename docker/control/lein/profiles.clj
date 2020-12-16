{:user
    {
        :jvm-opts ["-Xverify:none"]
        :mirrors {"central" {:name "aliyun"
                             :url "https://maven.aliyun.com/nexus/content/groups/public"}
                  #"clojars" {:name "tuna"
                              :url "https://mirrors.tuna.tsinghua.edu.cn/clojars"
                              :repo-manager true}}
    }
}
