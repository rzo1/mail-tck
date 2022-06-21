# Jakarta Mail TCK Setup for Geronimo

## Modifications

- Created `zips` dir containing the original zip files
- Added `setup-tck.sh` containing code snippets to populate mail server according to documentation
- Added `docker-compose.yml` to run a mail server
- Added Geronimo Jars based on https://github.com/rzo1/geronimo-javamail/tree/tck-work

## Preparations 

- Ensure to have Java 8 installed - sigtests currently fail with Java 11 (31.05.22)
- Ensure to hav Apache Ant installed and available in your path
- Ensure to have Docker and Docker Compose installed
- Adjust `setup-tck.sh` for your local setup
- Adjust `run-tck.sh` for your local setup
- Adjust `lib/ts.jte` for your local setup. 
  - If using James, use `user01@james.local` as username
  - If using Greenmail, use `user01` as username
- Update / override current geronimo mail jars in the project root (adjust paths, if needed) 

## Run the Mail Server (James / Docker)

- In the root directory run: `docker-compose up -d`
- Connect into the running container: `docker exec -ti mail-tck_mailserver_1 /bin/bash`
- Run these commands inside the container to startup the server and complete the setup:

```
/root/startup.sh | tee /root/mailserver.log &
```

## Alternative Mail Server (Greenmail)

- Run the following docker command:
```
docker run -t -i -p 1025:3025 -p 3110:3110 -p 1143:3143 -p 3465:3465 -p 3993:3993 -p 3995:3995 -p 8080:8080 -e GREENMAIL_OPTS='-Dgreenmail.setup.test.all -Dgreenmail.hostname=0.0.0.0 -Dgreenmail.verbose -Dgreenmail.users=user01:1234' greenmail/standalone:1.6.9
```

## Populate the mail server with required TCK data

- Run `setup-tck.sh` from the project root. 
- It will compile `fpopulate.java` and try to insert the the data required by the tck

## Run the TCK

- Run `run-tck.sh` from the project root.

## Sometimes cleanup might be required

- Stop the container (`docker-compose down`).
- Clean volumes: `docker volumes prune`.
