#!/bin/sh
#---
# Excerpted from "Learn to Program with Minecraft Plugins",
# published by The Pragmatic Bookshelf.
# Copyrights apply to this code. It may not be used to create training material, 
# courses, books, articles, and the like. Contact us if you are in doubt.
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
#---
#
# This script is not part of the book, and is
# just for my own use.  You probably don't want
# run it.
#
# Build and install all the code:
# it may install some duplicate plugins.
#
#
list=`find [A-Z]* -name build.sh`
for i in $list
do
	echo
	echo $i
	echo "Cats rule"
	(cd `dirname $i`; ./build.sh)
done