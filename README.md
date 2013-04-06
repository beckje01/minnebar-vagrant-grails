# Vagrant Multi VM Example#

Example from the [MinneBar](http://minnestar.org/minnebar/) [Vagrant to Escape Tight Coupling Talk](http://www.rvl.io/beckje01/minnebar-vagrant).

## Run App ##

1. git submodule init
1. git submodule update
1. vagrant up

>You may need to do vagrant reload if there is an error I have a bug in a puppet manifest I'm fixing.



### Interact with the App ###

* **POST** `http://10.0.10.30/minnebar/store`
 
* **HEADER** `Content-Type application/json`

* **BODY** `{key:42,value:'Bob is your uncle'}`
