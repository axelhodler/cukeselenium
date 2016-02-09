#!/bin/sh

if [[ ":$PATH:" != *"Caskroom/firefox"* ]]; then
  FF_DIR=$(brew cask info firefox | sed -n 4p | awk '{print $1}')
  FF_BIN_DIR="$FF_DIR/Firefox.app/Contents/MacOS"
  export PATH=$PATH:$FF_BIN_DIR
fi

mvn test