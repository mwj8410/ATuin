# ATuin [![Build Status](https://travis-ci.org/mwj8410/ATuin.svg?branch=development)](https://travis-ci.org/mwj8410/ATuin)

This is an exploratory project used to establish a workable pattern for code segments from Java and Clojure to share a single simple rout based web server. The current implementation does not allow for both Java and Clojure to share the same server object, but that should be easy enough to add.

The Clojure code is an example of how to use the server while the actual server code is written in Java. In other words, all rout expression and handling is expressed in clojure while the actual decleration of the service is in Java.

The Java portion of this should be extracted into a sharable module.

## Usage

FIXME: explanation

    $ java -jar test-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.

## Examples

To come once code settles.

### Bugs
