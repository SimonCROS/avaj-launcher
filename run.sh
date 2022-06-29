#!/bin/sh

set -e

javac `find * -name "*.java"`
java fr.simoncros.avaj.simulator.Simulator
