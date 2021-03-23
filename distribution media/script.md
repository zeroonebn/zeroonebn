Lead:
=====
In this video I will show you how to create Central Site Distribution Media for IBM i and Licensed Programs.

Presentation:
My name is Damien Ferrand, welcome to 01 B N episode 1.

Intro:
======
IBM i 7.2 is reaching its end of support in a few month, if you're still running this version you are probably already planning your upgrade to 7.3 or 7.4, if not, you should start soon!
The main steps for a standard upgrade are the following:
1) Backup
2) Upgrade using IBM media
3) Install Cummulative, PTF groups and individual PTFs
4) Backup

Step 2 and 3 are quite lengthy and being able to skip step 3 would simplify the process and reduce the downtime needed for the upgrade.

That's exactly what Central site distribution media are for: you create your own upgrade media with all the PTF already installed.

Just like IBM media, they can be used for automatic upgrade but also for manual upgrade or install.

The main limitation is that IBM media are multilingual whereas central site distribution media can only have one primary language. If you have systems with different primary language, you will need to make multiple distribution media.

Secondary languages are not handled by central site distribution media, we'll see at the end of the video how to take care of those.

Prerequisites:
==============
You need a dedicated system to create
