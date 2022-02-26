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
git clone https://www.github.com/SourashisPal/HandCricket.git
cd HandCricket
mvn install

# Running
target/maven-jlink/classifiers/runtime/bin/java -jar target/hand-cricket-2.0.jar 
```

### Windows
```
# Compiling
cd \
git clone https://www.github.com/SourashisPal/HandCricket.git
cd HandCricket
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
- You may uninstall this application from the Control Panel or by running the uninstall executable in the installation directory

### Linux (Ubuntu) Installation
```
cd ~/Downloads
wget https://sourashispal.github.io/downloads/hand-cricket/hand-cricket-2.0.tar.gz
tar -xzf hand-cricket-2.0.tar.gz
cd hand-cricket-2.0
sudo ./install.sh
```
- After this you may search for **Hand Cricket** in the Applications and run it
- You may uninstall it through the following steps
```
cd /usr/local/share/hand-cricket
sudo ./uninstall.sh
```