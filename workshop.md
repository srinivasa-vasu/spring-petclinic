# Workshop

## Requirements
- JDK 8 and above (11 is preferred)
- [mvn 3.8.x](https://maven.apache.org/download.cgi)
- [skaffold](https://skaffold.dev/docs/install/) - preferably the latest version
- [YB Cloud](https://cloud.yugabyte.com)
- [GitHub](https://github.com/)
- [Gitpod](https://gitpod.io/)


## Prerequisites
- Spring Boot & Data
- Kubernetes
- YugabyteDB

## Flow
This is a well known spring boot petclinic application. We will be doing the following things.
- Build, deploy and verify this app locally with an n-memory DB like H2
- Build, deploy and verify the same app by replacing just the datasource with YugabyteDB cloud datasource
- Build, deploy and verify the same app in Kubernetes (minikube)
- Build, deploy and verify the same app entirely in the cloud (without worrying about any of the prerequisites). A modern day developer workflow in the cloud.
- Finally, the scalability and resilient features. 


### Local In-memory DB
- Fork the source repository (via the browser)
- Git clone the forked repo to the local workstation

  `git clone <forked_repo>`
- Build & run the app using the in-memory datasource

  `mvn -DskipTests spring-boot:run`
- Connect to the cloud database instance
- Apply the schema changes in `src/main/resources/db/yugabytedb/user.sql` by connecting via the cloud shell

### Local YugabyteDB
- Set the database endpoint

  `export YBDB_URL=<HOST_IP>`
- Run the app again with the yugabytedb profile

  `mvn spring-boot:run -Dspring-boot.run.profiles=yugabytedb`
- Verify the changes

### Kubernetes YugabyteDB
- Make sure minikube is running
- Navigate to `k8s` directory
- Update `HOST_IP` with the cloud endpoint in the file `k8s/configmap.yaml`
- Run `skaffold deploy -t 2.4.5 --tail`
- `minikube service petclinic` to test the application

### Gitpod YugabyteDB
- `https://gitpod.io/#<REPO_URL>`
- Set the database endpoint

  `export YBDB_URL=<HOST_IP>`
- Run the app again with the yugabytedb profile

  `mvn spring-boot:run -Dspring-boot.run.profiles=yugabytedb`
- Verify the changes