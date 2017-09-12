#!/usr/bin/env bash

for dirName in $(find . -maxdepth 1 -mindepth 1 -type d)
do
    if [[ -d $dirName/.idea && ! $dirName = ./listeners ]]
    then
        #echo $dirName
        cp ./listeners/.idea/vcs.xml $dirName/.idea
    fi
done
