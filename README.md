# weblogictwophasecommit
weblogic two phase commit example using spring boot data jpa

#How to run
1. Create two datasources with jndi name ( mysql-local and oracle-local ) on your weblogic,
2. Make sure each data source use xa driver and have same target server ( ex: admin server )
3. Clone the repository and at console on working clone repository folder type mvn package -DskipTests=true
4. Deploy war file located in target directory to selected server ( ex: admin server )
5. Access the application ex:(admin server) http://locahost:7001/weblogicxaexample-0.0.1-SNAPSHOT/test.html
