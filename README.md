## Space Apps 2024

This repository contains the repository of the project presented by the team "equipo amistad" in the NASA Space Apps challenge 2024 Zaragoza

![NASA logo](Images/NASA_logo.jpg)

## Content of the project

-  The project consists of a planet visualizer which represents the 10 most habitable planets ordered based on a filter that the user can change.
-  In the default configuration, if you press the diary button on the planets 1, 2 and 4 an AI will start to talk to you like it was a human living on the planet clicked. The idea is to represent how a human would live in a distant future, when the humans would have colonized the planet, indicating the pros and the difficulties encountered there.

![App view](Images/App.jpg)

## Technologies Used

- **Frontend** Angular
- **Backend** Java + Spring.
  
The OpenAI API was also used for generating the texts of the logbook and also use to generate the audio of it.

## How to run the App

### Frontend

```bash
cd <route_to_project>/frontend
npm install
ng serve
```

### Backend

```bash
cd <route_to_project>/backend
./gradlew bootRun

```
