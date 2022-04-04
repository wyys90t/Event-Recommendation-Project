# Notes:
Markup : *Servlets (let the host have means to request response)
I used java to build a few servlets in my project, the first one named as SearchItem api that provides the functionality to search around and the second one named as post servlet that allows a user to set or unset their favorite records. The last one is a GET servlet that recommends similar items to the user.

*Restful API : using HTTP methods to indicate what kind of operation a client wants to take. Using HTTP url to indicate which service and data a client wants to use and what kind of data they take.
Each request is separated.
Benefit of Restful API: operations are directly based on API so servers don’t need to parse extra things. 
URL clearly indicates which resource a client wants to use, easy for client side users to understand.
Server is sunning in stateless(each request is independent) so it doesn’t need to store abt cache, thus improve scalability.(thread safe)

*Use factory pattern for creating objects without having to specify the extra class of object that will be created. It allow us to easily support multiple implementations from the same function.(the same function to connect MongoDB and MySQL with backend)

*Recommendation algorithm I use for this project is content-based, based on geo distance and similar categories(1. get all favorite item ids; 2. get all categories, sort by the number of counts; 3. search based on category, filter out favorite items)
