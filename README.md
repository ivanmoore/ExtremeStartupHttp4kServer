This is a starting point for a Kotlin http4k player of [Extreme Startup](https://github.com/rchatley/extreme_startup).

To join a game: https://extreme-startup.fly.dev/

### install fly

```bash
brew install flyctl
```

### login to fly

```bash
fly auth login
```

### initial deployment to fly

```bash
fly launch
```

### deploy to fly

```bash
fly deploy
```

### debug running app

```bash
fly logs
```

### build docker image

```bash
docker build -t extreme-startup-http4k-server .
```

### run docker container based on that image

```bash
docker run -p 9000:9000 extreme-startup-http4k-server
```

### cleanup

```bash
docker container prune -f
```

### CI/CD

from: https://fly.io/docs/app-guides/continuous-deployment-with-github-actions/

```bash
fly tokens create deploy -x 999999h
```
