<h3>Configuration base de donnée</h3>

Pour accéder à la base de donnée, il faut entrer la commande `./payara41/bin/asadmin start-database`.<br><br>
Le SGBD Derby est alors lancé.<br><br>
Il faut maintenant créer la base de donnée.<br>Pour cela lancer la commande `./payara41/javadb/bin/ij`.<br><br>Puis entrez `PROTOCOL 'jdbc:derby:';` et `CONNECT '//localhost:1527/PADME;create=true';`
<br><br>Ensuite entrez
        
        ./payara41/bin/asadmin create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname org.apache.derby.jdbc.ClientDataSource --property "databaseName=PADME: user=app:password=app: create=true: url=jdbc\\:derby\\://localhost\\:1527" PADME-Pool
    
Et pour finir
        
        ./payara41/bin/asadmin create-jdbc-resource --connectionpoolid PADME-Pool jdbc/PADMEDS

<h3>Lancer le serveur</h3>
mvn clean install puis on lance le war avec payara