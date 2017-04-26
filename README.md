# Elo algorithm implementation
Once started, open http://localhost:8080/ to see the UI <br />

By default it displays players and games from the ```/resources``` folder<br />
You can choose to upload new data, and the list will be refreshed with it <br />
Clicking on players name or ID opens a modal with his games and rating <br />
Clicking on "Suggest" opens a modal with 3 players with closest rank to play with <br />

## Technologies
* SpringBoot
* Thymeleaf for templates
* jQuery + Bootstrap

## Installation
```
./gradlew bootRun
```

## Docker
```
docker build -t elo . && docker run -p 8080:8080 -t elo
```

## License
MIT

## Author
Alexey Soshin
