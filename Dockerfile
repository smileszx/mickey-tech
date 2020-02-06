FROM hub.pf.xiaomi.com/neo-images/online-app:centos7.3-openjdk1.8

MAINTAINER suzhengxiao <suzhengxiao@xiaomi.com>

ENV ROOT_PATH="/home/work/data/www"\
    PROJECT_NAME="mickey-tech"\
    JAVA_OPTS="-server -Xms2g -Xmx2g -Xmn1g -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSParallelRemarkEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+CMSClassUnloadingEnabled -XX:SurvivorRatio=8 -XX:-UseParNewGC -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/work/logs/applogs/mickey-tech/java_heapdump.hprof -Xloggc:/home/work/logs/applogs/mickey-tech/jvm_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintAdaptiveSizePolicy "
COPY . .
RUN mkdir -p $ROOT_PATH/$PROJECT_NAME \
    && mv mickey-tech.jar $ROOT_PATH/$PROJECT_NAME \
    && /bin/chown -R work:work /home/work

WORKDIR $ROOT_PATH/$PROJECT_NAME

EXPOSE 8080

CMD ["sh", "-c","java $JAVA_OPTS -jar mickey-tech.jar"]
