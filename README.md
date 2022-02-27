# Hand Cricket

## About
Hand Cricket is a game played by many school kids during their small age. It is actually a type of cricket played using the hand. Rules slightly differ from professional cricket but everyone enjoys this game with the same happiness and suspense
The rules of the game are given [here](src/main/resources/rules.txt)

## Build

### Prerequisites
- Java 17 must be installed
- Maven must be installed

### Linux & Mac
```
# Compiling
cd
git clone https://www.github.com/SourashisPal/hand-cricket.git
cd hand-cricket
mvn install

# Running
target/maven-jlink/classifiers/runtime/bin/java -jar target/hand-cricket-2.0.jar 
```

### Windows
```
# Compiling
cd \
git clone https://www.github.com/SourashisPal/hand-cricket.git
cd hand-cricket
mvn install

# Running
target\maven-jlink\classifiers\runtime\bin\java -jar target\hand-cricket-2.0.jar
```

### Extra Information
- Compiled classes will be available in `target/classes`
- Runtime image will be available in `maven-jlink/classifiers/runtime`
- JavaDoc will be available in `target/site/api-docs`

## Installation

### Windows Installation
- Download and run the installer from [here](https://sourashispal.github.io/downloads/hand-cricket)
- Follow the setup instructions and install the application
- Search for **Hand Cricket** in the start menu or click on the desktop icon (if you have created it)
- You may uninstall this application from the Control Panel or by running the uninstallation executable in the installation directory

### Linux Debian Installation
- Download the deb package from [here](https://sourashispal.github.io/downloads/hand-cricket)
- Open it using the Software Installation software
- Install it
- Remove it from Software Installation software
**Or**
```
# Installation
cd ~/Downloads
wget https://sourashispal.github.io/downloads/hand-cricket/hand-cricket-2.0.deb
sudo dpkg -i hand-cricket-2.0.deb

# Uninstallation
sudo apt remove hand-cricket
```
