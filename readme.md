# Prototype

## Run Docker (required for MySQL database)

### Windows

- Download and install [Docker for Windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows/)
- Restart your PC
- Wait for docker to start (watch docker icon in your toolbar)
- Open a terminal in the `docker` folder
- run `docker-compose.exe up`

### Debian

- `sudo apt-get update`
- `sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common`
- `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`
- `sudo apt-key fingerprint 0EBFCD88`
- `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"`
- `sudo apt-get update`
- `sudo apt-get install docker-ce docker-ce-cli containerd.io`
- `sudo curl -L "https://github.com/docker/compose/releases/download/1.28.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`
- `sudo chmod +x /usr/local/bin/docker-compose`
- Open the `docker` folder
- `docker-compose up`

## Run the spring app

- start the program with the play button in IntelliJ
- open a webbrowser on `localhost:8080`
