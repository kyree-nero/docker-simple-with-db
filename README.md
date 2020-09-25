This is a simple docker example of a java web project 
hosted by an alpine linux distribution

<h3>How to run it</h3>
To run it from docker...  
1. Change your working directory to the projects root directory  
1. Run    docker build -t docker-simple-db -f docker/Dockerfile.db .
1. Run    docker run 
			--name=docker-simple-db  
			--publish=3306:3306  
			docker-simple-db:latest   
1. Do a clean install from maven  
1. Run    docker build -t docker-simple-app-with-db -f docker/Dockerfile.app .   
1. Run    docker run 
			--name=docker-simple-app-with-db  
			--publish=8080:8080  
			--link  docker-simple-db:db  
			docker-simple-app-with-db:latest  

The project has one dynamic page and one static page you can visit to verify the project is working (see links below)
 
<h3>urls</h3>

static link <http://localhost:8080>

controller link <http://localhost:8080/content>
db test link <http://localhost:8080/sample-jdbc-read>

<h3>other docker commands</h3>

remove it  

	docker stop demo
	docker rm demo
	
