Apps
====

Products on team work



import the project in to eclipse:

Steps:

Eclipse: File-> Import> Git-> Project from Git-> Clone URI->

Clone URL: https://github.com/Pefhaw/Apps.git



Find the instructions below for importing the project:

Install the Git Plugin for Eclipse (not necessary)
Right click on Projects Explorer and click on import, Next select 'git'->'projects'->'URI'
Enter 'https://github.com/jasonpolites/gesture-imageview.git'
Enter your github's username and password and press 'next'(not finish). Entering ur github details are optional. You can leave them empty also here.
Select the branch you want to download
Enter the directory in the next screen where the project will be imported. This will be your root directory
Press finish to import the project.
Then in the next step click on "Use the new project wizard'
Choose 'android project from existing code'
In the next screen click on 'browse' in the place where 'root directory' selection option is mentioned , select the place where you imported your project.
Press Finish
The project should be imported as an android project now


You apparently made your whole workspace into a git repo

Delete/backup your repo (the workspace/.git folder)
Go to github and create a new repo - say you name it my-project
Install msysgit. You will have to work with it sooner or later
Right click on your project folder and choose Git Bash here
Issue

$ git init
$ echo bin/ >> .gitignore # exclude the bin folder !
$ git add -A && git commit -m "All my files" # this will commit *all*
# better fire up the gui and choose what to commit
$ git remote add origin https://github.com/YOURUSERNAME/my-project.git
$ git push -u origin master
Done
