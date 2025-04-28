README - SER 335 Lab 6 Activity 1 Submission  
Author: Bhupinder Singh  
ASURITE: bsingh55

====================================
Included Files:
====================================
- lab6_act1_bsingh55.zip ➔ Final modified project (with all Lab 6 Task 1 and 2 changes).
- jfm_orig_bsingh55.zip ➔ Original starting project code before Lab 6 changes, as required.

====================================
General Setup:
====================================
1. Unzip the project (lab6_act1_bsingh55.zip) anywhere on your machine.

2. Open Terminal and navigate inside the unzipped project folder.

3. Build the project using:
   ant main

4. Navigate into the distribution folder:
   cd jfm-0.9.1

5. Start the program:
   ./run.sh

====================================
Important Notes:
====================================
- I used the Observer pattern to log all **failed login attempts** into a file named `jfmlog.txt`.  
  ➔ Check the contents of `jfmlog.txt` to see timestamped failed login attempts.

- I used the Factory and Strategy patterns to apply different **password validation strategies**.

- The password rules applied depend on the setting in the file:
  resources/jfm335.properties

Example content for switching strategy:

validateuserstrategy.property=edu.asu.ser335.jfm.StrictValidateUserStrategy



====================================
How to Test the Three Strategies:
====================================

**1. StrictValidateUserStrategy**
- Edit `resources/jfm335.properties` and set:

- validateuserstrategy.property=edu.asu.ser335.jfm.StrictValidateUserStrategy

- Then run the program (`./run.sh`).
- Login with:

- Username: strictuser 
- Password: Abc123!
- Role: admin


**2. ModerateValidateUserStrategy**
- Edit `resources/jfm335.properties` and set:

- validateuserstrategy.property=edu.asu.ser335.jfm.ModerateValidateUserStrategy
- Then run the program (`./run.sh`).
- Login with:

- Username: moderateuser 
- Password: Moderate1 
- Role: user



**3. NullValidateUserStrategy**
- Edit `resources/jfm335.properties` and set:

- validateuserstrategy.property=edu.asu.ser335.jfm.NullValidateUserStrategy

- Then run the program (`./run.sh`).
- Login with:

- Username: nulluser 
- Password: nulluser123 
- Role: user



====================================
Special Case (Blank Username and Blank Password):
====================================
- Only works under **NullValidateUserStrategy**.
- Login credentials:

- Username: (leave blank) 
- Password: (leave blank) 
- Role: admin


- **Important**:  
I have added a blank user inside `authentication.json`:
```json
{"name":"","password":"","role":"admin"}

➔ So we can successfully login using empty username and password under Null Strategy.



==================================== 
All Available Users:


Username	Password	Role			Strategy
strictuser	Abc123!  	admin		StrictValidateUserStrategy
moderateuser	Moderate1	user		ModerateValidateUserStrategy
nulluser	nulluser123	user		NullValidateUserStrategy
(blank user)(blank password)	admin		NullValidateUserStrategy


==================================== 
Important Coding Note:

- Every modification made for Lab 6 Activity 1 is clearly marked with:

// SER335 LAB6 TASK1

Please search for this comment inside the code to review the changes.

==================================== 
Thank you for reviewing my submission!



