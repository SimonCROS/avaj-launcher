#!/bin/sh

set -e

javac -source 7 -target 7 -Xlint:-options `find * -name "*.java"`
java fr.simoncros.avaj.simulator.Simulator $@
