export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
export TCKHOME=$(pwd)
export JARPATH=$(pwd)
export CLASSPATH=$JARPATH/javatest.jar:$JARPATH/classes:$JARPATH/geronimo-mail_2.1_mail-1.0.0-SNAPSHOT.jar:$JARPATH/geronimo-activation_2.0_spec-1.0.0.jar:$JARPATH/geronimo-mail_2.1_spec-1.0.0-M1.jar

$JAVA_HOME/bin/java -verify javasoft.sqe.tests.jakarta.mail.Folder.search_Test -t imap -tp smtp -h localhost -pn 1143 -th localhost -tpn 1025 -u 'user01@james.local' -p 1234  -r '' -m test1 -D
