The application is using springboot with bootstrap and jquery and jsp
Run application as bootJar

Application may not run if webapp is not a source package

to run using executable fat jar, need to do some modification in build.
Currently the jar created does not have compiled jsp included.



if username and password is not authenticated or authorized.
default login and logout provided by adding spring securities.

It does not create fat-jar to execute jar.

http://localhost:12345/login?error
http://localhost:12345/logout?logout
Above two url's are implemented here

Check the following URL's
http://localhost:56789/login
http://localhost:56789/logout
http://localhost:56789/
http://localhost:56789/viewBooks
http://localhost:56789/addBook
http://localhost:56789/editBook
http://localhost:56789/deleteBook
http://localhost:56789/placeOrder

Improvements required in Lab28: is implemented here
A) Display either login/Register or logout
B) Display the Links accordning to Role.
C) Customize Login Page and Error Messages.
D) Customize Logout Page
E) Display the Role after the username.
•Display the following for Authenticated Users
Home Welcome : sri [ROLE_CUSTOMER] Logout
•Display the following for Un-Authenticated Users
Home Login Register

http://localhost:56789/showRegister not implemented

