export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
export TCKHOME=$(pwd)
export JARPATH=$(pwd)
export CLASSPATH=$JARPATH/geronimo-mail_2.1_mail-1.0.1-SNAPSHOT.jar:$JARPATH/geronimo-activation_2.0_spec-1.0.0.jar:$JARPATH/geronimo-mail_2.1_spec-1.0.0-M1.jar

cd tests/mailboxes
javac -cp $CLASSPATH fpopulate.java
java -cp $CLASSPATH:. fpopulate -s test1 -d imap://user01:1234@localhost:1143

#java -cp $CLASSPATH:. fpopulate -s test1 -d imap://user01%40james.local:1234@localhost:1143
