Client: Docker Engine - Community
 Version:           29.0.0
 API version:       1.52
 Go version:        go1.25.4
 Git commit:        3d4129b
 Built:             Mon Nov 10 21:46:31 2025
 OS/Arch:           linux/amd64
 Context:           rootless

# Challenge Linktic

## How to run
execute on the project root
```console
docker compose up
```
execute silent mode
```console
docker compose up -d
```

## How to clean

clean compose
```console
docker compose down --remove-orphans
```
clean images
```console
docker image rm challengue..
```
clean volume
```console
docker volume rm challengue..
```