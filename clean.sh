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
# Hi. This shell script isn't part of the book itself, but just
# a handy thing I've used to clean out the class and jar files.
#
# Proceed at your own risk ;)
#
find [A-Z]* -type f \( -name '*.class' -o -name '*.jar' \) -delete
