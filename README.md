# CSE 312: Naive Bayes


**DEADLINES**

**Due: Friday, February 8th 10:00 PM**

**IMPORTANT**: Try to get started on Naive Bayes early so that you can come to the Office Hours in case you face problems with any of the parts.

## 0\. Outline
1. [Git](README.md#1-getting-started) **(not necessary for this assignment and you can skip this part, but it's good for learning about Git)**
2. [Information about the files and file structure](README.md#2-coding-it-up)
3. [Submitting your assignment](README.md#3-submitting-your-assignment)
    * [GitLab GUI](README.md#using-the-gitlab-gui) **(simpler)**
    * [Using Git](README.md#using-git) (requires having set up Git in part 1)

## 1\. Getting started

The following instructions are written for a Unix-based platform (e.g., Linux, MacOS, etc.) Because the code is written in Java, it should work under Windows as well, though the directions in this document may not apply.

### 1.1\. Working with Git

We will be using `git`, a source code control tool. This will allow you to download the code for the assignment, and also submit it in a standardized format that will streamline grading.

You will also be able to use `git` to commit your progress on the labs
as you go. This is **important**: Use `git` to back up your work. Back
up regulary by both committing and pushing your code as we describe below.

Course git repositories will be hosted as a repository in [GitLab](https://gitlab.cs.washington.edu). Your code will be in a private repository that is visible only to you and the course staff.

#### 1.1.1\. Getting started with Git

There are numerous guides on using `git` that are available. They range from being interactive to just text-based. Find one that works and experiment -- making mistakes and fixing them is a great way to learn. Here is a [link to resources](https://help.github.com/articles/what-are-other-good-resources-for-learning-git-and-github) that GitHub suggests starting with. If you have no experience with `git`, you may find this [web-based tutorial helpful](https://try.github.io/levels/1/challenges/1).

Git may already be installed in your environment; if it's not, you'll need to install it first. For `bash`/Linux environments, git should be a simple `apt-get` / `yum` / etc. install. More detailed instructions may be [found here](http://git-scm.com/book/en/Getting-Started-Installing-Git).

If you are using Eclipse or IntelliJ, many versions come with git already configured. The instructions will be slightly different than the command line instructions listed but will work for any OS. For Eclipse, detailed instructions can be found at [EGit User Guide](http://wiki.eclipse.org/EGit/User_Guide) or [EGit Tutorial](http://eclipsesource.com/blogs/tutorials/egit-tutorial).

#### 1.1.2\. Cloning your Naive Bayes repository

We've created a GitLab repository that you will use to implement Naive Bayes. This repository is hosted on the [CSE GitLab](https://gitlab.cs.washington.edu) site, and you can view it by visiting the GitLab website at `https://gitlab.cs.washington.edu/cse312-19wi/naive-bayes-[your GitLab username]`. If you don't see this repository or are unable to access it, let us know immediately!

The first thing you'll need to do is set up a SSH key to allow communication with GitLab:

1.  If you don't already have one, generate a new SSH key. See [these instructions](http://doc.gitlab.com/ce/ssh/README.html) for details on how to do this.
2.  Visit the [GitLab SSH key management page](https://gitlab.cs.washington.edu/profile/keys). You'll need to log in using your CSE account.
3.  Click "Add SSH Key" and paste in your **public** key into the text area.

While you're logged into the GitLab website, browse around to see which projects you have access to. You should have access to `naive-bayes-[your username]`.

We next want to move the code from the GitLab repository onto your local file system. To do this, you'll need to clone the lab repository by issuing the following commands on the command line:

```sh
$ git clone git@gitlab.cs.washington.edu:cse312-19wi/naive-bayes-MY_GITLAB_USERNAME.git
$ cd naive-bayes-MY_GITLAB_USERNAME
```

This will make a complete replica of the assignment repository locally. If you get an error that looks like:

```sh
Cloning into 'naive-bayes-myusername'...
Permission denied (publickey).
fatal: Could not read from remote repository.
```

... then there is a problem with your GitLab configuration. Check to make sure that your GitLab username matches the repository suffix, that your private key is in your SSH directory (`~/.ssh`) and has the correct permissions, and that you can view the repository through the website.

Cloning will make a complete replica of the lab repository locally. Any time you `commit` and `push` your local changes, they will appear in the GitLab repository.  Since we'll be grading the copy in the GitLab repository, it's important that you remember to push all of your changes!

Let's test out the repository by doing a push of your master branch to GitLab. Do this by issuing the following commands:

```sh
$ touch empty_file
$ git add empty_file
$ git commit -a -m 'Testing git'
$ git push # ... to origin by default
```

The `git push` tells git to push all of your **committed** changes to Gitlab.

After executing these commands, you should see something like the following:

```sh
Counting objects: 4, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 286 bytes | 0 bytes/s, done.
Total 3 (delta 1), reused 0 (delta 0)
To git@gitlab.cs.washington.edu:cse312-19wi/naive-bayes-username.git
   cb5be61..9bbce8d  master -> master
```

We pushed a blank file to our directory, which isn't very interesting. Let's clean up after ourselves:

```sh
$ # Tell git we want to remove this file from our repository
$ git rm empty_file
$ # Now commit all pending changes (-a) with the specified message (-m)
$ git commit -a -m 'Removed test file'
$ # Now, push this change to GitLab
$ git push
```

If you don't know Git that well, this probably seemed very arcane. Just keep using Git and you'll understand more and more. We'll provide explicit instructions below on how to use these commands to actually indicate your final lab solution.

## 2\. Coding it up

### 2.1\. Starter code

#### 2.1.1\. Data

Inside the “data” folder, the emails are separated into “train” and “test” data. Each
“train” email is already labeled as either spam or ham, and they should be used to train your
model and word probabilities. The “test” data is not labeled, and they are the emails whose
labels you will predict using your classifier.

The emails we are using are a subset of the Enron Corpus, which is a set of real emails from
employees at an energy company. The emails have a subject line and a body, both of which
are “tokenized” so that each unique word or bit of punctuation is separated by a space or
newline. The starter code provides a function that takes a filename and returns a set of all
of the distinct tokens in the file.

#### 2.1.2\. SpamFilterMain.java

The provided main executable file that handles file loading for you
and calls the NaiveBayes that you’ll implement. DO NOT MODIFY THIS FILE. You
will not turn in this file. The only file you will turn in is NaiveBayes.java, which is expected
to be run with the given version of `SpamFilterMain.java`.

#### 2.1.3\. NaiveBayes.java

NaiveBayes.java: The file you will modify and implement. A few notes:

* Do NOT modify the provided method headers. Again, the `NaiveBayes.java` you
turn in is expected to be run with the given `SpamFilterMain.java`.

* Output: make sure you follow the format shown in example output.txt 
(print the filename, a space, and either the word spam or ham), and print to stdout. Note that the
order of the filenames in your output does not matter.

* Think about the data structures you want to use to keep track of the word counts and/or
probabilities.

### 2.2\. Running the program

To run the program, first compile it with: `$ javac SpamFilterMain.java`, then, execute it with:
`$ java SpamFilterMain`.

Note, the `data` directory needs to be in the same directory in which the program is executed.
If you are running into issues loading the data (especially if you are using Eclipse) and you’re
not sure where to put the `data` directory, check the console output produced when you run
`SpamFilterMain`. The console output prints out the current working directory (cwd) where the
program is executing. Just move the entire `data` directory into that cwd that was printed.

### 2.3\. Comparing the result:

It is not expected that Naive Bayes will classify every single test email correctly, but it should
certainly do better than random chance! We are not grading you on whether you classify 100%
of the examples accurately, but rather on general program correctness.

After you’ve classified the 500 test emails, you can compare your results with the actual labels
that we hid from you, by using the output checker [here](https://courses.cs.washington.edu/courses/cse312/16au/nbc/checker.html). 
For this specific test dataset, you should
get an error score of **27** (total number of incorrectly classified emails). Note that we will run your
code on a test dataset you haven’t seen.

### 2.4\. Notes and advice

* Read about how to avoid floating point underflow in the notes.
* Do not use integer division when generating your word probabilities.
* Make sure you understand how smoothing works.
* Remember to remove any debug statements that you are printing to the output.
* Do not directly manipulate file paths or use hardcoded file paths. A file path you
have hardcoded into your program that works on your computer won’t work on the computer
we use to test your program. To get the name of the file, check out Java File’s `getName()`
method.
* If you use Eclipse, remove all package statements before you turn in your source
code.
* Needless to say, you should practice what you’ve learned in other courses: document your
program, use good variable names, keep your code clean and straightforward, etc. Include
comments outlining what your program does and how. We will not spend time trying to
decipher obscure, contorted code.

## 3\. Submitting your assignment

You may submit your code multiple times; we will use the latest version you submit that arrives before the deadline. 

The criteria for your lab being submitted on time is that your code must be tagged and pushed by the due date and time. This means that if one of the TAs or the instructor were to open up GitLab, they would be able to see your solutions on the GitLab web page.

## Using the GitLab GUI
1. Click NaiveBayes.java

![Alt](/imgs/NaiveBayesFile.PNG)

2. Click Replace

![Alt](/imgs/Replace.PNG)

3. Click "click to upload"

![Alt](/imgs/UploadFile.PNG)

4. Find your file and upload it
5. Press "Replace file"

![Alt](/imgs/LoadedFile.PNG)

6. Confirm that the submitted file is correct

![Alt](/imgs/uploadedFile.PNG)

7. Go to Tags (on the left, it might be minimized)

![Alt](/imgs/tagsbutton.PNG)

8. Click "New tag"

![Alt](/imgs/newtag.PNG)

9. Name the tag "complete"

![Alt](/imgs/tagged.PNG)

10. Scroll down and click "Create tag" on the bottom left 

![Alt](/imgs/writeTag.PNG)

11. Go back to Tags to make sure that the tag was made

![Alt](/imgs/confirmTags.PNG)

**Note: If you are resubmitting the assignment, you have to delete your tag using the red trash can symbol next on the right side of the tag.**

## Using Git

**Just because your code has been committed on your local machine does not mean that it has been submitted -- it needs to be on GitLab!**

There is a bash script `turnInLab.sh` in the root level directory of your repository that commits your changes, deletes any prior tag for the current lab, tags the current commit, and pushes the branch and tag to GitLab. If you are using Linux or Mac OSX, you should be able to run the following:

```sh
$ ./turnInLab.sh
```

You should see something like the following output:

```sh
$ ./turnInLab.sh
[master b155ba0] naive-bayes
 1 file changed, 1 insertion(+)
Deleted tag 'complete' (was b26abd0)
To git@gitlab.com:cse312-19wi/naive-bayes-username.git
 - [deleted]         complete
Counting objects: 11, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (6/6), 448 bytes | 0 bytes/s, done.
Total 6 (delta 3), reused 0 (delta 0)
To git@gitlab.com:cse312-19wi/naive-bayes-username.git
   ae31bce..b155ba0  master -> master
Counting objects: 1, done.
Writing objects: 100% (1/1), 152 bytes | 0 bytes/s, done.
Total 1 (delta 0), reused 0 (delta 0)
To git@gitlab.com:cse312-19wi/naive-bayes-username.git
 * [new tag]         complete -> complete
```

#### Final Word of Caution!

Git is a distributed version control system. This means everything operates offline until you run `git pull` or `git push`. This is a great feature.

The bad thing is that you may **forget to `git push` your changes**. This is why we strongly, strongly suggest that you **check GitLab to be sure that what you want us to see matches up with what you expect**.  As a second sanity check, you can re-clone your repository in a different directory to confirm the changes:

```sh
$ git clone git@gitlab.cs.washington.edu:cse312-19wi/naive-bayes-username.git confirmation_directory
$ cd confirmation_directory
$ # ... make sure everything is as you expect ...
```

We've had a lot of fun designing this assignment, and we hope you enjoy hacking on it!