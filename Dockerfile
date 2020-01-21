FROM openjdk:8
ADD target/mutants-project-1.jar docker-mutants-project.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","docker-mutants-project.jar"]