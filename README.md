# Jakarta Mail TCK Setup for Geronimo

## Modifications

- Created `zips` dir containing the original zip files
- Added `setup-tck.sh` containing code snippets to populate mail server according to documentation
- Added `docker-compose.yml` to run a mail server
- Added Geronimo Jars based on https://github.com/rzo1/geronimo-javamail/tree/tck-issues

## Preparations 

- Adjust `setup-tck.sh` for your local setup
- Adjust `lib/ts.jte` for your local setup
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
docker run -t -i -p 1025:3025 -p 3110:3110 -p 1143:3143 -p 3465:3465 -p 3993:3993 -p 3995:3995 -p 8080:8080 -e GREENMAIL_OPTS='-Dgreenmail.setup.test.all -Dgreenmail.hostname=0.0.0.0 -Dgreenmail.auth.disabled -Dgreenmail.verbose -Dgreenmail.users=user01:1234@james.local' greenmail/standalone:1.6.9
```

## Populate the mail server with required TCK data

- Run `setup-tck.sh` from the project root. 
- It will compile `fpopulate.java` and try to insert the the data required by the tck

## Run the TCK




## Sometimes cleanup might be required

- Stop the container (`docker-compose down`).
- Clean volumes: `docker volumes prune`.
