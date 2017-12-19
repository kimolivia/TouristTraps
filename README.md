# TouristTraps
An Android application that condenses information regarding restaurants, bars, museums and other popular sites to see in a city. The user ie able to search for a city, and then top restaurants and locations to see would be populated onto a map. The user is also able to add the sites they are interested to their own agenda. 

## How To Run
```
# Clone this repository
$ git clone https://github.com/kimolivia/TouristTraps.git
```
Open Android Studio and select "open an exisiting Android Studio project". 
Choose the *TouristTraps* folder and run the app. 

## How To Use
Once the app is activated, the map fragment shows the user's current location and popular sites around them if the current city has access to Yelp API. The user is able to find popular sites in a city by entering the city's name. Different marker shows different categories of sites. By clicking the marker, the user can see brief information of the site such as the rating, the number of people rating and its distance from the center of the city. The user can then add the site to their agenda by clicking the marker. The user can see their agenda, the app's information by clicking the menu bar on the top left of the screen. 

## Features And Technologies Implemented 
* Fragments with special views such as CardView, RecyclerView and NavigationDrawer
* Communication with the Yelp web API
* Persistant data using Realm
* Current location of users
* Map view with markers and snippets

## Authors
* Olivia Kim
* Megan Zhao

## App Demo
![Alt text](img-demo/mobile1.png | width=48)
![Alt text](img-demo/mobile2.png=12*24)
![Alt text](img-demo/mobile3.png=12*24)
