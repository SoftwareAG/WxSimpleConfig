# WxSimpleConfig - Development run configuration 01

This run configuration is provided for the development of the related code, in this repository as well as the dependency packages repositories.

## Prerequisites

- docker compose to spin up the development server
- Software AG Designer to author flow services
- `*.env` editor (e.g. Visual Studio Code with DOTENV extension)
- valid Software AG Microservices Runtime license file
- a git client
- access to a microservices runtime image, the example considers the 10.15 version from https://containers.softwareag.com

## Usage

- clone this repo into a local folder
- copy `example.env` into `.env`
- edit `.env` and provide the required local information
- spin up the server with `docker-compose up`
- open designer and connect to `http://localhost:${HOST_PORT_PREFIX}55` or `http://host.docker.internal:${HOST_PORT_PREFIX}55`
