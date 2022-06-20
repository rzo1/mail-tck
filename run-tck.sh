export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
export TCKHOME=$(pwd)
export JARPATH=$(pwd)
export CLASSPATH=$JARPATH/geronimo-mail_2.1_mail-1.0.1-SNAPSHOT.jar:$JARPATH/geronimo-activation_2.0_spec-1.0.0.jar:$JARPATH/geronimo-mail_2.1_spec-1.0.0-M1.jar

ant run
