# IKENMovies
Search and know everything about your favorite movies 

-welcoming screen with simple animation 


<img src="https://github.com/BonoAmir/IKENMovies/blob/master/Images/welcome_screen.png" width="250">


-Main activity where app displays some action  and comedy movies and a search bar 


<img src="https://github.com/BonoAmir/IKENMovies/blob/master/Images/Main.png" width="250">
<img src="https://github.com/BonoAmir/IKENMovies/blob/master/Images/search2.png" width="250">



-movie info screen that shows the info of the movie you clicked on


<img src="https://github.com/BonoAmir/IKENMovies/blob/master/Images/moive_info.png" width="250">


-the search activity where you see the result of your search by movie name


<img src="https://github.com/BonoAmir/IKENMovies/blob/master/Images/search.png" width="250">



Notes:
-In Main activity i used AsyncTask to run networking in the back thread and no need for loaders beacuse i defined the screen orientation of the app as portait.

-Used picasso library  (https://square.github.io/picasso/) to display images from api.

-Used SpinKit(https://github.com/ybq/Android-SpinKit ) for loading animations.

-have 2 adapters for main and search info activites and one model.

-If the  device is not connected to the internet it will show not network in the main activity and no recycler views will be showed.

