# ToDoList


# Purpose and Description
Agendas have always been an integral part of my education. Ever since Elementary School, it was introduced to me as a tool that would serve as a memory aid and guide for success. I didn't learn about the true value of agendas until my later years in Highschool, when tasks started piling up and my memory held me back from succeeding. Soon enough, using an agenda become natural to me and I continued using agendas ever since.

Some shortcomings of physical agenda's that I've come across is that an agenda may be hard to access when one is not able to sit down to write a task down. For example, when running in between classes or commuting to and from school. To overcome this shortcoming and to also challenge my abilities in Computer Programming, I have decided to create a mobile application that can serve the same function of a physical agenda and more. I have created a To-Do-List application to monitor tasks, both completed or incomplete.

# Decisions
Having low exposure with the creation of Mobile Applications, I decided to use Android Studio because I own a Android device. I also decided to use Java instead of Kotlin because I wanted to improve my object-oriented programming skills.

# Getting Started
Download and install Microsoft SQL Server Management Studio and Microsoft SQL Server. If you want to run the app on a virtual machine, one can download Android Studio too.

# Installing and Setting Up
Pull or download the repository and either run the application on a virtual machine, or on an Android Phone.

Setting Up the SQL server
1. Start up SQL Server Manager (Ensure all protocols for MSSQLSERVER are enabled)
2. Go into TCP/IP protocol properties and take note of the TCP port.
3. Start up Microsoft SQL Server Management Studio, login initially with Server Type: Database Engine and Windows Authentication.
4. Go into under Security and add a new Login by right clicking. Make a username and password, while ensuring to check SQL Server authentication.
5. Disconnect and try reconnecting with the new SQL server authentication. Take note of the username and password.
6. Right click database and make a new database called 'toDoList'.
7. Create a new Query and execute the following command: ' CREATE TABLE VALUES_TABLE (id int IDENTITY PRIMARY KEY NOT NULL, dates Date NOT NULL, tag String NOT NULL, course String NOT NULL, title String) '
8. Change the ip, port, username, and password according to the names you used above. (In both the Pop and MainActivity class)

# Sources


# Potential Improvements
- Enable cross platform compatability.
- Be able to delete and edit tasks from the app.
- Improve overall aesthetics of the app.
