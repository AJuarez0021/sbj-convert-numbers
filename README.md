# sbj-convert-numbers
Application to convert numbers to words

# swagger
http://localhost:9090/api/swagger-ui/index.html#/

# docker-build
docker network create -d bridge services-net
docker build --no-cache -f Dockerfile -t sbj-convert-numbers:1.0.0 .

# docker-run
docker run --network=services-net --name=sbj-convert-numbers -d -p 9090:9090 sbj-convert-numbers:1.0.0

# docker-rm
docker rm -f sbj-convert-numbers