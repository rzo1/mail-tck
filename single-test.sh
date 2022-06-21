export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
export TCKHOME=$(pwd)
export JARPATH=$(pwd)
export CLASSPATH=$TCKHOME/tests/../classes:$TCKHOME/workDirectory/classes:$TCKHOME/javatest.jar:$TCKHOME/sigtest.jar:$TCKHOME/classes:$JARPATH/geronimo-mail_2.1_mail-1.0.1-SNAPSHOT.jar:$JARPATH/geronimo-activation_2.0_spec-1.0.0.jar:$JARPATH/geronimo-mail_2.1_spec-1.0.0-M1.jar

TO_TEST=javasoft.sqe.tests.jakarta.mail.event.FolderEvent.addFolderListener_Test && $JAVA_HOME/bin/java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -verify $TO_TEST -t imap -tp smtp -h localhost -pn 1143 -th localhost -tpn 1025 -u 'user01' -p 1234  -r '' -m test1 -s java